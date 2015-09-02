package de.ustutt.omi.cloudiator.visor.sensors.net;

import org.junit.Before;
import org.junit.Test;

import de.uniulm.omi.cloudiator.visor.monitoring.DefaultMonitorContext;
import de.uniulm.omi.cloudiator.visor.monitoring.InvalidMonitorContextException;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementNotAvailableException;
import de.uniulm.omi.cloudiator.visor.monitoring.SensorInitializationException;
import de.uniulm.omi.cloudiator.visor.monitoring.DefaultMonitorContext.MonitorContextBuilder;
import de.ustutt.omi.cloudiator.visor.sensors.net.NetBandwidthSensor;

public class NetBandwidthSensorTest {

	private NetBandwidthSensor sensor;

	@Before
	public void setUp() throws SensorInitializationException {
		sensor = new NetBandwidthSensor();
		sensor.init();
	}

	@Test
	public void testEth0Traffic() 
			throws MeasurementNotAvailableException,
			       InvalidMonitorContextException,
			       InterruptedException {
		MonitorContextBuilder builder = DefaultMonitorContext.builder();
		builder.addContext("device", "eth0");
		builder.addContext("mode", "rx");
		builder.addContext("unit", "kb");

		sensor.setMonitorContext(builder.build());
		try {
			sensor.getMeasurement();
		} catch (Exception e) {
			// first measurement
		}
		Thread.sleep(2000);
		System.out.println(sensor.getMeasurement().getValue());
	}
}
