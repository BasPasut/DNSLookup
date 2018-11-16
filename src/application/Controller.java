package application;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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

	List<Label> labels;

	Calculate cal = new Calculate();

	@FXML
	public void initialize() {
		getIP.setOnAction(this::getIP);
		getMyIP.setOnAction(this::getMyIP);
	}

	public void getIP(ActionEvent event) {
		clearTextArea();
		String hostname = host.getText().trim();
		hostnameShow.setText(hostname);
		List<String> listOfIP = new ArrayList<>();
		listOfIP.addAll(cal.getIP(hostname));
		int countServer = listOfIP.size();
		if (listOfIP.get(0).contains("Cannot")) {
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

	public void getMyIP(ActionEvent event) {
		clearTextArea();
		hostnameShow.setText("Localhost");
		result.appendText(cal.getIPofLocalHost());

	}
	
	public void clearTextArea(){
		result.clear();
	}
}