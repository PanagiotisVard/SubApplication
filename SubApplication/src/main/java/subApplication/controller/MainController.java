package subApplication.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import subApplication.dao.ClientDAO;
import subApplication.model.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;



public class MainController implements Initializable {

		@FXML
		private TableView<Client> clientsTableview;
		
		@FXML
		private TableColumn<Client, String> firstName;
		@FXML
		private TableColumn<Client, String> lastName;
		@FXML
		private TableColumn<Client, String> fatherFirstName;
		@FXML
		private TableColumn<Client, String> address;
		@FXML
		private TableColumn<Client, String> kindOfSubscription;
		@FXML
		private TableColumn<Client, String> kindOfExercise;
		@FXML
		private TableColumn<Client, String> birthDate;
		@FXML
		private TableColumn<Client, Integer> zipCode;
		@FXML
		private TableColumn<Client, Long> phoneNumber;
		
		private ClientDAO dao;

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			populate();
			
		}
		

		@FXML	
		public void populate() {
			dao = new ClientDAO();
			ObservableList<Client> clients = FXCollections.observableArrayList(dao.selectAll());
			
			
			firstName.setCellValueFactory( new PropertyValueFactory<Client, String>("firstName"));
			lastName.setCellValueFactory(new PropertyValueFactory<Client, String>("LastName"));
			fatherFirstName.setCellValueFactory(new PropertyValueFactory<Client, String>("FatherFirstName"));
			kindOfSubscription.setCellValueFactory(new PropertyValueFactory<Client, String>("kindOfSubscription"));
			kindOfExercise.setCellValueFactory(new PropertyValueFactory<Client, String>("kindOfExercise"));
			address.setCellValueFactory(new PropertyValueFactory<Client, String>("address"));
			birthDate.setCellValueFactory(new PropertyValueFactory<Client, String>("birthDate"));
			zipCode.setCellValueFactory(new PropertyValueFactory<Client, Integer>("zipCode"));
			phoneNumber.setCellValueFactory(new PropertyValueFactory<Client, Long>("phoneNumber"));
			
			clientsTableview.setItems(clients);
			
		}
		
		@FXML
		public void addBtnHandler() {
			 try {
			        FXMLLoader fxmlLoader = new FXMLLoader();
			        fxmlLoader.setLocation(getClass().getClassLoader().getResource("addNewClient.fxml"));
			        /* 
			         * if "fx:controller" is not set in fxml
			         * fxmlLoader.setController(NewWindowController);
			         */
			        Scene scene = new Scene(fxmlLoader.load());
			        Stage stage = new Stage();
//			        stage.setTitle("New Window");
			        stage.setScene(scene);
			        stage.show();
			    } catch (IOException e) {
			        
			    }
			
		}

		

		

}
