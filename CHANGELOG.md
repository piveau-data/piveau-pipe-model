# ChangeLog

## [2.1.1](https://gitlab.fokus.fraunhofer.de/viaduct/pipe-model/tags/2.1.1) (2019-12-10)

**Added:**
* Pretty print as extension for pipe
 
## [2.1.0](https://gitlab.fokus.fraunhofer.de/viaduct/pipe-model/tags/2.1.0) (2019-08-28)

**Added:**
* `currentEndpoint`

**Changed:**
* `nextEndpoint` now always after `currentEndpoint`

**Fixed:**
* Typo in `PipeHeader` description

## [2.0.0](https://gitlab.fokus.fraunhofer.de/viaduct/pipe-model/tags/2.0.0) (2019-08-21)

**Added:**
* Required property `name` in `PipeHeader`

**Changed:**
* `nextSegment` is first of not processed segments
* `config` not nullable field
* `dataInfo` not nullable field

## [1.0.3](https://gitlab.fokus.fraunhofer.de/viaduct/pipe-model/tags/1.0.3) (2019-08-13)

**Fixed:**
* Pipe stringify for deep copy

## [1.0.2](https://gitlab.fokus.fraunhofer.de/viaduct/pipe-model/tags/1.0.2) (2019-08-12)

**Fixed:**
* Pipe deep copy
 
## [1.0.1](https://gitlab.fokus.fraunhofer.de/viaduct/pipe-model/tags/1.0.1) (2019-08-09)

**Fixed:**
* Data classes now have an empty default constructor

## [1.0.0](https://gitlab.fokus.fraunhofer.de/viaduct/pipe-model/tags/1.0.0) (2019-08-09)

**Added:**
* jacoco for code coverage

**Changed:**
* Upgrade to kotlin 1.3.41

**Fixed:**
* Annotate factory methods with JvmStatic

## [0.0.6](https://gitlab.fokus.fraunhofer.de/viaduct/pipe-model/tags/0.0.6) (2019-07-30)

**Added:**
* Test stage in gitlab ci

**Changed:**
* Re-implemented everything in kotlin

## [0.0.3-0.0.5](https://gitlab.fokus.fraunhofer.de/viaduct/pipe-model/tags/0.0.5)
Missed documentation of intermediate releases.

## [0.0.2](https://gitlab.fokus.fraunhofer.de/viaduct/pipe-model/tags/0.0.2) (2019-04-15)

**Added:**
- Start time property in pipe model
- ChangeLog #1

**Changed:**
- Tagging version without prefix 'v'

**Fixed:**
- Link to release tag v0.0.1 in ChangeLog

## [v0.0.1](https://gitlab.fokus.fraunhofer.de/viaduct/pipe-model/tags/v0.0.1) (2019-03-01)
Initial release