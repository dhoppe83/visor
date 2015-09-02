package de.ustutt.omi.cloudiator.visor.sensors.net;

import org.hyperic.sigar.SigarException;
import org.junit.Before;
import org.junit.Test;

import de.uniulm.omi.cloudiator.visor.monitoring.DefaultMonitorContext;
import de.uniulm.omi.cloudiator.visor.monitoring.InvalidMonitorContextException;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementNotAvailableException;
import de.uniulm.omi.cloudiator.visor.monitoring.SensorInitializationException;
import de.uniulm.omi.cloudiator.visor.monitoring.DefaultMonitorContext.MonitorContextBuilder;

public class NetPacketSensorTest {

	private NetPacketSensor sensor;
	
	@Before
    public void setUp() throws SensorInitializationException {
		sensor = new NetPacketSensor();
		sensor.init();
    }
	
	@Test
	public void testNetInterfaceEth0() 
			throws MeasurementNotAvailableException,
			       InvalidMonitorContextException, 
			       InterruptedException, 
			       SigarException {
		MonitorContextBuilder builder = DefaultMonitorContext.builder();
		builder.addContext("device", "eth0");
		builder.addContext("mode", "rx");
		sensor.setMonitorContext(builder.build());

		while (true) {
			System.out.println(sensor.getMeasurement().getValue());
			Thread.sleep(1000);
		}
	}
	
}
