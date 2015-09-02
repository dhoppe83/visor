package de.ustutt.omi.cloudiator.visor.sensors.net;

import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.SigarProxyCache;

import de.uniulm.omi.cloudiator.visor.monitoring.Measurement;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementImpl;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementNotAvailableException;
import de.uniulm.omi.cloudiator.visor.monitoring.MonitorContext;
import de.ustutt.omi.cloudiator.visor.sensors.SensorUtils;

public class NetPacketSensor extends NetInterfaceSensor {

	@Override
	protected Measurement getMeasurement(MonitorContext monitorContext)
			throws MeasurementNotAvailableException {
		return new MeasurementImpl(System.currentTimeMillis(), getNetStat());
	}
	
	protected long getNetStat() throws MeasurementNotAvailableException {
		try {
			NetInterfaceStat stat = SigarProxyCache.newInstance().getNetInterfaceStat(device);
			if (mode.equals(SensorUtils.RX)) {
				return stat.getRxPackets();
			} else {
				return stat.getTxPackets();
			}
		} catch (Exception e) {
			throw new MeasurementNotAvailableException(e);
		}
	}
}
