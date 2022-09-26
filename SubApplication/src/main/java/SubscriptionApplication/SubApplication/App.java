package SubscriptionApplication.SubApplication;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import SubscriptionApplication.SubApplication.client.Client;

/**
 * JavaFX App
 */
public class App extends Application {
	Stage window;
	TableView<Client> table;
	TextField inputFName,inputLName;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		window = primaryStage;
		window.setTitle("SubscriptionApplicationDemo");

		//Name Columns
		
		//Column for the Clients First Name
		TableColumn<Client, String> firstNameColumn = new TableColumn<>("�����");
		firstNameColumn.setMinWidth(95);	
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		
		//Column for the Clients Last Name
		TableColumn<Client, String> lastNameColumn = new TableColumn<>("�������");
		lastNameColumn.setMinWidth(95);	
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		
		//Column for the Clients father first name
		TableColumn<Client, String> fatherFirstNameColumn = new TableColumn<>("����� ������");
		fatherFirstNameColumn.setMinWidth(115);	
		fatherFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("fatherFirstName"));
		
		//Column for the Clients address
		TableColumn<Client, String> addressColumn = new TableColumn<>("����");
		addressColumn.setMinWidth(95);	
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
		
		//Column for the Clients kind of Subscription
		TableColumn<Client, String> kindOfSubscriptionColumn = new TableColumn<>("����� ��������");
		kindOfSubscriptionColumn.setMinWidth(115);	
		kindOfSubscriptionColumn.setCellValueFactory(new PropertyValueFactory<>("kindOfSubscription"));
		
		//Column for the Clients kind of Exercise
		TableColumn<Client, String> kindOfExerciseColumn = new TableColumn<>("����� �������");
		kindOfExerciseColumn.setMinWidth(115);	
		kindOfExerciseColumn.setCellValueFactory(new PropertyValueFactory<>("kindOfExercise"));
		
		//Column for the Clients Zip Code
		TableColumn<Client, Integer> zipCodeColumn = new TableColumn<>("��");
		zipCodeColumn.setMinWidth(95);	
		zipCodeColumn.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
		
		//Column for the Clients Phone Number
		TableColumn<Client, Long> phoneNumberColumn = new TableColumn<>("��������");
		phoneNumberColumn.setMinWidth(95);	
		phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		
		//Column for the Clients Birth Day
		TableColumn<Client, Integer> birthDayColumn = new TableColumn<>("����� ��������");
		birthDayColumn.setMinWidth(115);	
		birthDayColumn.setCellValueFactory(new PropertyValueFactory<>("birthDay"));
		
		//Column for the Clients Birth Month
		TableColumn<Client, Integer> birthMonthColumn = new TableColumn<>("����� ��������");
		birthMonthColumn.setMinWidth(125);	
		birthMonthColumn.setCellValueFactory(new PropertyValueFactory<>("birthMonth"));
		
		//Column for the Clients Birth Year
		TableColumn<Client, Integer> birthYearColumn = new TableColumn<>("���� ��������");
		birthYearColumn.setMinWidth(115);	
		birthYearColumn.setCellValueFactory(new PropertyValueFactory<>("birthYear"));
		
		
		//First Name Input
		inputFName = new TextField();
		inputFName.setPromptText("First Name");
		inputFName.setMinWidth(100);
		

		//Last Name Input
		//lName = new TextField();
		//lName.setPromptText("Last Name");
		//lName.setMinWidth(100);
		
		
		
		//Buttons
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> addButtonClicked());
		Button deleteButton = new Button("Delete");
		
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(200,10,10,10));
		hBox.setSpacing(10);
		hBox.getChildren().addAll(inputFName, addButton, deleteButton);
		
		
		//Create table
		table = new TableView<>();
		table.setItems(getClient());
		
		//Add column to the Table
		table.getColumns().addAll(firstNameColumn,lastNameColumn,fatherFirstNameColumn,
				addressColumn,kindOfSubscriptionColumn,kindOfExerciseColumn,zipCodeColumn,phoneNumberColumn,birthDayColumn,birthMonthColumn,birthYearColumn);
		
		
		
		//Create a layer
		VBox vBox = new VBox();
		//Add the table into the layer
		vBox.getChildren().addAll(table,hBox);//put back in hBox later
		
		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.show();
		
		
	}
	
	//add button clicked
	
	public void addButtonClicked() {
		
		Client client = new Client();
		client.setFirstName(inputFName.getText());
		table.getItems().add(client);
		inputFName.clear();
		
		
	}
	
	
	//Gets the information of the Client
	public ObservableList<Client> getClient(){
		
		ObservableList<Client> clients = FXCollections.observableArrayList();
		clients.add(new Client("����������","��������","��������","�������� ������ 28","���������","���",67100,6944163467L, 4, 8 ,2000));
		clients.add(new Client("����������","�������","���������","������� ����������� 4 ","���������","Mai Thai",45221,6944163467L, 4, 8 ,2000));

		return clients;
	}

    public static void main(String[] args) {
        launch();
    }

}