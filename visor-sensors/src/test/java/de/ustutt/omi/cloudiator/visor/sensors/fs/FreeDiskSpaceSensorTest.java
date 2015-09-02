package de.ustutt.omi.cloudiator.visor.sensors.fs;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementNotAvailableException;
import de.uniulm.omi.cloudiator.visor.monitoring.SensorInitializationException;
import de.ustutt.omi.cloudiator.visor.sensors.fs.FreeDiskSpaceSensor;

public class FreeDiskSpaceSensorTest {

	private FreeDiskSpaceSensor sensor;

	@Before
	public void setUp() throws SensorInitializationException {
		sensor = new FreeDiskSpaceSensor();
		sensor.init();
	}

	@Test
	public void testgetFileSystemUsage()
			throws MeasurementNotAvailableException {
		assertTrue("free disk space greather than 0",
				(double) sensor.getMeasurement().getValue() > 0);
	}
}
