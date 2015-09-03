package de.ustutt.omi.cloudiator.visor.sensors.net;

import org.hyperic.sigar.SigarException;
import org.junit.Before;
import org.junit.Test;

import de.uniulm.omi.cloudiator.visor.monitoring.DefaultMonitorContext;
import de.uniulm.omi.cloudiator.visor.monitoring.InvalidMonitorContextException;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementNotAvailableException;
import de.uniulm.omi.cloudiator.visor.monitoring.SensorInitializationException;
import de.uniulm.omi.cloudiator.visor.monitoring.DefaultMonitorContext.MonitorContextBuilder;

public class RxPacketsSensorTest {

	private RxPacketsSensor sensor;
	
	@Before
    public void setUp() throws SensorInitializationException {
		sensor = new RxPacketsSensor();
		sensor.init();
    }
	
	@Test
	public void testNetInterfaceEth0() 
			throws MeasurementNotAvailableException,
			       InvalidMonitorContextException, 
			       SigarException {
		MonitorContextBuilder builder = DefaultMonitorContext.builder();
		builder.addContext("device", "eth0");
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
	
	@Test(expected = InvalidMonitorContextException.class)
	public void testWrongDevice() 
			throws MeasurementNotAvailableException, 
			       InvalidMonitorContextException {
		MonitorContextBuilder builder = DefaultMonitorContext.builder();
		builder.addContext("device", "foobar");
		sensor.setMonitorContext(builder.build());
		
		System.out.println(sensor.getMeasurement().getValue());
	}
	
}
