# NfsAccessSensor

> This sensor checks if a network file system (NFS) is mounted and accessible. A client connection is established to network shares using NFS clients provided by the Sigar API. Our implementation handles both NFSv2 and NFSv3. Users have to set NFS mount point for checking via context parameters. The implemented class is [NfsAccessSensor](https://github.com/dhoppe83/visor/blob/hlrs-sensors/visor-sensors-hlrs/src/main/java/de/ustutt/omi/cloudiator/visor/sensors/fs/NfsAccessSensor.java).

## Configuration
| MonitorContext Parameter | Description | Example Value |
|:-------------------------|:------------|:--------------|
| nfs_root                 | Mount point of a NFS to be checked. No default value is provided. | `/media/nfs` |
