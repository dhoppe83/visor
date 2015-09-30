# UploadBandwidthSensor

> This sensor implements functionality to measure upload and download data rates of a given network interface. We reuse the implementations for the TxBytes and RxBytes sensors to compute the bandwidth. Those values are then used to compute the bandwidth per second. For each connection type, i.e. upload or download, an individual sensor is implemented. The implemented classes are [DownloadBandwidthSensor](https://github.com/dhoppe83/visor/blob/hlrs-sensors/visor-sensors-hlrs/src/main/java/de/ustutt/omi/cloudiator/visor/sensors/net/DownloadBandwidthSensor.java) and [UploadBandwidthSensor](https://github.com/dhoppe83/visor/blob/hlrs-sensors/visor-sensors-hlrs/src/main/java/de/ustutt/omi/cloudiator/visor/sensors/net/UploadBandwidthSensor.java).


## Configuration
| MonitorContext Parameter | Description | Example Value |
|:-------------------------|:------------|:--------------|
| net_device               | The network interface to be monitored. The value defaults to `eth0` (Linux). | `wlan0` |
| unit                     | The unit of measurement. Unit of measurments available are: KBytes/s (`kb`), MBytes/s (`mb`), and GBytes/s (`gb`). The value defaults to `kb`. | `mb` |
