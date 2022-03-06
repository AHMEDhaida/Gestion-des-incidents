package com.gd.controller;

import com.gd.model.Etat;
import com.gd.model.Incident;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CreerIncidentController {


	private Incident incident;

	private Stage dialogStage;
	private boolean validerClicked;
	@FXML
	private ComboBox<String> NiveauComboBox;
	@FXML
	private TextField AppField;
	@FXML
	private TextArea DesField;
	
	@FXML
	private DatePicker Date;

	@FXML
	private void initialize() {
		NiveauComboBox.getItems().clear();
		NiveauComboBox.getItems().addAll("Basse", "Moyenne", "Critique");
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
		AppField.setText(incident.getApplication());
		DesField.setText(incident.getDescription());
		Date.getEditor().setText(incident.getOpendate());

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	// Called when the user clicks Valider.
	@FXML
	private void handleValider() {
		if (isInputValid()) {
			incident.setApplication(AppField.getText());
			incident.setDescription(DesField.getText());
			incident.setGravite(NiveauComboBox.getSelectionModel().getSelectedItem());
			incident.setOpendate(Date.getEditor().getText().toString());
			incident.setEtat(Etat.Nouveau.toString());

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
