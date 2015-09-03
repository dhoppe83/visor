package de.ustutt.omi.cloudiator.visor.sensors.fs;

import org.hyperic.sigar.DiskUsage;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;

import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementNotAvailableException;
import de.ustutt.omi.cloudiator.visor.sensors.AbstractDiskIoSensor;

public class DiskIoReadSensor extends AbstractDiskIoSensor {

	public double getUsage(final String name, final double unit) 
			throws MeasurementNotAvailableException {
		try {
			SigarProxy proxy = SigarProxyCache.newInstance();
			DiskUsage du = proxy.getDiskUsage(name);
			double dataPerSecond = du.getReads() / unit;
			return Math.floor(dataPerSecond * 100) / 100;
		} catch (Exception e) {
			throw new MeasurementNotAvailableException(e);
		}
	}

}
