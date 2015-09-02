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
import de.ustutt.omi.cloudiator.visor.sensors.net.NetInterfaceSensor;

/**
 * @author hopped
 *
 */
public class NetInterfaceSensorTest {

	private NetInterfaceSensor sensor;
	
	@Before
    public void setUp() throws SensorInitializationException {
		sensor = new NetInterfaceSensor();
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
		builder.addContext("unit", "mb");
		sensor.setMonitorContext(builder.build());

		while (true) {
			System.out.println(sensor.getMeasurement().getValue());
			Thread.sleep(1000);
		}
	}
}
