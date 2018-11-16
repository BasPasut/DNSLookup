package application;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Calculate {

	public List<String> getIP(String host) {
		InetAddress[] ip;
		List<String> listOfIP = new ArrayList<String>();
		try {
			ip = InetAddress.getAllByName(host);
			for (int i = 0; i < ip.length; i++) {
				String hostAddress = ip[i].getHostAddress();
				listOfIP.add(">> " +hostAddress+"\n     "+"Ping RTT: " + pingCheckerIP(hostAddress) + " ms.");
			}
		} catch (UnknownHostException e) {
			listOfIP.add("Cannot find " + host);
		}
		return listOfIP;
	}

	public String getIPofLocalHost() {
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			String myIP = ">> "+ip.getHostAddress() +"\n";
			return myIP;
		} catch (UnknownHostException e) {
			return "Cannot find localhost";
		}
	}

	public long pingCheckerIP(String ip) {
		try {
		      String ipAddress = ip;
		      InetAddress inet = InetAddress.getByName(ipAddress);
		      
		      long finish = 0;
		      long start = new GregorianCalendar().getTimeInMillis();
		 
		      if (inet.isReachable(5000)){
		        finish = new GregorianCalendar().getTimeInMillis();
		        long ping = finish - start;
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
