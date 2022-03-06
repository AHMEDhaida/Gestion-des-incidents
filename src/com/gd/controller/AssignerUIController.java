package com.gd.controller;

import java.util.List;

import com.gd.db.UMSDBException;
import com.gd.model.Developpeur;
import com.gd.model.Incident;
import com.gd.model.Utilisateur;
import com.gd.run.GDApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AssignerUIController {
	
	private Incident incident;

	private Stage dialogStage;
	private boolean validerClicked;
	@FXML
	private ComboBox<String> UserComboBox;
	@FXML
	private TextField AppField;
	@FXML
	private TextArea DesField;
	
	List<Utilisateur> users;
	
	

	
	@FXML
	private void initialize() {
		UserComboBox.getItems().clear();
		UserComboBox.setItems(GDApplication.getInstance().getDataSource().getDev());
	}
	


	public void setIncident(Incident incident) {
		this.incident = incident;
		AppField.setText(incident.getApplication());
		DesField.setText(incident.getDescription());
		

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	// Called when the user clicks Valider.
	@FXML
	private void handleValider() {
		if (isInputValid()) {
			Developpeur user = null;
			 try {
				 user = (Developpeur) GDApplication.getInstance().getDataSource().Login(UserComboBox.getSelectionModel().getSelectedItem());
			} catch (UMSDBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			incident.setDeveloppeur(user);
			validerClicked = true;
			dialogStage.close();
		}
	}

	// Validates the user input in the text fields.
	private boolean isInputValid() {
		String errorMessage = "";
		if (AppField.getText() == null || AppField.getText().length() == 0) {
			errorMessage += "Le Nom Application n'est pas renseigné !\n";
		}

		// Compléter les contrôles des autres champs ...

		if (DesField.getText() == null || DesField.getText().length() == 0) {
			errorMessage += "Le Description n'est pas renseigné !\n";
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


}
