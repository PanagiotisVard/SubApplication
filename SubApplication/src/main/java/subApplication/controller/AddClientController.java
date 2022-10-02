package subApplication.controller;


import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import subApplication.dao.ClientDAO;
import subApplication.dao.ExercisesDAO;
import subApplication.dao.SubscriptionsDAO;
import subApplication.model.Client;

public class AddClientController implements Initializable {
	private ClientDAO dao;
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField fatherFirstName;
	@FXML
	private TextField address;
	@FXML
	private TextField zipCode;
	@FXML
	private TextField phoneNumber;
	@FXML
	private DatePicker birthDate;
	@FXML
	private ComboBox<String> kindOfSubscription;
	@FXML
	private CheckComboBox<String> kindOfExercise;
	@FXML
	private Button add;
	@FXML
	private Button cancel;
	
	

	public AddClientController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> kindOfExercises = FXCollections.observableArrayList(ExercisesDAO.getInstance().selectAll());
		ObservableList<String> kindOfSubscriptions = FXCollections.observableArrayList(SubscriptionsDAO.getInstance().selectAll());
		
		kindOfExercise.getItems().addAll(kindOfExercises);
		kindOfSubscription.setItems(kindOfSubscriptions);
		

		
		


	}
	
	@FXML
	public void addBtnHandler(ActionEvent event) {
		Stage stage = (Stage) add.getScene().getWindow();
		dao = ClientDAO.getInstance();
		String firstNameString = firstName.getText();
		String lastNameString = lastName.getText();
		String fatherFirstNameString = fatherFirstName.getText();
		String phoneNumberString = phoneNumber.getText();
		String zipCodeString = zipCode.getText();
		String addressString = address.getText();
		String kindOfSubscriptionString = kindOfSubscription.getValue();
		String kindOfExerciseString = ""; //kindOfExercise.getValue();
		String birthDateString = birthDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				
		int zipCodeInt = Integer.parseInt(zipCodeString);
		long phoneNumberLong = Long.parseLong(phoneNumberString);
		
		
		for (String exercises: kindOfExercise.getCheckModel().getCheckedItems()) {
			kindOfExerciseString += exercises + "\\";
		}
		kindOfExerciseString = kindOfExerciseString.substring(0, kindOfExerciseString.length()-1);

   
		
		
		Client newClient = new Client(firstNameString, lastNameString, fatherFirstNameString, addressString, zipCodeInt,
										kindOfSubscriptionString ,kindOfExerciseString , phoneNumberLong, birthDateString);
		
		dao.insert(newClient);
		
		stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
		stage.close();
					
	}
	
	@FXML 
	public void cancelBtnHandler() {
		Stage stage = (Stage) add.getScene().getWindow();
		stage.close();
	}
	

}
