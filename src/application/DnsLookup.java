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
 * This class is used to calculated the dnslookup tool.
 * 
 * @author Pasut Kittipapras
 * @author Theeruth Borisuth
 * @author Poorin Pitchayamongkol
 */
public class DnsLookup {

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
}
