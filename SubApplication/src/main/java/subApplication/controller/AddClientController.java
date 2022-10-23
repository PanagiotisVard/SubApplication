package subApplication.controller;


import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import subApplication.dao.ClientDAO;
import subApplication.dao.ExercisesDAO;
import subApplication.dao.SubscriptionsDAO;
import subApplication.model.Client;
import subApplication.model.Subscription;

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
	
	private Client toUpdateClient;
	private Long toUpdareClientPhoneNumber;
	private int payments;
	
	
	

	public AddClientController() {
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		payments = 0;
		dao = ClientDAO.getInstance();
		ObservableList<String> kindOfExercises = FXCollections.observableArrayList(ExercisesDAO.getInstance().selectAll());
		ObservableList<String> kindOfSubscriptionsStrings = FXCollections.observableArrayList();
		ArrayList<Subscription>  kindOfSubscriptions =  SubscriptionsDAO.getInstance().selectAll();
		
		
		// TODO
		for (Subscription subscription: kindOfSubscriptions) {
			System.out.println(subscription.getVisible());
			if (subscription.getVisible() == 1) {
				kindOfSubscriptionsStrings.add(subscription.getKindOfSubscription());
			}
			
		}
		
		kindOfExercise.getItems().addAll(kindOfExercises);
		kindOfSubscription.setItems(kindOfSubscriptionsStrings);
		Platform.runLater(() -> {
			toUpdareClientPhoneNumber = (Long) kindOfExercise.getScene().getWindow().getUserData();
			if (toUpdareClientPhoneNumber != null) {
				try {
					toUpdateClient = dao.selectByPhoneNumber(toUpdareClientPhoneNumber.longValue());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					new Alert(AlertType.ERROR, "Error! "+e.getMessage()).showAndWait();			

				}
				firstName.setText(toUpdateClient.getFirstName());
				lastName.setText(toUpdateClient.getLastName());
				fatherFirstName.setText(toUpdateClient.getFatherFirstName());
				address.setText(toUpdateClient.getAddress());
				zipCode.setText(toUpdateClient.getZipCode()+"");
				
				// Add old exercises to combobox
				ObservableList<String> oldExercises = FXCollections.observableArrayList();
				
				for(String exercise: toUpdateClient.getKindOfExercise().split("\\\\")) {
					if (!kindOfExercise.getItems().contains(exercise))
						oldExercises.add(exercise);
				}
				kindOfExercise.getItems().addAll(oldExercises);
				
				// Check old exercises to combobox
				for(String exercise: toUpdateClient.getKindOfExercise().split("\\\\")) {
					kindOfExercise.getCheckModel().check(exercise);
				} 
				
				phoneNumber.setText(toUpdateClient.getPhoneNumber()+"");
				kindOfSubscription.setValue(toUpdateClient.getKindOfSubscription());
				
				birthDate.setValue(LocalDate.parse(toUpdateClient.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				payments = toUpdateClient.getPayments();
				add.setText("Update");

				
			}

			
		});

		
		

		
		


	}
	
	@FXML
	public void addBtnHandler(ActionEvent event){
		Stage stage = (Stage) add.getScene().getWindow();
		DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		String firstNameString = firstName.getText();
		String lastNameString = lastName.getText();
		String fatherFirstNameString = fatherFirstName.getText();
		String phoneNumberString = phoneNumber.getText();
		String zipCodeString = zipCode.getText();
		String addressString = address.getText();
		String kindOfSubscriptionString = kindOfSubscription.getValue();
		String kindOfExerciseString = "";
		String birthDateString ;
		String created_at;
		
		int zipCodeInt;
		long phoneNumberLong;
		
		try {
			
			birthDateString = birthDate.getValue().format(dateTimeFormatter);
			created_at = dateTimeFormatter.format(LocalDateTime.now());
			
		}catch(NullPointerException e) {
			
			new Alert(AlertType.ERROR, "Error! No name"+ e.getMessage()).showAndWait();	
			return ;
			
		}
		
		try {
			
			zipCodeInt = Integer.parseInt(zipCodeString);
			phoneNumberLong = Long.parseLong(phoneNumberString);
			
		}catch(NumberFormatException e) {
			
			new Alert(AlertType.ERROR, "Error! No name"+ e.getMessage()).showAndWait();	
			return ;
		}
		
		
		if(kindOfSubscriptionString == null || kindOfSubscriptionString.isEmpty()) {
			new Alert(AlertType.ERROR, "Error! Subcription wrong").showAndWait();	
			return ; 
		}
		
		if(firstNameString.isEmpty() || lastNameString.isEmpty() || fatherFirstNameString.isEmpty()) {
			
			new Alert(AlertType.ERROR, "Error! No name").showAndWait();	
			return ;
		}
		
		
		for (String exercises: kindOfExercise.getCheckModel().getCheckedItems()) {
			kindOfExerciseString += exercises + "\\";
		}
		if (kindOfExerciseString.length() > 0)
			kindOfExerciseString = kindOfExerciseString.substring(0, kindOfExerciseString.length()-1);

		if(kindOfExerciseString.isEmpty()) {
			
			new Alert(AlertType.ERROR, "Error! Text Kind Of Exercise is empty").showAndWait();	
			
			return;
		}
		
		
		Client newClient = new Client(firstNameString, lastNameString, fatherFirstNameString, addressString, zipCodeInt,
										kindOfSubscriptionString ,kindOfExerciseString , phoneNumberLong, birthDateString, created_at, payments);
		
		try {
		
			if (add.getText().contains("Update")) {
				
				dao.update(toUpdareClientPhoneNumber, newClient);
					
			}else{
				
				dao.insert(newClient);	
				
			}
		}catch(SQLException sql) {
			
			new Alert(AlertType.ERROR, "Error! "+sql.getMessage()).showAndWait();			

			
		}
		
		stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
		stage.close();
		
		
	}
	
	@FXML 
	public void cancelBtnHandler() {
		Stage stage = (Stage) add.getScene().getWindow();
		stage.close();
	}
	
	
	 
	

}
