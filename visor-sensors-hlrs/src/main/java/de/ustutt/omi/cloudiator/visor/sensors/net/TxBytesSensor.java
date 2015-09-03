package de.ustutt.omi.cloudiator.visor.sensors.net;

import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;

import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementNotAvailableException;
import de.ustutt.omi.cloudiator.visor.sensors.AbstractNetInterfaceSensor;

public class TxBytesSensor extends AbstractNetInterfaceSensor {

	@Override
	protected long getNetStat() throws MeasurementNotAvailableException {
		try {
			SigarProxy proxy = SigarProxyCache.newInstance();
			NetInterfaceStat stat = proxy.getNetInterfaceStat(device);
			return stat.getTxBytes();
		} catch (Exception e) {
			throw new MeasurementNotAvailableException(e);
		}
	}

}
