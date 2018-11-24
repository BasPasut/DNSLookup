package application;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Font;
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
	private Task<String> trace;
	// variables
	private final ToolType DEFAULT_TYPE = ToolType.DNSLOOKUP;
	private ToolFactory factory = ToolFactory.getInstance();

	@FXML
	public void initialize() {
		Font.loadFont(getClass().getResourceAsStream("/fonts/big_noodle_titling_oblique.ttf"), 14);
		Font.loadFont(getClass().getResourceAsStream("/fonts/big_noodle_titling.ttf"), 14);
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
		int countServer = 0;
		List<String> listOfIP = new ArrayList<>(cal.getIP(hostname));;
		if (title.getText().equalsIgnoreCase("dnslookup")) {
			countServer = listOfIP.size();
		} else {
			trace = new TraceRoute(hostname);
			ExecutorService executor = Executors.newFixedThreadPool(2);
			result.textProperty().bind(trace.messageProperty());
			executor.execute(trace);
			executor.shutdown();
		}
		if (listOfIP.get(0).contains("Cannot")) {
			// do nothing
		} else {
			listOfIP.clear();
			if (title.getText().equalsIgnoreCase("dnslookup")) {
				listOfIP.add("Hostname: "+ hostname);
				listOfIP.add("Number of Server: " + countServer + "\n");
				listOfIP.addAll(cal.getIP(hostname));
				appendTexttoResult(listOfIP);
			}
		}
	}

	private void Tool2(ActionEvent event) {
		clearTextArea();
		hostnameShow.setText("Localhost");
		trace = new TraceRoute("Localhost");
		if (title.getText().equalsIgnoreCase("dnslookup")) {
			result.appendText(cal.getIPofLocalHost());
		} else {
			ExecutorService executor = Executors.newFixedThreadPool(2);
			result.textProperty().bind(trace.messageProperty());
			executor.execute(trace);
			executor.shutdown();
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
			clearTextArea();
			if (type.getName().equalsIgnoreCase("dnslookup")) {
				result.textProperty().unbind();
				title.setText("DNSLookup");
				sol1.setText("Get Addresses");
				sol2.setText("Get My IP");
			} if (type.getName().equalsIgnoreCase("traceroute")) {
				title.setText("Trace Route");
				sol1.setText("Trace Host");
				sol2.setText("Trace My IP");
			}
		}
	}
}