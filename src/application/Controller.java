package application;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

/**
 * @author Pasut Kittipapras
 * @author Theeruth Borisuth
 * @author Poorin Pitchayamongkol
 */
public class Controller {

	@FXML
	Button sol1;
	@FXML
	Button sol2;
	@FXML
	TextField host;
	@FXML
	Text hostnameShow;
	@FXML
	TextArea result;
	@FXML
	Menu tool;
	@FXML
	Text title;

	private DnsLookup cal = new DnsLookup();
	private TraceRoute trace = new TraceRoute();
	// variables
	private final ToolType DEFAULT_TYPE = ToolType.DNSLOOKUP;
	private ToolFactory factory = ToolFactory.getInstance();

	@FXML
	public void initialize() {
		result.setStyle("-fx-text-inner-color: white;");
		/** add Unit type into the menu **/
		for (ToolType ut : factory.getToolTypes()) {
			MenuItem currentItem = new MenuItem(ut.getName());
			currentItem.setOnAction(new EventListener(ut));
			tool.getItems().add(0, currentItem);
		}

		sol1.setOnAction(this::Tool1);
		sol2.setOnAction(this::Tool2);
	}

	private void Tool1(ActionEvent event) {
		clearTextArea();
		String hostname = host.getText().trim();
		hostnameShow.setText(hostname);
		List<String> listOfIP;
		int countServer = 0;
		if (title.getText().equalsIgnoreCase("dnslookup")) {
			listOfIP = new ArrayList<>(cal.getIP(hostname));
			countServer = listOfIP.size();
		} else {
			listOfIP = new ArrayList<>(trace.traceRoute(hostname));
		}
		if (listOfIP.get(0).contains("Cannot")) {
			// do nothing
		} else {
			listOfIP.clear();
			if (title.getText().equalsIgnoreCase("dnslookup")) {
				listOfIP.add("Number of Server: " + countServer + "\n");
				listOfIP.addAll(cal.getIP(hostname));
			} else {
				listOfIP.addAll(trace.traceRoute(hostname));
			}
		}
		appendTexttoResult(listOfIP);
	}

	private void Tool2(ActionEvent event) {
		clearTextArea();
		hostnameShow.setText("Localhost");
		if (title.getText().equalsIgnoreCase("dnslookup")) {
			result.appendText(cal.getIPofLocalHost());
		} else {
			List<String> traceOutput = new ArrayList<>();
			traceOutput.addAll(trace.traceRoute("localhost"));
			appendTexttoResult(traceOutput);
		}
	}

	private void appendTexttoResult(List<String> list){
		StringBuilder sb = new StringBuilder();
		for (String text : list) {

			if (sb.length() > 0) {
				sb.append("\n");
			}

			sb.append(text);
		}

		result.appendText(sb.toString());
	}
	
	private void clearTextArea() {
		result.clear();
	}

	/**
	 * this anonymous class is for setting up the comboBox
	 * 
	 * @author Theeruth Borisuth
	 *
	 */
	class EventListener implements EventHandler<ActionEvent> {

		// variables
		private ToolType type;

		// constructor
		public EventListener(ToolType units) {
			this.type = units;
		}

		/**
		 * A method for setting up comboBox.
		 */
		@Override
		public void handle(ActionEvent event) {
			if (type.getName().equalsIgnoreCase("dnslookup")) {
				clearTextArea();
				title.setText("DNSLookup");
				sol1.setText("Get Addresses");
				sol2.setText("Get My IP");
			} else if (type.getName().equalsIgnoreCase("traceroute")) {
				clearTextArea();
				title.setText("Trace Route");
				sol1.setText("Trace Host");
				sol2.setText("Trace My IP");

			}
		}
	}
}