package de.ustutt.omi.cloudiator.visor.sensors.net;

import de.uniulm.omi.cloudiator.visor.monitoring.Measurement;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementImpl;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementNotAvailableException;
import de.uniulm.omi.cloudiator.visor.monitoring.MonitorContext;

public class DownloadBandwidthSensor extends RxBytesSensor {

	private long previousVal = 0;
	private long previousTime = 0;
	
	@Override
	protected Measurement getMeasurement(MonitorContext monitorContext)
			throws MeasurementNotAvailableException {
		if (previousVal == 0) {
			previousVal = getNetStat();
			previousTime = System.currentTimeMillis();
			throw new MeasurementNotAvailableException(
					new Error("No data collected"));
		}
		long currentVal = getNetStat();
		long currentTime = System.currentTimeMillis();
		
		double data = currentVal - previousVal;
		double seconds = (currentTime - previousTime) / 1000.0;
		
		previousVal = currentVal;
		previousTime = currentTime;
		
		data = data / unit / seconds;
		data = Math.floor(data * 100.0) / 100.0;
		
		return new MeasurementImpl(System.currentTimeMillis(), data);
	}

}
