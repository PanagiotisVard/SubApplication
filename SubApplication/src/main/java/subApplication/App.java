package subApplication;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import subApplication.dao.ClientDAO;
import subApplication.dao.Database;
import subApplication.model.Client;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
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