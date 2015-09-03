/**
 * 
 */
package de.ustutt.omi.cloudiator.visor.sensors.net;

import org.hyperic.sigar.SigarException;
import org.junit.Before;
import org.junit.Test;

import de.uniulm.omi.cloudiator.visor.monitoring.DefaultMonitorContext;
import de.uniulm.omi.cloudiator.visor.monitoring.DefaultMonitorContext.MonitorContextBuilder;
import de.uniulm.omi.cloudiator.visor.monitoring.InvalidMonitorContextException;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementNotAvailableException;
import de.uniulm.omi.cloudiator.visor.monitoring.SensorInitializationException;

/**
 * @author hopped
 *
 */
public class RxBytesSensorTest {

	private RxBytesSensor sensor;
	
	@Before
    public void setUp() throws SensorInitializationException {
		sensor = new RxBytesSensor();
		sensor.init();
    }
	
	@Test
	public void testRxBytesOnEth0() 
			throws MeasurementNotAvailableException,
			       InvalidMonitorContextException,
			       SigarException {
		MonitorContextBuilder builder = DefaultMonitorContext.builder();
		builder.addContext("device", "eth0");
		builder.addContext("unit", "mb");
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
	public void testMissingDevice() 
			throws MeasurementNotAvailableException, 
			       InvalidMonitorContextException {
		MonitorContextBuilder builder = DefaultMonitorContext.builder();
		builder.addContext("unit", "mb");
		sensor.setMonitorContext(builder.build());
		
		System.out.println(sensor.getMeasurement().getValue());
	}
	
	@Test
	public void testMissingUnit() 
			throws MeasurementNotAvailableException, 
			       InvalidMonitorContextException {
		MonitorContextBuilder builder = DefaultMonitorContext.builder();
		builder.addContext("device", "eth0");
		sensor.setMonitorContext(builder.build());
		
		System.out.println(sensor.getMeasurement().getValue());
	}
	
	@Test(expected = InvalidMonitorContextException.class)
	public void testWrongDevice() 
			throws MeasurementNotAvailableException, 
			       InvalidMonitorContextException {
		MonitorContextBuilder builder = DefaultMonitorContext.builder();
		builder.addContext("device", "foobar");
		sensor.setMonitorContext(builder.build());
		
		System.out.println(sensor.getMeasurement().getValue());
	}
	
	@Test
	public void testWrongUnit() 
			throws MeasurementNotAvailableException, 
			       InvalidMonitorContextException {
		MonitorContextBuilder builder = DefaultMonitorContext.builder();
		builder.addContext("unit", "foobar");
		sensor.setMonitorContext(builder.build());
		
		System.out.println(sensor.getMeasurement().getValue());
	}
	
}
