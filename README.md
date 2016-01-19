[Akka-Quartz-Scheduler-Application](http://blog.knoldus.com/2016/01/18/code-dissection-akka-quartz-scheduler-scalas-way-of-scheduling/)
=================================

This repository describes a basic Example of  Akka-Quartz-Scheduler. This example describes how we can use Akka-Quartz-Scheduler in our application for time based jobs.

### Scalastyle : Check the code quality

To check code quality of all the modules
```
$ activator clean compile scalastyle
```

### Scoverage : Check code coverage of test cases

To check code coverage of test cases for all modules
```
$ activator clean coverage test
```
By default, scoverage will generate reports for each project seperately. You can merge them into an aggregated report by invoking
```
$ activator coverageAggregate
```

### Deployment :
```
$ activator run
```

-----------------------------------------------------------------------
### References
-----------------------------------------------------------------------
* [Akka-Quartz-Scheduler](https://github.com/enragedginger/akka-quartz-scheduler)