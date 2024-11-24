Contains a bunch of sample event JSON objects from the Harvard and MIT events calendars.

* harvard_event1.json
    * recurring event
    * includes location, but address is empty, no "geo" property
    * both startDate and endDate only include the (local) date, local ISO format "yyyy-MM-dd"
* harvard_event2.json
    * recurring event
    * includes location with value for address, plus internal "geo" object
    * both startDate and endDate include both date and time, format = "yyyy-MM-ddThh:mm:ss(+-)offset" where offset is "hh:mm"
* harvard_event3.json
    * non-recurring event
    * no location property
    * both startDate and endDate only include the (local) date, local ISO format "yyyy-MM-dd"
* harvard_event4.json
    * non-recurring event
    * no location property
    * startDate has both date and time, but endDate has only local date

* mit_event1.json
    * includes virtual location with zoom url link
    * includes "offers" object with "free" price
    * startDate and endDate both have date/time
* mit_event2.json
    * includes location with address plus internal "geo" object
    * includes "offers" object with "0" price
    * startDate and endDate both have date/time
