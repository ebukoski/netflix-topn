# netflix-topn (aka Groot)

Command line tool "Groot" that analyzes the popularity of an organization's GitHub repos.  Reports statistics for the following four dimensions:

* Top-N repos by number of stars.
* Top-N repos by number of forks.
* Top-N repos by number of Pull Requests (PRs).
* Top-N repos by contribution percentage (PRs/forks).

## Features
* Written in Groovy
* Supports threaded REST calls, threads are configurable at the command line
* Supports analyzing multiple github repos with single command line call
* Debug output for performance and call info
* Log4j logging
* Encapsulated in single class that can be reused in any JVM applicaiton

## Usage
```
usage: Groot [options] [orgs]
Options:
 -d       debug output
 -help    print this message
 -n <n>   Top N repos (default 10)
 -t <t>   Maximum threads to use (default 10)
```

### Examples

##### 1) analyze the NASA repos and return Top 1 ranking using default thread settings
```
$ Groot -n 1 nasa
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

##### 2) analyze the Nasa repos and return Top 3 ranking using 15 threads
```
$ Groot -n 3 -t 15 nasa
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

##### 2) analyze the Nasa repos and return Top 3 ranking using 15 threads (with debug)
```
$ Groot -d -n 3 -t 15 nasa
DEBUG - args n: 3, orgs: [nasa], threads: 15
DEBUG - getting repo for org: nasa, page: 1, ext_pull_request: true
DEBUG - getting repo for org: nasa, page: 2, ext_pull_request: true
DEBUG - getting pull count for owner: nasa, repo: NTL-ISS-Robonaut-2-Vision-Algorithm-Challenge
DEBUG - getting pull count for owner: nasa, repo: SCRD
DEBUG - getting pull count for owner: nasa, repo: cash
DEBUG - getting pull count for owner: nasa, repo: Kodiak
DEBUG - getting pull count for owner: nasa, repo: eefs
DEBUG - getting pull count for owner: nasa, repo: TTECTrA
DEBUG - getting pull count for owner: nasa, repo: NASA-3D-Resources
DEBUG - getting pull count for owner: nasa, repo: autodoc
DEBUG - getting pull count for owner: nasa, repo: NTL-Asteroid-Tracker
DEBUG - getting pull count for owner: nasa, repo: NTL-Lunar-Mapping-and-Modeling-Portal
DEBUG - getting pull count for owner: nasa, repo: WellClear
DEBUG - getting pull count for owner: nasa, repo: NTL-Planetary-Data-System-API
DEBUG - getting pull count for owner: nasa, repo: Formation-Flying
DEBUG - getting pull count for owner: nasa, repo: NTL-Solution-Mechanism-Guide
DEBUG - getting pull count for owner: nasa, repo: OpenSPIFe
DEBUG - getting pull count for owner: nasa, repo: NTL-Asteroid-Data-Hunter
DEBUG - getting pull count for owner: nasa, repo: api-docs
DEBUG - getting pull count for owner: nasa, repo: DAVEtools
DEBUG - getting pull count for owner: nasa, repo: NASA-Space-Weather-Media-Viewer
DEBUG - getting pull count for owner: nasa, repo: CrisisMappingToolkit
DEBUG - getting pull count for owner: nasa, repo: libSPRITE
DEBUG - getting pull count for owner: nasa, repo: pvslib
DEBUG - getting pull count for owner: nasa, repo: XPlaneConnect
DEBUG - getting pull count for owner: nasa, repo: europa
DEBUG - getting pull count for owner: nasa, repo: Open-Source-Catalog
DEBUG - getting pull count for owner: nasa, repo: StereoPipeline
DEBUG - getting pull count for owner: nasa, repo: visionworkbench
DEBUG - getting pull count for owner: nasa, repo: OpenMDAO-Framework
DEBUG - getting pull count for owner: nasa, repo: World-Wind-Java
DEBUG - getting pull count for owner: nasa, repo: OpenVSP
DEBUG - getting pull count for owner: nasa, repo: CertWare
DEBUG - getting pull count for owner: nasa, repo: echo
DEBUG - getting pull count for owner: nasa, repo: MCT-Plugins
DEBUG - getting pull count for owner: nasa, repo: coeci-cms-mpsp
DEBUG - getting pull count for owner: nasa, repo: VADER
DEBUG - getting pull count for owner: nasa, repo: github-latest-commits-widget
DEBUG - getting pull count for owner: nasa, repo: digital-strategy
DEBUG - getting pull count for owner: nasa, repo: cfe
DEBUG - getting pull count for owner: nasa, repo: osal
DEBUG - getting pull count for owner: nasa, repo: mct
DEBUG - getting pull count for owner: nasa, repo: code.nasa.gov
DEBUG - getting pull count for owner: nasa, repo: NASA-APIs
DEBUG - getting pull count for owner: nasa, repo: data.nasa.gov
DEBUG - getting pull count for owner: nasa, repo: 39A
DEBUG - getting pull count for owner: nasa, repo: NTL-Disruption-Tolerant-Networking
DEBUG - getting pull count for owner: nasa, repo: NTL-ISS-Food-Intake-Tracker
DEBUG - getting pull count for owner: nasa, repo: CoECI-CMS-Healthcare-Fraud-Prevention
DEBUG - getting pull count for owner: nasa, repo: CoECI-CMS-Open-Payment
DEBUG - getting pull count for owner: nasa, repo: NTL-Electrocardiogram
DEBUG - getting pull count for owner: nasa, repo: CoECI-USAID-Atrocity-Prevention-Model
DEBUG - getting pull count for owner: nasa, repo: NTL-Crater-Detection-Challenge
DEBUG - getting pull count for owner: nasa, repo: NTL-Collective-Minds-and-Machines-Exploration
DEBUG - getting pull count for owner: nasa, repo: T-MATS
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
DEBUG - elapsed time: 7.696 sec, 53 repos, 6.8866943867 repos per sec 
```
