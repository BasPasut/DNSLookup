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
	Button getIP;
	@FXML
	Button getMyIP;
	@FXML
	TextField host;
	@FXML
	Text hostnameShow;
	@FXML
	TextArea result;
	@FXML
	Menu tool;

	private DnsLookup cal = new DnsLookup();
	//variables
	private final ToolType  DEFAULT_TYPE = ToolType.DNSLOOKUP;
	private ToolFactory factory = ToolFactory.getInstance();

	@FXML
	public void initialize() {
		/**add Unit type into the menu**/
		for (ToolType ut : factory.getToolTypes()){
			MenuItem currentItem = new MenuItem(ut.getName());
			currentItem.setOnAction(new EventListener(ut));
			tool.getItems().add(0,currentItem);
		}

		getIP.setOnAction(this::getIP);
		getMyIP.setOnAction(this::getMyIP);
	}

	private void getIP(ActionEvent event) {
		clearTextArea();
		String hostname = host.getText().trim();
		hostnameShow.setText(hostname);
		List<String> listOfIP = new ArrayList<>(cal.getIP(hostname));
		int countServer = listOfIP.size();
		if (listOfIP.get(0).contains("Cannot")) {
			//do nothing
		} else {
			listOfIP.clear();
			listOfIP.add("Hostname: " + hostname);
			listOfIP.add("Number of Server: " + countServer + "\n");
			listOfIP.addAll(cal.getIP(hostname));
		}
		StringBuilder sb = new StringBuilder();
		for (String text : listOfIP) {

			if (sb.length() > 0) {
				sb.append("\n");
			}

			sb.append(text);
		}

		result.appendText(sb.toString());
	}

	private void getMyIP(ActionEvent event) {
		clearTextArea();
		hostnameShow.setText("Localhost");
		result.appendText(cal.getIPofLocalHost());

	}
	
	private void traceRoute(ActionEvent event){

	}

	
	private void clearTextArea(){
		result.clear();
	}

	/**
	 * this anonymous class is for setting up the comboBox
	 * @author Theeruth Borisuth
	 *
	 */
	class EventListener implements EventHandler<ActionEvent> {

		//variables
		private ToolType type ;

		//constructor
		public EventListener(ToolType units) {
			this.type = units ;
		}

		/**
		 * A method for setting up comboBox.
		 */
		@Override
		public void handle(ActionEvent event) {

		}
	}
}