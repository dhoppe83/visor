# Documentation
This documentation provides for each sensor a brief description including available parameters to be set at run-time.

## File System Sensors
| Name of sensor                             | Description                                         |
| :----------------------------------------- |:----------------------------------------------------|
| [DiskIoReadSensor][DiskIoReadSensor]       | Reports on data reads per second for a given disk   |
| [DiskIoWriteSensor][DiskIoWriteSensor]     | Reports on data writes per second for a given disk  |
| [FreeDiskSpaceSensor][FreeDiskSpaceSensor] | Reports on available disk space                     |
| [NfsAccessSensor][NfsAccessSensor]         | Checks the availability of a NFS via pinging        |

## Network Sensors
| Name of sensor                                   | Description                                               |
| :----------------------------------------------- |:----------------------------------------------------------|
| [DownloadBandwidthSensor][DownloadBandwidthSensor] | Reports on the download speed of a given network device   |
| [NetworkLatencySensor][NetworkLatencySensor]     | Reports on the network latency for a specific host        |
| [RxBytesSensor][RxBytesSensor]                   | Reports on the accumulated RxBytes for a given device     |
| [RxPacketsSensor][RxPacketsSensor]               | Reports on the accumulated RxPackets for a given device   |
| [TxBytesSensor][TxBytesSensor]                   | Reports on the accumulated TxBytes for a given device     |
| [TxPacketsSensor][TxPacketsSensor]               | Reports on the accumulated TxPackets for a given device   |
| [UploadBandwidthSensor][UploadBandwidthSensor]     | Reports on the upload speed of a given network device     |

[DiskIoReadSensor]: DiskIoReadSensor.md
[DiskIoWriteSensor]: DiskIoWriteSensor.md
[FreeDiskSpaceSensor]: FreeDiskSpaceSensor.md
[NfsAccessSensor]: NfsAccessSensor.md

[DownloadBandwidthSensor]: DownloadBandwidthSensor.md
[NetworkLatencySensor]: NetworkLatencySensor.md
[RxBytesSensor]: RxBytesSensor.md
[RxPacketsSensor]: RxPacketsSensor.md
[TxBytesSensor]: TxBytesSensor.md
[TxPacketsSensor]: TxPacketsSensor.md
[UploadBandwidthSensor]: UploadBandwidthSensor.md
