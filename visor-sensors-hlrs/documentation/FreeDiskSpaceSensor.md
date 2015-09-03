# FreeDiskSpaceSensor

> This sensor reports on the available disk space for a given file system. To achieve a unified monitoring solution, we rely again on the Sigar API. The API implements the class __FileSystemUsage__, that offers a proxy to native utilities such as `df` on Linux systems. The file system's `root path` can be configured via the user-defined context at run-time. The implemented class is [FreeDiskSpaceProbe](https://github.com/dhoppe83/visor/blob/hlrs-sensors/visor-sensors-hlrs/src/main/java/de/ustutt/omi/cloudiator/visor/sensors/fs/FreeDiskSpaceSensor.java).

## Configuration
| MonitorContext Parameter | Description | Example Value |
|:-------------------------|:------------|:--------------|
| fs_root                  | The path to the file system to be monitored. The value defaults to `/` (Linux) and `D:/` (Windows). | `/dev` |
| unit                     | The unit of measurement. Unit of measurments available are: MBytes/s (`mb`), GBytes/s (`gb`), TBytes/s (`tb`). The value defaults to `gb`. | `mb` |
