package de.ustutt.omi.cloudiator.visor.sensors;

import de.uniulm.omi.cloudiator.visor.monitoring.AbstractSensor;
import de.uniulm.omi.cloudiator.visor.monitoring.InvalidMonitorContextException;
import de.uniulm.omi.cloudiator.visor.monitoring.Measurement;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementImpl;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementNotAvailableException;
import de.uniulm.omi.cloudiator.visor.monitoring.MonitorContext;

/**
 * @author hopped
 *
 */
public abstract class AbstractDiskIoSensor extends AbstractSensor {

	public static final String CONTEXT_DEVICE = "fs_device";
	public static final String CONTEXT_UNIT = "unit";

	private final String defaultDevice = SensorUtils.getDefaultFsDevice();
	private double unit = SensorUtils.FS_DEFAULT_UNIT;
	private String ioDevice = null;
	
	@Override
	public void setMonitorContext(MonitorContext monitorContext)
			throws InvalidMonitorContextException {
		super.setMonitorContext(monitorContext);
		ioDevice = monitorContext.getOrDefault(CONTEXT_DEVICE, defaultDevice);
		String ctx = monitorContext.getValue(CONTEXT_UNIT);
		unit = SensorUtils.parseContextUnit(ctx);
	}

	@Override
	protected Measurement getMeasurement(MonitorContext monitorContext)
			throws MeasurementNotAvailableException {
		double usage = getUsage(ioDevice, unit);
		return new MeasurementImpl(System.currentTimeMillis(), usage);
	}

	public abstract double getUsage(final String name, final double unit)
			throws MeasurementNotAvailableException;

}
