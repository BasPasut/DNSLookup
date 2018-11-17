package application;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 *Get IP address of the specific address or check your own local host.
 *
 * @author Pasut Kittipapras
 * @author Theeruth Borisuth
 * @author Poorin Pitchayamongkol
 */
public class Calculate {

	/**
	 * This method will get the ip by using InetAddress package. Catch exception if the it cannot find.
	 * @param host : ip of the host
	 * @return list of ip address
	 */
	public List<String> getIP(String host) {
		isValid(host);
		InetAddress[] ip;
		List<String> listOfIP = new ArrayList<>();
		try {
			ip = InetAddress.getAllByName(host);
			for (InetAddress i: ip){
				String hostAddress = i.getHostAddress();
				listOfIP.add(">> " +hostAddress+"\n     "+"Ping RTT: " + pingCheckerIP(hostAddress) + " ms.");
			}
		} catch (UnknownHostException e) {
			listOfIP.add("Cannot find " + host);
		}
		return listOfIP;
	}

	/**
	 * Check whether the string is a valid url or not.
	 * @param url : specific url
	 * @return true if it's valid else it's will be false.
	 */
	public static boolean isValid(String url)
	{

		url = "http://"+url;
		/* Try creating a valid URL */
		try {
			new URL(url).toURI();
			return true;
		}

		// If there was an Exception
		// while creating URL object
		catch (Exception e) {
			return false;
		}
	}

	/**
	 * Get the IP address of the local host. catch exception if it cannot find the local host.
	 * @return user's IP
	 */
	public String getIPofLocalHost() {
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			String myIP = ">> Your IP: "+ip.getHostAddress() +"\n";
			return myIP;
		} catch (UnknownHostException e) {
			return "Cannot find localhost";
		}
	}

	/**
	 * Check the latency of that IP address with given 5 seconds. If it's pass 5 seconds and it still not return the ping.
	 * That's mean its unreachable.
	 * @param ip : user specific IP.
	 * @return
	 */
	public long pingCheckerIP(String ip) {
		long finish ;
		long ping;

		try {
		      String ipAddress = ip;
		      InetAddress iNet = InetAddress.getByName(ipAddress);
		      

		      long start = new GregorianCalendar().getTimeInMillis();
		 
		      if (iNet.isReachable(5000)){
		        finish = new GregorianCalendar().getTimeInMillis();
				  ping = finish - start;
				  System.out.println(ping);
				  return ping;
		      } else {
		        System.out.println(ipAddress + " NOT reachable.");
		      }
		    } catch ( Exception e ) {
		      System.out.println("Exception:" + e.getMessage());
		    }
		return 0;
	}

}
