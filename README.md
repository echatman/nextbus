nextbus [![Build Status](https://travis-ci.org/echatman/nextbus.svg?branch=master)](https://travis-ci.org/echatman/nextbus)
=======



Simple Java client for the NextBus API.

Official NextBus documentation: http://www.nextbus.com/xmlFeedDocs/NextBusXMLFeed.pdf

Quick start:
```Java
NextBusService nextBusService = new NextBusService();
AgencyListResponse agencyList = nextBusService.agencyList();
RouteListResponse routeList = nextBusService.routeList("sf-muni");
ScheduleResponse scheduleResponse = nextBusService.schedule("sf-muni", "N");
// and so on
```
