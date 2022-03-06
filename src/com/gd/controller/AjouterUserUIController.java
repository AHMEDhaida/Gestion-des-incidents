package com.gd.controller;




import com.gd.model.Utilisateur;
import com.gd.run.GDApplication;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AjouterUserUIController {
	@FXML
	private TextField nomField;
	@FXML
	private TextField prenomField;
	@FXML
	private TextField emailField;
	
	
	@FXML
	private TextField loginField;
	@FXML
	public PasswordField passwordField;
	@FXML
	private ComboBox<String> roleComboBox;
	private Stage dialogStage;
	private Utilisateur user;
	private boolean validerClicked;
	private String role;
	

	public void setRole(String role) {
		this.role = role;
	}

	@FXML
	private void initialize() {
		roleComboBox.getItems().clear();
		roleComboBox.getItems().addAll("ADMINISTRATEUR","DEVELOPPEUR", "RESPONSABLE","RAPPORTEUR");
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
		nomField.setText(user.getNom());
		prenomField.setText(user.getPrenom());
		emailField.setText(user.getEmail());
		loginField.setText(user.getLogin());
	

	}

	// Called when the user clicks Valider.
	@FXML
	private void handleValider() {
		if (isInputValid()) {
			user.setNom(nomField.getText());
			user.setPrenom(prenomField.getText());
			user.setEmail(emailField.getText());
			user.setLogin(loginField.getText());
			String pass = GDApplication.getInstance().getPasswordUtil().getHashedPassword(passwordField.getText());
			user.setPassword(pass);
			setRole( roleComboBox.getSelectionModel().getSelectedItem());

			validerClicked = true;
			dialogStage.close();
		}
	}

	

	// Validates the user input in the text fields.
	private boolean isInputValid() {
		String errorMessage = "";
		if (nomField.getText() == null || nomField.getText().length() == 0) {
			errorMessage += "Le Nom n'est pas renseigné !\n";
		}
		
		// Compléter les contrôles des autres champs ...
		
		if (prenomField.getText() == null || prenomField.getText().length() == 0) {
			errorMessage += "Le Prenom n'est pas renseigné !\n";
		}
		
		if (emailField.getText() == null || emailField.getText().length() == 0) {
			errorMessage += "Le Email n'est pas renseigné !\n";
		}
		
		
		
		if (loginField.getText() == null || loginField.getText().length() == 0) {
			errorMessage += "Le Togin n'est pas renseigné !\n";
		}
		
		
		
		
		
	
		if (errorMessage.length() == 0)
			return true;
		else { // Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Champs non renseignés et/ou invalides !");
			alert.setHeaderText("Veuillez remplir tous les champs svp !");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	}

	// Called when the user clicks Annuler.
	@FXML
	private void handleAnnuler() {
		validerClicked = false;
		dialogStage.close();
	}

	public boolean isValiderClicked() {
		return validerClicked;
	}
	
	public String isRole(){
		return role;
	}
	

	

}
