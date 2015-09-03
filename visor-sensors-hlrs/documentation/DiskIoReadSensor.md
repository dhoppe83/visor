# DiskIoReadSensor

> This sensor reports on the disk I/O performance of a given disk. The class __DiskUsage__ of the Sigar API is employed to assess relevant values for disk reads and writes per second. For example, the command-line utility __iostat__ is used on Linux systems to obtain this data. HLRS provides two individual sensors that measure disk reads and writes, respectively. Users are able to select the disk and unit of measurement (e.g., MBytes/s). The implemented classes are __DiskIoReadsSensor__ and __DiskIoWritesSensor__.

## Configuration
| MonitorContext Parameter | Description | Example Value |
|:-------------------------|:------------|:--------------|
| fs_device                | The mounting point to the disk to be measured. The value defaults to `sda`. | `sdb` |
| unit                     | The unit of measurement. Unit of measurments available are: MBytes/s (`mb`), GBytes/s (`gb`), TBytes/s (`tb`). The value defaults to `gb`. | `mb` |
