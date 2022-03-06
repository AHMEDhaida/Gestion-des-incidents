package com.gd.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.gd.model.Incident;
import com.gd.model.Note;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CommentaireUIController {
	private Stage dialogStage;
	private boolean validerClicked;
	@SuppressWarnings("unused")
	private Incident incident;
	@FXML
	private TextArea CmmentaireField;
	@FXML
	private TextArea TiketField;
	
	private Date date;
	
	private List<Note> notes = new ArrayList<>();
	
	
	public void setIncident(Incident incident) {
		this.incident = incident;

		
		
		
		String s = "#"+incident.getId()+" "+incident.getApplication();
		TiketField.setText(s);

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	// Called when the user clicks Valider.
		@FXML
		private void handleValider() {
			if (isInputValid()) {
				 Note note = new Note(CmmentaireField.getText(),date);
				
				notes.add(note);
				//incident.getNotes().clear();
				incident.getNotes().addAll(notes);
				validerClicked = true;
				dialogStage.close();
			}
		}
		
		// Validates the user input in the text fields.
		private boolean isInputValid() {
			String errorMessage = "";
			

			if (CmmentaireField.getText() == null || CmmentaireField.getText().length() == 0) {
				errorMessage += "Le Commentaire n'est pas renseigné !\n";
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
