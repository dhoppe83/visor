package de.ustutt.omi.cloudiator.visor.sensors.fs;

import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.NfsFileSystem;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;

import de.uniulm.omi.cloudiator.visor.monitoring.AbstractSensor;
import de.uniulm.omi.cloudiator.visor.monitoring.InvalidMonitorContextException;
import de.uniulm.omi.cloudiator.visor.monitoring.Measurement;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementImpl;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementNotAvailableException;
import de.uniulm.omi.cloudiator.visor.monitoring.MonitorContext;

public class NfsAccessSensor extends AbstractSensor {

	public static final String CONTEXT_NFS = "nfs_root";
	
	private String nfsRoot = null;

	@Override
	public void setMonitorContext(MonitorContext monitorContext) throws InvalidMonitorContextException {
		super.setMonitorContext(monitorContext);
		nfsRoot = monitorContext.getValue(CONTEXT_NFS);
	}
	
	@Override
	protected Measurement getMeasurement(MonitorContext monitorContext)
			throws MeasurementNotAvailableException {
		return new MeasurementImpl(System.currentTimeMillis(), checkNFSAvailability(nfsRoot));
	}

	public boolean checkNFSAvailability(String name) {
		try {
			SigarProxy proxy = SigarProxyCache.newInstance();
			FileSystem fs = proxy.getFileSystemMap().getFileSystem(name);
			if (fs instanceof NfsFileSystem) {
	            NfsFileSystem nfs = (NfsFileSystem) fs;
	            return nfs.ping();
	        }
		} catch (Exception e) {
			return false;
		}
		
		return false;
	}
}
