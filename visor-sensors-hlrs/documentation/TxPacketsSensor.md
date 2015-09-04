# TxPacketsSensor

> This sensor reports on transmitted and received network packages of a given network interface. This information is again retrievable by the __NetInterfaceStat__ class of the Sigar API to monitor the data. The sensor follows the implementation of the TxBytes and RxBytes sensors save monitoring transferred packets instead of bytes. The mplemented classes are [TxPacketsSensor](https://github.com/dhoppe83/visor/blob/hlrs-sensors/visor-sensors-hlrs/src/main/java/de/ustutt/omi/cloudiator/visor/sensors/net/TxPacketsSensor.java) and [RxPacketsSensor](https://github.com/dhoppe83/visor/blob/hlrs-sensors/visor-sensors-hlrs/src/main/java/de/ustutt/omi/cloudiator/visor/sensors/net/RxPacketsSensor.java).


## Configuration
| MonitorContext Parameter | Description | Example Value |
|:-------------------------|:------------|:--------------|
| net_device               | The network interface to be monitored. The value defaults to `eth0` (Linux). | `wlan0` |
