package de.ustutt.omi.cloudiator.visor.sensors.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;

import de.uniulm.omi.cloudiator.visor.monitoring.AbstractSensor;
import de.uniulm.omi.cloudiator.visor.monitoring.InvalidMonitorContextException;
import de.uniulm.omi.cloudiator.visor.monitoring.Measurement;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementImpl;
import de.uniulm.omi.cloudiator.visor.monitoring.MeasurementNotAvailableException;
import de.uniulm.omi.cloudiator.visor.monitoring.MonitorContext;

public class LatencySensor extends AbstractSensor {

	public static final String CONTEXT_HOST = "host";
	public static final String CONTEXT_PORT = "port";
	public static final String CONTEXT_LOOPS = "loops";
	
	private static final String DEFAULT_HOST = "http://www.hlrs.de";
	private static final String DEFAULT_PORT = "80";
	private static final String DEFAULT_LOOPS = "1";

	private String host;
	private int port;
	private int loops;

	@Override
	public void setMonitorContext(MonitorContext monitorContext)
			throws InvalidMonitorContextException {
		super.setMonitorContext(monitorContext);
		host = monitorContext.getOrDefault(CONTEXT_HOST, DEFAULT_HOST);
		String strLoops = monitorContext.getOrDefault(CONTEXT_LOOPS, DEFAULT_LOOPS);
		String strPort = monitorContext.getOrDefault(CONTEXT_PORT, DEFAULT_PORT);
		try {
			port = Integer.parseInt(strPort);
		} catch (NumberFormatException nfe) {
			throw new InvalidMonitorContextException(
					new Error("Port (" + strPort + ") is invalid."));
		}
		try {
			loops = Integer.parseInt(strLoops);
		} catch (NumberFormatException nfe) {
			throw new InvalidMonitorContextException(
					new Error("Given loops (" + strLoops + ") are not a number."));
		}
		checkURL(host, port);
	}

	private void checkURL(String host, int port)
			throws InvalidMonitorContextException {
		SocketAddress sockaddr = new InetSocketAddress(host, port);
		Socket socket = new Socket();
		try {
			socket.connect(sockaddr, 500);
		} catch (SocketTimeoutException stex) {
			throw new InvalidMonitorContextException(stex);
		} catch (IOException ioe) {
			throw new InvalidMonitorContextException(ioe);
		} finally {
			try {
				socket.close();
			} catch (IOException ex) {
				throw new InvalidMonitorContextException(ex);
			}
		}
	}

	@Override
	protected Measurement getMeasurement(MonitorContext monitorContext)
			throws MeasurementNotAvailableException {
		return new MeasurementImpl(System.currentTimeMillis(), computeAverageLatency());
	}
	
	private double computeAverageLatency() {
		double latency = 0.0;
		
		Socket socket = null;
		SocketAddress address = new InetSocketAddress(host, port);
		for (int i = 0; i != loops; ++i) {
			long start = System.currentTimeMillis();
			socket = new Socket();
			try {
				socket.connect(address);
				socket.close();
			} catch (IOException e) {
				continue;
			}
			long end = System.currentTimeMillis();
			latency = latency + (end - start);
		}
		double average = latency / loops;
		return Math.floor(average * 100) / 100;
	}

}
