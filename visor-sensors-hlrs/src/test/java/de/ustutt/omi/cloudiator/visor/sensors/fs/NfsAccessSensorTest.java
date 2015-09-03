package de.ustutt.omi.cloudiator.visor.sensors.fs;

import org.hyperic.sigar.SigarException;
import org.junit.Before;
import org.junit.Test;

import de.uniulm.omi.cloudiator.visor.monitoring.DefaultMonitorContext;
import de.uniulm.omi.cloudiator.visor.monitoring.InvalidMonitorContextException;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementNotAvailableException;
import de.uniulm.omi.cloudiator.visor.monitoring.SensorInitializationException;
import de.uniulm.omi.cloudiator.visor.monitoring.DefaultMonitorContext.MonitorContextBuilder;
import de.ustutt.omi.cloudiator.visor.sensors.fs.NfsAccessSensor;

public class NfsAccessSensorTest {

	private NfsAccessSensor sensor;

	@Before
	public void setUp() throws SensorInitializationException {
		sensor = new NfsAccessSensor();
		sensor.init();
	}
	
	@Test
	public void testNfsAccessibility() 
			throws InvalidMonitorContextException, 
				   MeasurementNotAvailableException,
				   SigarException {
		MonitorContextBuilder builder = DefaultMonitorContext.builder();
		builder.addContext("nfs_root", "/media/nfs");
		sensor.setMonitorContext(builder.build());

		System.out.println(sensor.getMeasurement().getValue());
	}
	
	@Test
	public void testEmptyMonitorContext() 
			throws MeasurementNotAvailableException,
			       InvalidMonitorContextException {
		MonitorContextBuilder builder = DefaultMonitorContext.builder();
		sensor.setMonitorContext(builder.build());
		
		System.out.println(sensor.getMeasurement().getValue());
	}

	@Test
	public void testMissingNfsRoot() 
			throws MeasurementNotAvailableException, 
			       InvalidMonitorContextException {
		MonitorContextBuilder builder = DefaultMonitorContext.builder();
		sensor.setMonitorContext(builder.build());
		
		System.out.println(sensor.getMeasurement().getValue());
	}
	
	@Test
	public void testWrongNfsRoot() 
			throws MeasurementNotAvailableException, 
			       InvalidMonitorContextException {
		MonitorContextBuilder builder = DefaultMonitorContext.builder();
		builder.addContext("nfs_root", "foobar");
		sensor.setMonitorContext(builder.build());
		
		System.out.println(sensor.getMeasurement().getValue());
	}

}
