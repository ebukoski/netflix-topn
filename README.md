# <img src="http://th00.deviantart.net/fs71/PRE/f/2014/255/c/7/groot_dancing_by_vanessabettencourt-d7yy2eu.jpg" height="100" width="100"> netflix-topn (aka Groot)

Command line tool "Groot" that analyzes the popularity of an organization's GitHub repos.  Reports statistics for the following four dimensions:

* Top-N repos by number of stars.
* Top-N repos by number of forks.
* Top-N repos by number of Pull Requests (PRs).
* Top-N repos by contribution percentage (PRs/forks).

## Features
* Written in [Groovy](http://groovy.codehaus.org/)
* Supports threaded REST calls, threads are configurable at the command line
* Supports analyzing multiple github repos with single command line call
* Debug output for performance and REST call info
* Log4j logging
* Encapsulated in single class that can be reused in any JVM applicaiton

## Usage
```
usage: groovy Groot [options] [orgs]
Options:
 -d       debug output
 -help    print this message
 -n <n>   Top N repos (default 10)
 -t <t>   Maximum threads to use (default 10)
```

## Installing
1. Install Java 7 or later
1. [Install Groovy 2.4](http://groovy.codehaus.org/Installing+Groovy)
1. Download [Groovy.groot](https://raw.githubusercontent.com/ebukoski/netflix-topn/master/Groot.groovy)
1. Run it!  NOTE: the first time run will be a bit slow as all dependencies are downloaded.
```
$ groovy Groot -help
```

## Limitations
* Org and repo properites (stars, forks, etc) are assumed to be present, will error out hard if they are missing
* Most exceptions are pushed all the way back to the stack
* Log4J output formatting could be improved
* Would be nice to create a [Unix-friendly Groovy script](http://groovy.codehaus.org/Running) that doesn't require Groovy on the command line

## Tests

JUnit tests are provided in the file [GroovyTest.groovy](https://github.com/ebukoski/netflix-topn/blob/master/GrootTest.groovy).  To run the tests:

```
$ groovy GrootTest
```

## Examples

##### 1) analyze the NASA repos and return Top 1 ranking using default thread settings
```
$ groovy Groot -n 1 nasa
INFO - Top 1 rankings for [nasa] by [stargazers_count]
INFO - rank: 1, id: 4193864, name: mct, stargazers_count: 470
INFO -
INFO - Top 1 rankings for [nasa] by [forks_count]
INFO - rank: 1, id: 4193864, name: mct, forks_count: 144
INFO -
INFO - Top 1 rankings for [nasa] by [ext_pullrequests_count]
INFO - rank: 1, id: 4814601, name: osal, ext_pullrequests_count: 3
INFO -
INFO - Top 1 rankings for [nasa] by [ext_contrib_pct]
INFO - rank: 1, id: 24205073, name: Open-Source-Catalog, ext_contrib_pct: 25
INFO -
```

##### 2) analyze the NASA repos and return Top 3 ranking using 15 threads
```
$ groovy Groot -n 3 -t 15 nasa
INFO - Top 3 rankings for [nasa] by [stargazers_count]
INFO - rank: 1, id: 4193864, name: mct, stargazers_count: 470
INFO - rank: 2, id: 3071996, name: visionworkbench, stargazers_count: 199
INFO - rank: 3, id: 3043479, name: World-Wind-Java, stargazers_count: 178
INFO -
INFO - Top 3 rankings for [nasa] by [forks_count]
INFO - rank: 1, id: 4193864, name: mct, forks_count: 144
INFO - rank: 2, id: 3071996, name: visionworkbench, forks_count: 38
INFO - rank: 3, id: 3043479, name: World-Wind-Java, forks_count: 33
INFO -
INFO - Top 3 rankings for [nasa] by [ext_pullrequests_count]
INFO - rank: 1, id: 4814601, name: osal, ext_pullrequests_count: 3
INFO - rank: 2, id: 4193864, name: mct, ext_pullrequests_count: 2
INFO - rank: 3, id: 24205073, name: Open-Source-Catalog, ext_pullrequests_count: 2
INFO -
INFO - Top 3 rankings for [nasa] by [ext_contrib_pct]
INFO - rank: 1, id: 24205073, name: Open-Source-Catalog, ext_contrib_pct: 25
INFO - rank: 2, id: 14899786, name: NTL-Crater-Detection-Challenge, ext_contrib_pct: 20
INFO - rank: 3, id: 4814601, name: osal, ext_contrib_pct: 18.75
INFO -
```

##### 3) analyze the NASA repos and return Top 3 ranking using 15 threads (with debug)
```
$ groovy Groot -d -n 3 -t 15 nasa
DEBUG - args n: 3, orgs: [nasa], threads: 15
DEBUG - REST: repo: https://api.github.com/orgs/nasa/repos?page=1
DEBUG - REST: repo: https://api.github.com/orgs/nasa/repos?page=2
DEBUG - REST: pull count: https://api.github.com/repos/nasa/NTL-ISS-Robonaut-2-Vision-Algorithm-Challenge/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/autodoc/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/cash/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/SCRD/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/WellClear/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/Kodiak/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/eefs/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/OpenSPIFe/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/NTL-Solution-Mechanism-Guide/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/NTL-Planetary-Data-System-API/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/Formation-Flying/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/NASA-3D-Resources/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/TTECTrA/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/NTL-Lunar-Mapping-and-Modeling-Portal/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/DAVEtools/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/NTL-Asteroid-Tracker/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/Open-Source-Catalog/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/NTL-Asteroid-Data-Hunter/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/europa/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/XPlaneConnect/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/pvslib/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/libSPRITE/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/CrisisMappingToolkit/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/api-docs/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/World-Wind-Java/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/OpenMDAO-Framework/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/StereoPipeline/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/visionworkbench/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/NASA-Space-Weather-Media-Viewer/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/OpenVSP/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/CertWare/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/mct/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/osal/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/cfe/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/digital-strategy/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/github-latest-commits-widget/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/VADER/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/coeci-cms-mpsp/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/MCT-Plugins/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/echo/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/NASA-APIs/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/code.nasa.gov/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/data.nasa.gov/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/39A/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/NTL-Disruption-Tolerant-Networking/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/NTL-ISS-Food-Intake-Tracker/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/CoECI-USAID-Atrocity-Prevention-Model/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/CoECI-CMS-Open-Payment/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/NTL-Electrocardiogram/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/CoECI-CMS-Healthcare-Fraud-Prevention/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/T-MATS/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/NTL-Collective-Minds-and-Machines-Exploration/pulls
DEBUG - REST: pull count: https://api.github.com/repos/nasa/NTL-Crater-Detection-Challenge/pulls
INFO - Top 3 rankings for [nasa] by [stargazers_count]
INFO - rank: 1, id: 4193864, name: mct, stargazers_count: 470
INFO - rank: 2, id: 3071996, name: visionworkbench, stargazers_count: 199
INFO - rank: 3, id: 3043479, name: World-Wind-Java, stargazers_count: 178
INFO -
INFO - Top 3 rankings for [nasa] by [forks_count]
INFO - rank: 1, id: 4193864, name: mct, forks_count: 144
INFO - rank: 2, id: 3071996, name: visionworkbench, forks_count: 38
INFO - rank: 3, id: 3043479, name: World-Wind-Java, forks_count: 33
INFO -
INFO - Top 3 rankings for [nasa] by [ext_pullrequests_count]
INFO - rank: 1, id: 4814601, name: osal, ext_pullrequests_count: 3
INFO - rank: 2, id: 4193864, name: mct, ext_pullrequests_count: 2
INFO - rank: 3, id: 24205073, name: Open-Source-Catalog, ext_pullrequests_count: 2
INFO -
INFO - Top 3 rankings for [nasa] by [ext_contrib_pct]
INFO - rank: 1, id: 24205073, name: Open-Source-Catalog, ext_contrib_pct: 25
INFO - rank: 2, id: 14899786, name: NTL-Crater-Detection-Challenge, ext_contrib_pct: 20
INFO - rank: 3, id: 4814601, name: osal, ext_contrib_pct: 18.75
INFO -
DEBUG - elapsed time: 5.767 sec, 53 repos, 9.1902202185 repos/sec
```
## References
The following links could be helpful:
* Github API: http://developer.github.com/v3/
* List an organization’s repos: http://developer.github.com/v3/repos/#list-organization-repositories
* List pull requests: http://developer.github.com/v3/pulls/
* Rate limits on API: https://developer.github.com/v3/#rate-limiting
