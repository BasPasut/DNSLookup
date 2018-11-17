package application;
	
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
/**
 * This is a main class that will initiate the program.
 *
 * @author Pasut Kittipapras
 * @author Theeruth Borisuth
 * @author Poorin Pitchayamongkol
 */

public class Main extends Application {
	/**
	 * Launch the program
	 * @param primaryStage
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("UI.fxml"));
			Scene scene = new Scene(root);
			setUserAgentStylesheet(STYLESHEET_MODENA);
			primaryStage.getIcons().add(new Image("file:icon.png"));
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.show();
			primaryStage.setTitle("DNSLookUP");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
