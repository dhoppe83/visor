package de.ustutt.omi.cloudiator.visor.sensors.net;

import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;

import de.uniulm.omi.cloudiator.visor.monitoring.Measurement;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementImpl;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementNotAvailableException;
import de.uniulm.omi.cloudiator.visor.monitoring.MonitorContext;
import de.ustutt.omi.cloudiator.visor.sensors.AbstractNetInterfaceSensor;

public class TxPacketsSensor extends AbstractNetInterfaceSensor {

	@Override
	protected Measurement getMeasurement(MonitorContext monitorContext)
			throws MeasurementNotAvailableException {
		return new MeasurementImpl(System.currentTimeMillis(), getNetStat());
	}

	@Override
	protected long getNetStat() throws MeasurementNotAvailableException {
		try {
			SigarProxy proxy = SigarProxyCache.newInstance();
			NetInterfaceStat stat = proxy.getNetInterfaceStat(device);
			return stat.getTxPackets();
		} catch (Exception e) {
			throw new MeasurementNotAvailableException(e);
		}
	}

}
