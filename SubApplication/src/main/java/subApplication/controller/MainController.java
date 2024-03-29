package subApplication.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import subApplication.dao.ClientDAO;
import subApplication.dao.SubscriptionsDAO;
import subApplication.model.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



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
		@FXML
		private TableColumn<Client, String> expireOfSubscription;
		@FXML
		private TableColumn<Client,String> debt;
		@FXML
		private TextField searchField;
		
		
		
		private ClientDAO dao;

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			dao = ClientDAO.getInstance();
			
			try {
				populate(dao.selectAll());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				new Alert(AlertType.ERROR, "Error! "+e.getMessage()).showAndWait();	
			}
			
		}
		
		//TODO ADD final to try catch later
		
		private void populate(ArrayList<Client> clientsArrayList ) {
			if (clientsArrayList.contains(null)) {
				throw new NullPointerException();
			}
			
			ObservableList<Client> clients = FXCollections.observableArrayList(clientsArrayList);
			
			for (Client client: clients) {
				double subscriptionPrice = SubscriptionsDAO.getInstance().getPrice(client.getKindOfSubscription());
				LocalDate created_at = LocalDate.parse(client.getCreated_at(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				long difference = ChronoUnit.DAYS.between(created_at, LocalDate.now());
				if (difference < 0)
					difference = -1;
				client.setDaysToExpire((int) (30 - difference%31));
				client.setDebt((1 + difference / 31) * subscriptionPrice - (client.getPayments() * subscriptionPrice));
				
			}
		
			
			firstName.setCellValueFactory( new PropertyValueFactory<Client, String>("firstName"));
			lastName.setCellValueFactory(new PropertyValueFactory<Client, String>("LastName"));
			fatherFirstName.setCellValueFactory(new PropertyValueFactory<Client, String>("FatherFirstName"));
			kindOfSubscription.setCellValueFactory(new PropertyValueFactory<Client, String>("kindOfSubscription"));
			kindOfExercise.setCellValueFactory(new PropertyValueFactory<Client, String>("kindOfExercise"));
			address.setCellValueFactory(new PropertyValueFactory<Client, String>("address"));
			birthDate.setCellValueFactory(new PropertyValueFactory<Client, String>("birthDate"));
			zipCode.setCellValueFactory(new PropertyValueFactory<Client, Integer>("zipCode"));
			phoneNumber.setCellValueFactory(new PropertyValueFactory<Client, Long>("phoneNumber"));
			expireOfSubscription.setCellValueFactory(new PropertyValueFactory<Client, String>("daysToExpireWithSuffix"));
			debt.setCellValueFactory(new PropertyValueFactory<Client, String>("debtWithCurrency"));
			
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
			        
					stage.setResizable(false);
					//stage.initStyle(StageStyle.UNDECORATED);
					stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("icon.jpg")));
			        stage.show();
			        stage.setOnCloseRequest(event ->  {
						try {
							populate(dao.selectAll());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							new Alert(AlertType.ERROR, "Error! "+e.getMessage()).showAndWait();	
						}
					});
			        
			    } catch (IOException e) {
					new Alert(AlertType.ERROR, "Error! "+e.getMessage()).showAndWait();	
			        
			    }
			
		}
		
		@FXML
		public void updateHandler() {
			
			Long selectedClientPhoneNumber= null;
			
			try {
			
				selectedClientPhoneNumber =  clientsTableview.getSelectionModel().getSelectedItem().getPhoneNumber();	
				
			}catch(NullPointerException e) {
				
				new Alert(AlertType.ERROR, "Error! No selection").showAndWait();	
				return;
			}
			
			try {
		        FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setLocation(getClass().getClassLoader().getResource("addNewClient.fxml"));
		        Scene scene = new Scene(fxmlLoader.load());
		        Stage stage = new Stage();
		         
		        stage.setScene(scene);
		        stage.setUserData(selectedClientPhoneNumber);
		    	stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("icon.jpg")));
		    	stage.setResizable(false);
				//stage.initStyle(StageStyle.UNDECORATED);
		        stage.show();
		        stage.setOnCloseRequest(event ->  {
					try {
						populate(dao.selectAll());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						new Alert(AlertType.ERROR, "Error! "+e.getMessage()).showAndWait();	
					}
				});
		    } catch (IOException e) {
				new Alert(AlertType.ERROR, "Error! "+e.getMessage()).showAndWait();	
		        
		    }
			
			
		}
		//TODO ADD final to try catch later
		@FXML
		public void deleteHandler() {
		
			long selectedClientPhoneNumber;
			
			try {
			
				selectedClientPhoneNumber =  clientsTableview.getSelectionModel().getSelectedItem().getPhoneNumber();	
				
			}catch(NullPointerException e) {
				
				new Alert(AlertType.ERROR, "Error! No selection").showAndWait();	
				return;
			}
			
			
			Alert confirmationAlert  = new Alert(AlertType.CONFIRMATION, "Confirm delete", ButtonType.YES, ButtonType.NO);
			Optional<ButtonType> confirmation = confirmationAlert.showAndWait();
			if (confirmation.get() == ButtonType.YES) {
				
				try {
					dao.delete(selectedClientPhoneNumber);
				} catch (SQLException sql) {
					// TODO Auto-generated catch block
					new Alert(AlertType.ERROR, "Error! "+sql.getMessage()).showAndWait();	
				}
				try {
					populate(dao.selectAll());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					new Alert(AlertType.ERROR, "Error! "+e.getMessage()).showAndWait();	
				}
			}
		}
		
		//TODO ADD final to try catch later
		@FXML
		public void payHandler() {
			Client selectedClient =  clientsTableview.getSelectionModel().getSelectedItem();
			selectedClient.setPayments(selectedClient.getPayments()+1);
			
			try {
				dao.update(selectedClient.getPhoneNumber(), selectedClient);
			}
			catch(SQLException sql) {
				
				new Alert(AlertType.ERROR, "Error! "+sql.getMessage()).showAndWait();	
			}
			
			try {
				populate(dao.selectAll());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				new Alert(AlertType.ERROR, "Error! "+e.getMessage()).showAndWait();			

			}
			
		}
		
		@FXML
		public void searchHandler() {
			try {
				long phoneNumber = Long.parseLong(searchField.getText());
				Client client = dao.selectByPhoneNumber(phoneNumber);
				ArrayList<Client> clients = new ArrayList<Client>();
				
				
				clients.add(client);
				
				
				populate(clients);
			} catch (NumberFormatException e) {
				new Alert(AlertType.ERROR, "Error! no phonenumber").showAndWait();
				
			}
			catch ( NullPointerException e1) {
				// TODO: handle exception
				new Alert(AlertType.ERROR, "Error! no record").showAndWait();
			}
			catch (SQLException e2) {
				
				new Alert(AlertType.ERROR, "Error! "+e2.getMessage()).showAndWait();			
			}
			
			
			
			
		}
		
		@FXML
		public void clearHandler() {
			searchField.clear();
			try {
				populate(dao.selectAll());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				new Alert(AlertType.ERROR, "Error! "+e.getMessage()).showAndWait();			

			}
		}
		
		@FXML
		public void quitHandler() {
			
			System.exit(0);
			
		}
		

}
