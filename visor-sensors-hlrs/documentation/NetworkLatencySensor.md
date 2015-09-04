# NetworkLatencySensor

> Network latency can be measured two-fold: Latency can be assessed either by the time a data packet requires to reach its destination (i.e. one-way), or by additionally taking into account the time it takes back from destination to source (i.e. round-trip). Intuitively, the [Ping][ping] utility is first choice to measure the latency. However, we have to be aware that Ping operates through the Internet Control Message Protocol (ICMP), and thus doesn't use the more common Transport Control Protocol (TCP). As a consequence, we are relying on a simple socket connection established via native Java methods to obtain latency. Users can set the following parameters in the configuration file: destination address, port number, and number of measurements (cf. Table 1). The sensor then computes an average over all measurements, and pushes the average connection time in milliseconds to the monitoring agent; the implemented class is called [NetworkLatencySensor](https://github.com/dhoppe83/visor/blob/hlrs-sensors/visor-sensors-hlrs/src/main/java/de/ustutt/omi/cloudiator/visor/sensors/net/NetworkLatencySensor.java).


## Configuration
| MonitorContext Parameter | Description | Example Value |
|:-------------------------|:------------|:--------------|
| host                     | A given host to monitor the latency for. | `http://www.google.com` |
| port                     | The port number on the host. This value defaults to `80`. | `80` |
| loops                    | Sets the number of measurements for which an average latency is computed. | `3` |


[ping]: http://manpages.ubuntu.com/manpages/trusty/man8/ping.8.html
