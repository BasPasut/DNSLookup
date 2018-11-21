package application;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.io.*;
import java.net.*;

/**
 *
 *
 * @author Pasut Kittipapras
 * @author Theeruth Borisuth
 * @author Poorin Pitchayamongkol
 */
public class Calculate {

	/**
	 * This method will get the ip by using InetAddress package. Catch exception
	 * if the it cannot find.
	 * 
	 * @param host
	 * @return list of ip address
	 */
	public List<String> getIP(String host) {
		InetAddress[] ip;
		List<String> listOfIP = new ArrayList<>();
		try {
			ip = InetAddress.getAllByName(host);
			for (InetAddress i : ip) {
				String hostAddress = i.getHostAddress();
				listOfIP.add(">> " + hostAddress + "\n     ");
				// +"Ping RTT: " + pingCheckerIP(hostAddress) + " ms."
			}
		} catch (UnknownHostException e) {
			listOfIP.add("Cannot find " + host);
		}
		return listOfIP;
	}

	/**
	 * Get the IP address of the local host. catch exception if it cannot find
	 * the local host.
	 * 
	 * @return
	 */
	public String getIPofLocalHost() {
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			String myIP = ">> Your IP: " + ip.getHostAddress() + "\n";
			return myIP;
		} catch (UnknownHostException e) {
			return "Cannot find localhost";
		}
	}

	/**
	 * Check the latency of that IP address with given 5 seconds. If it's pass 5
	 * seconds and it still not return the ping. That's mean its unreachable.
	 * 
	 * @param ip
	 * @return
	 */
	public long pingCheckerIP(String ip) {
		long finish;
		long ping;

		try {
			String ipAddress = ip;
			InetAddress inet = InetAddress.getByName(ipAddress);

			long start = new GregorianCalendar().getTimeInMillis();

			if (inet.isReachable(5000)) {
				finish = new GregorianCalendar().getTimeInMillis();
				ping = finish - start;
				return ping;
			} else {
				System.out.println(ipAddress + " NOT reachable.");
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		}
		return 0;
	}

	public String trackOS(){
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")){
		    return "tracert";
		}
		else if (os.contains("osx")){
		    return "trace";
		}
		return null;
	}
	
	public List<String> traceRoute(String host) {
		List<String> output = new ArrayList<>();
		BufferedReader in;

		try {
			Runtime r = Runtime.getRuntime();
			String os = trackOS();
			Process p = r.exec(os+" "+host);

			in = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line;

			if (p == null)
				output.add("Cannot connect");

			while ((line = in.readLine()) != null) {

				output.add(line);
			}

		} catch (IOException e) {

			System.out.println(e.toString());

		}
		return output;
	}
}
