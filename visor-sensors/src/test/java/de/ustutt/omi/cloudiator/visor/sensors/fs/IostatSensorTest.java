package de.ustutt.omi.cloudiator.visor.sensors.fs;

import org.hyperic.sigar.SigarException;
import org.junit.Before;
import org.junit.Test;

import de.uniulm.omi.cloudiator.visor.monitoring.DefaultMonitorContext;
import de.uniulm.omi.cloudiator.visor.monitoring.InvalidMonitorContextException;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementNotAvailableException;
import de.uniulm.omi.cloudiator.visor.monitoring.SensorInitializationException;
import de.uniulm.omi.cloudiator.visor.monitoring.DefaultMonitorContext.MonitorContextBuilder;
import de.ustutt.omi.cloudiator.visor.sensors.fs.IostatSensor;

public class IostatSensorTest {

	private IostatSensor sensor;

	@Before
	public void setUp() throws SensorInitializationException {
		sensor = new IostatSensor();
		sensor.init();
	}

	@Test
	public void testDiskIoSensor() 
			throws InvalidMonitorContextException, 
				   MeasurementNotAvailableException,
				   SigarException {
		MonitorContextBuilder builder = DefaultMonitorContext.builder();
		builder.addContext("fs_device", "sda");
		builder.addContext("unit", "mb");
		builder.addContext("mode", "w");
		sensor.setMonitorContext(builder.build());

		System.out.println(sensor.getMeasurement().getValue());
	}

}
