@Grab(group='log4j', module='log4j', version='1.2.16')
@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7')

import java.util.concurrent.Executors
import java.util.concurrent.Callable
import groovyx.net.http.RESTClient
import groovy.util.logging.Log4j
import org.apache.log4j.Level

@Log4j
class Groot {
  def repoThreadPool = null
  def pullThreadPool = null
  def repos = []
  def headers = ["User-Agent":"Apache HTTPClient"]

  static void main(String[] args) {
    def start = System.currentTimeMillis()
    def options = parseOptions(args)
    
    def n = (options.n) ? options.n.toInteger() : 10
    def threads = (options.t) ? options.t.toInteger() : 10
    def orgs = options.arguments()
    log.level = options.d ? Level.DEBUG : Level.INFO
    if (options.user && options.pass) {
      Groot.headers += ["Authorization" : 'Basic ' + "${options.user}:${options.pass}".bytes.encodeBase64().toString()]
    }
    log.debug "args n: ${n}, orgs: ${orgs}, threads: ${threads}, user: ${options.user}"

    def repocount = 0
    orgs.each {
      Groot groot = new Groot(threads)
      groot.githubRepos(it)

      def topstars = groot.githubTopRepos(n, "stargazers_count")
      dump(it, topstars, "stargazers_count", n)

      def forks = groot.githubTopRepos(n, "forks_count")
      dump(it, forks, "forks_count", n)

      def pulls = groot.githubTopRepos(n, "ext_pullrequests_count")
      dump(it, pulls, "ext_pullrequests_count", n)

      def contribs = groot.githubTopRepos(n, "ext_contrib_pct")
      dump(it, contribs, "ext_contrib_pct", n)    
        
      repocount += groot.repos.size()
    }
    
    def time = (System.currentTimeMillis()-start) / 1000.0
    log.debug "elapsed time: ${time} sec, ${repocount} repos, ${repocount / time} repos/sec"
  }

  static def parseOptions(args) {
    def cli = new CliBuilder(usage:'Groot [options] [orgs]',
                             header:'Options:')
    cli.help('print this message')
    cli.n(args: 1, argName: 'n', 'Top N repos (default 10)')
    cli.t(args: 1, argName: 't', 'Maximum threads to use (default 10)')
    cli.d('debug output')
    cli.user(args: 1, argName: 'user', 'Username for API login (defaults to anonymous if not provided)')
    cli.pass(args: 1, argName: 'pass', 'Password for API login (defaults to anonymous if not provided)')    
    def options = cli.parse(args)

    if (options.arguments().size() == 0) {
        cli.usage()
        System.exit(0)
    } 
    return options
  }
  
  static def dump(org, repos, prop, n) {
    log.info "Top ${n} rankings for [${org}] by [${prop}]"
    repos.eachWithIndex { r, i -> 
      def propval = r."${prop}"
      log.info "rank: ${i+1}, id: ${r.id}, name: ${r.name}, ${prop}: ${propval}"        
    }
    log.info("")
  }
  
  def Groot(threads=10) { 
    repoThreadPool = Executors.newFixedThreadPool(threads)
    pullThreadPool = Executors.newFixedThreadPool(threads)
  } 
    
  def githubPullRequestCount = { i, owner, repo ->
    log.debug("REST: pull count: https://api.github.com/repos/${owner}/${repo}/pulls")
    def github = new RESTClient("https://api.github.com"); 
    def response = github.get(path: "/repos/${owner}/${repo}/pulls", headers: headers)
    [ i: i, count: response.data.size() ]
  }

  def githubReposPage = { org, page, ext_pull_request ->
    log.debug "REST: repo: https://api.github.com/orgs/${org}/repos?page=${page}"; 
    def github = new RESTClient("https://api.github.com"); 
    def response = github.get(path: "/orgs/${org}/repos", query: [page:page], headers: headers)
    
    if (ext_pull_request) {
      response.data.each { it.ext_pullrequests_count = 0 }
      def futures = (0..response.data.size()-1).collect { i ->
          pullThreadPool.submit( { githubPullRequestCount(i, response.data[i].owner.login, response.data[i].name) } as Callable);      
      }
      futures.each { 
        def map = it.get() 
        response.data[map.i].ext_pullrequests_count += map.count
      }
      response.data.each { 
	    it.ext_contrib_pct = (it.forks_count == 0) ? 0 : it.ext_pullrequests_count * 100 / it.forks_count
      }
    }
  }  
  
  def githubRepos(org, ext_pull_request=true) {
    def github = new RESTClient("https://api.github.com"); 
    def response = github.get(path: "/orgs/${org}", headers: headers)
    def repocount = response.data.public_repos.toInteger()  
    int pages = (repocount / 30) + (repocount % 30 > 0 ? 1 : 0)
    try {
      def futures = (1..pages).collect{ page ->
        repoThreadPool.submit( { githubReposPage(org, page, ext_pull_request) } as Callable);
      } 
      futures.each{ repos += it.get() }
    } finally {
      repoThreadPool.shutdown()
      pullThreadPool.shutdown()
    }
  }

  def githubTopRepos(n, prop, descending=true) {
    def m = Math.min(n, repos?.size())
    def order = descending ? -1 : 1 
    repos?.sort { order * it."${prop}" }[0..m-1] 
  }
}
