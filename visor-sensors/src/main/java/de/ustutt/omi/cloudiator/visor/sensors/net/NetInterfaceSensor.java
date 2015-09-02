package de.ustutt.omi.cloudiator.visor.sensors.net;

import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarProxyCache;

import de.uniulm.omi.cloudiator.visor.monitoring.AbstractSensor;
import de.uniulm.omi.cloudiator.visor.monitoring.InvalidMonitorContextException;
import de.uniulm.omi.cloudiator.visor.monitoring.Measurement;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementImpl;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementNotAvailableException;
import de.uniulm.omi.cloudiator.visor.monitoring.MonitorContext;
import de.ustutt.omi.cloudiator.visor.sensors.SensorUtils;

public class NetInterfaceSensor extends AbstractSensor {

	public static final String CONTEXT_NET_DEVICE = "device";
	public static final String CONTEXT_NET_MODE = "mode";
	public static final String CONTEXT_NET_UNIT = "unit";

	protected double unit = SensorUtils.NET_DEFAULT_UNIT;
	protected String device = null;
	protected String mode = null;
	
	private static final String DEFAULT_NET_DEVICE = "eth0";
	private static final String DEFAULT_NET_MODE = "tx";
	private static final String DEFAULT_NET_UNIT = "kb";
	
	@Override
	public void setMonitorContext(MonitorContext monitorContext)
			throws InvalidMonitorContextException {
		super.setMonitorContext(monitorContext);
		device = monitorContext.getOrDefault(CONTEXT_NET_DEVICE, DEFAULT_NET_DEVICE);
		try {
			SigarProxyCache.newInstance().getNetInterfaceStat(device);
		} catch (SigarException e) {
			throw new InvalidMonitorContextException(
					new Error("Device (" + device + ") not available."));
		}
		mode = monitorContext.getOrDefault(CONTEXT_NET_MODE, DEFAULT_NET_MODE);
		mode = mode.toLowerCase();
		String strUnit = monitorContext.getOrDefault(CONTEXT_NET_UNIT, DEFAULT_NET_UNIT);
		unit = SensorUtils.parseContextNetUnit(strUnit);
	}

	@Override
	protected Measurement getMeasurement(MonitorContext monitorContext)
			throws MeasurementNotAvailableException {
		double val = getNetStat() / unit;
		val = Math.floor(val * 100) / 100;
		return new MeasurementImpl(System.currentTimeMillis(), val);
	}
	
	protected long getNetStat() throws MeasurementNotAvailableException {
		try {
			NetInterfaceStat stat = SigarProxyCache.newInstance().getNetInterfaceStat(device);
			if (mode.equals(SensorUtils.RX)) {
				return stat.getRxBytes();
			} else {
				return stat.getTxBytes();
			}
		} catch (Exception e) {
			throw new MeasurementNotAvailableException(e);
		}
	}
}
