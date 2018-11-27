package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javafx.concurrent.Task;
/**
 * 
 * This class is the heart of the traceroute calucalating process.
 * 
 * @author Pasut Kittipapras
 * @author Theeruth Borisuth
 * @author Poorin Pitchayamongkol
 */
public class TraceRoute extends Task<String> {
	
	String hostname;
	
	/**
	 * Constructor of the traceroute class.
	 * @param hostname
	 */
	public TraceRoute(String hostname){
		this.hostname = hostname;
	}
	
	/**
	 * check whether the os is Window or Macos.
	 * @return
	 */
	public String trackOS() {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			return "tracert";
		} else if (os.contains("mac")) {
			return "traceroute";
		}
		else{
			return "traceroute";
		}
	}
	
	/**
	 * This method is auto generated from extending the task.
	 */
	@Override
	protected String call() throws Exception {
		String os = trackOS();

		BufferedReader in;
		String line = null;
		StringBuilder consoleContent = new StringBuilder();

		try {
			Runtime r = Runtime.getRuntime();
			Process p = r.exec(os+ " " + hostname);

			in = new BufferedReader(new InputStreamReader(p.getInputStream()));

			if (p == null)
				updateMessage("Cannot connect");

			while ((line = in.readLine()) != null) {
				consoleContent.append(line).append("\n");
				updateMessage(consoleContent.toString());
			}

		} catch (IOException e) {
			updateMessage(e.toString());
		}
		return line;
	}
}
