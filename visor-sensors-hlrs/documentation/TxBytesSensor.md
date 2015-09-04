# TxBytesSensor

> This sensor implements funcitonality to report on transmitted and received bytes of a given network interface. We have implemented a proxy to the class __NetInterfaceStat__ of the Sigar API to monitor the data. The respective class provides accumulated data for transmitted (`TxBytes`) and received (`RxBytes`), respectively. For example, the command-line utility __ifconfig__ is used on Linux platforms to monitor transferred bytes. Again, separate sensors for transmitted and received data are provided; implemented classes are [TxBytesSensor](https://github.com/dhoppe83/visor/blob/hlrs-sensors/visor-sensors-hlrs/src/main/java/de/ustutt/omi/cloudiator/visor/sensors/net/TxBytesSensor.java) and [RxBytesSensor](https://github.com/dhoppe83/visor/blob/hlrs-sensors/visor-sensors-hlrs/src/main/java/de/ustutt/omi/cloudiator/visor/sensors/net/RxBytesSensor.java).


## Configuration
| MonitorContext Parameter | Description | Example Value |
|:-------------------------|:------------|:--------------|
| net_device               | The network interface to be monitored. The value defaults to `eth0` (Linux). | `wlan0` |
| unit                     | The unit of measurement. Unit of measurments available are: KBytes/s (`kb`), MBytes/s (`mb`), and GBytes/s (`gb`). The value defaults to `kb`. | `mb` |
