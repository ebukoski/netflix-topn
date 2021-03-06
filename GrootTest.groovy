class GrootTest extends GroovyTestCase {
  void testDotnet() {
    Groot groot = new Groot()
    auth(groot)
    groot.githubRepos("dotnet")
    assert groot.repos.size() == 12

    def topstars = groot.githubTopRepos(5, "stargazers_count")
    assert topstars[0].name == "corefx"

    def forks = groot.githubTopRepos(5, "forks_count")
    assert forks[0].name == "corefx"

    def pulls = groot.githubTopRepos(5, "ext_pullrequests_count")
        assert pulls[0].name == "roslyn"

    def contribs = groot.githubTopRepos(5, "ext_contrib_pct")
    assert contribs[0].name == "codeformatter"
  }

  void testNasa() {
    Groot groot = new Groot()
    auth(groot)
    groot.githubRepos("nasa")
    assert groot.repos.size() == 53

    def topstars = groot.githubTopRepos(5, "stargazers_count")
    assert topstars[0].name == "mct"

    def forks = groot.githubTopRepos(5, "forks_count")
    assert forks[0].name == "mct"

    def pulls = groot.githubTopRepos(5, "ext_pullrequests_count")
    assert pulls[0].name == "osal"

    def contribs = groot.githubTopRepos(5, "ext_contrib_pct")
    assert contribs[0].name == "Open-Source-Catalog"
  }

  void testNetflix() {
    Groot groot = new Groot()
    auth(groot)
    groot.githubRepos("netflix")
    assert groot.repos.size() == 64

    def topstars = groot.githubTopRepos(5, "stargazers_count")
    assert topstars[0].name == "Hystrix"

    def forks = groot.githubTopRepos(5, "forks_count")
    assert forks[0].name == "Cloud-Prize"

    def pulls = groot.githubTopRepos(5, "ext_pullrequests_count")
    assert pulls[0].name == "astyanax"

    def contribs = groot.githubTopRepos(5, "ext_contrib_pct")
    assert contribs[0].name == "Surus"
  }

  private static void auth(groot) {
    def user = System.getProperty("user")
    def pass = System.getProperty("pass")
    Groot.headers += ["Authorization" : 'Basic ' + "${user}:${pass}".bytes.encodeBase64().toString()]
  }
}
