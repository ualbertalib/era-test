Selenium-Grid
=============

This is all you need to start a Selenium Grid hub or node.

Selenese JUnit tests will use the nodes managed by the hub to execute it's tests on various operating systems and browsers.  Ensure that the browsers available in the Node configuration file are accurate or errors may occur.

Starting a Hub
--------------
To start a hub with default parameters, run the following command from a command-line shell. This will work on all the supported platforms, Windows Linux, or MacOs.

     java -jar selenium-server-standalone-2.21.0.jar -role hub [-hubConfig FILENAME] 

Starting a Node
---------------
To start a node using default parameters, run the following command from a command-line.

     java -jar selenium-server-standalone-2.21.0.jar -role node  -hub http://localhost:4444/grid/register [-nodeConfig FILENAME]

