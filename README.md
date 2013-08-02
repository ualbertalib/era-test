era-test
=========

Tests for era.library.ualberta.ca

Background
----------
This package performs tests to validate and verify the ERA project.

Currently just a placeholder for tests -- will run a Selenese JUnit test to check Edmonton weather.  Requires that a Selenium Grid with at least one node be available at the ${grid.host}:${grid.port} property. This should be available in a file called era.properties. See the selenium-grid/README.md for more information about starting the hub and node(s).

Requirements
------------
* Java 6+
* Apache Ant [http://ant.apache.org/]
* Apache Ivy [http://ant.apache.org/ivy/]
* Mozilla Firefox [http://www.mozilla.org/en-US/firefox/new/]
* Other Browsers
  * Internet Explorer (Windows only)
  * Safari
  * Chrome (or Chromium) (Windows and Linux)
* A network connection
 * to download dependencies
 * to access pages under test

To Run
------
    ant

See also
--------
* Selenium [http://seleniumhq.org/]
