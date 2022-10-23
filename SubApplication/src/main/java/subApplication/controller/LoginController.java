package subApplication.controller;

import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import subApplication.dao.UserDAO;
import subApplication.model.User;

public class LoginController implements Initializable {
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserDAO dao;
	
	@FXML
	private TextField username;
	@FXML
	private TextField password;

	public LoginController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		dao = UserDAO.getInstance();
		bCryptPasswordEncoder  = new BCryptPasswordEncoder(10, new SecureRandom());;
		
	}
	
	private String hashPassword(String plainTextPassword) {
				
		return bCryptPasswordEncoder.encode(plainTextPassword);
	}

	private boolean checkPassword(String encodedPassword, String plainTextPassword) {

		return bCryptPasswordEncoder.matches(plainTextPassword, encodedPassword);
		
	}
	
	@FXML
	public void loginHandler() {
		if (checkPassword(dao.selectByPhoneNumber(username.getText()).getPassword(), password.getText())) {
			 try {
			        FXMLLoader fxmlLoader = new FXMLLoader();
			        fxmlLoader.setLocation(getClass().getClassLoader().getResource("main.fxml"));
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
			        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			            public void handle(WindowEvent we) {
			                System.exit(0);
			            }
			        });
			        Stage myStage = (Stage) username.getScene().getWindow();
			        myStage.close();
			        
			    } catch (IOException e) {
			    	e.printStackTrace();
			        
			    }
		}
		
	}
	@FXML
	public void registerHandler() {
		
		try {
			
			dao.insert(new User(username.getText(), hashPassword(password.getText())));
			
		}catch(SQLException sql){
			
			sql.printStackTrace();
			
		}
		
	}

}
