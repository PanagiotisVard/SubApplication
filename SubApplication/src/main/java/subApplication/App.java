package subApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
	

	@Override
	public void start(Stage primaryStage) throws Exception {
	
		
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("icon.jpg")));
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
	
	}
	
	
	

	


    public static void main(String[] args) {
    	
        launch();
    }

}