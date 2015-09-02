package de.ustutt.omi.cloudiator.visor.sensors.fs;

import org.hyperic.sigar.DiskUsage;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;

import de.uniulm.omi.cloudiator.visor.monitoring.AbstractSensor;
import de.uniulm.omi.cloudiator.visor.monitoring.InvalidMonitorContextException;
import de.uniulm.omi.cloudiator.visor.monitoring.Measurement;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementImpl;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementNotAvailableException;
import de.uniulm.omi.cloudiator.visor.monitoring.MonitorContext;
import de.ustutt.omi.cloudiator.visor.sensors.SensorUtils;

public class IostatSensor extends AbstractSensor {

	public static final String CONTEXT_DEVICE = "fs_device";
	public static final String CONTEXT_UNIT = "unit";
	public static final String CONTEXT_MODE = "mode";

	private final String defaultIoDevice = SensorUtils.getDefaultFsDevice();
	private double unit = SensorUtils.FS_DEFAULT_UNIT;
	private String ioDevice = null;
	private String mode = null;
	
	@Override
	public void setMonitorContext(MonitorContext monitorContext) throws InvalidMonitorContextException {
		super.setMonitorContext(monitorContext);
		ioDevice = monitorContext.getOrDefault(CONTEXT_DEVICE, defaultIoDevice);
		mode = monitorContext.getOrDefault(CONTEXT_MODE, SensorUtils.IO_DEFAULT_MODE);
		unit = SensorUtils.parseContextUnit(monitorContext.getValue(CONTEXT_UNIT));
	}

	@Override
	protected Measurement getMeasurement(MonitorContext monitorContext)
			throws MeasurementNotAvailableException {
		return new MeasurementImpl(System.currentTimeMillis(), getUsage(ioDevice, unit));
	}

	public double getUsage(String name, double size)
			throws MeasurementNotAvailableException {
		try {
			SigarProxy proxy = SigarProxyCache.newInstance();
			DiskUsage du = proxy.getDiskUsage(ioDevice);
			
			double dataPerSecond = 0.0;
			if (mode.equals(SensorUtils.READS)) {
				dataPerSecond = du.getReads() / size;
			} else {
				dataPerSecond = du.getWrites() / size;
			}
			return Math.floor(dataPerSecond * 100) / 100;
		} catch (Exception e) {
			throw new MeasurementNotAvailableException(e);
		}
	}


}
