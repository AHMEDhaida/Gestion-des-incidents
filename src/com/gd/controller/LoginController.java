package com.gd.controller;


import java.sql.SQLException;

import com.gd.model.Developpeur;
import com.gd.model.Rapporteur;
import com.gd.model.Responsable;
import com.gd.model.Utilisateur;
import com.gd.run.GDApplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginController {

	@FXML
	private TextField UserIDTextbox;

	@FXML
	private PasswordField PasswordTextbox;

	@FXML
	private Button submitButton;
	@FXML
	public Button closeButton;
	
	Utilisateur user;
	
	@FXML
	public void login(ActionEvent event) throws SQLException {

		if (UserIDTextbox.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter your login");
			return;
		}
		if (PasswordTextbox.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a password");
			return;
		}

		String login = UserIDTextbox.getText();
		String password = PasswordTextbox.getText();
		
		
		 user = GDApplication.getInstance().getDataSource().Login(login);
		if (user == null) {
			// afficher message d'erreur 
			showAlert(Alert.AlertType.ERROR, "Form Error!", "Cet utilisateur n'existe pas");
			

		} else {	
			
			
			boolean test = GDApplication.getInstance().getPasswordUtil().comparePassword(user.getPassword(), password);
			if(!test) {
				
				showAlert(Alert.AlertType.ERROR, "Form Error!", "Password Incorcte");
				
			}else {
			
			String userType = GDApplication.getInstance().getDataSource().type(user.getId());
			switch (userType)
			{
				case "Rapporteur":
					Rapporteur repp = new Rapporteur();
					repp = (Rapporteur) user;
					GDApplication.getInstance().initRapporteurLayout(repp);
					
					

					break;
					
				case "Developpeur":
					Developpeur dev = new Developpeur();
					dev = (Developpeur) user;
					GDApplication.getInstance().initDevelopeurLayout(dev);
					
					
					break;
				case "Responsable":
					Responsable respo = new Responsable();
					respo = (Responsable) user;
					GDApplication.getInstance().initResponsableLayout(respo);
					
					
					break;
					
				case "Administrateur":
					
					GDApplication.getInstance().initAdministrateurLayout(user);
					
					break;
			}
			
			}
			
		}
      

	}
	
	@FXML
	public void handleCloseButtonAction(ActionEvent event) {
	    Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}

	public static void infoBox(String infoMessage, String headerText, String title) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText(infoMessage);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.showAndWait();
	}

	private static void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		
		alert.show();
	}

}
