package com.gd.controller;


import java.util.Date;

import com.gd.model.Etat;
import com.gd.model.Incident;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ModifierEtatController {
	
	
	private Incident incident;
	private Date dateupdate;

	private Stage dialogStage;
	private boolean validerClicked;
	@FXML
	private ComboBox<String> EtatComboBox;
	@FXML
	private TextField AppField;
	@FXML
	private TextArea DesField;
	
	

	@FXML
	private void initialize() {
		EtatComboBox.getItems().clear();
		EtatComboBox.getItems().addAll(Etat.Nouveau.getValue(), Etat.En_cours.getValue(), Etat.En_attente.getValue(),Etat.Résolu.getValue(),Etat.Fermé.getValue());
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
	@SuppressWarnings("deprecation")
	@FXML
	private void handleValider() {
		if (isInputValid()) {
			
			this.dateupdate = new Date();
			incident.setEtat(EtatComboBox.getSelectionModel().getSelectedItem());
			incident.setOpendate("ll y a "+" " +dateupdate.getDay()+" "+ " jours");
		

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
