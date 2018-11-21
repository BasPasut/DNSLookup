package application;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
	MenuItem dns;
	@FXML
	MenuItem trace;

	private Calculate cal = new Calculate();

	@FXML
	public void initialize() {
		getIP.setOnAction(this::getIP);
		getMyIP.setOnAction(this::getMyIP);
	}

	private void getIP(ActionEvent event) {
		clearTextArea();
		String hostname = host.getText().trim();
//		if(!hostname.contains("www.") && !hostname.contains(".com")) {
//			hostname = "www."+hostname+".com";
//		}
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
	
//	private void traceRoute(ActionEvent event){
//		
//	}

	
	private void clearTextArea(){
		result.clear();
	}
}