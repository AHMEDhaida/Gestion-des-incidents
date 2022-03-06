package com.gd.controller;
import com.gd.db.UMSDBException;
import com.gd.model.Incident;
import com.gd.model.Notif;
import com.gd.model.Responsable;
import com.gd.run.GDApplication;


import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.sql.Date;


public class ResponsableUIController {
	
	@FXML
	private TableView<Incident> IncidentTable;
	
	
	@FXML
	private TableColumn<Incident, String> EtatColumn;
	@FXML
	private TableColumn<Incident, String> DescriptionColumn;
	@FXML
	private TableColumn<Incident, Date> DateColumn;
	@FXML
	private TableColumn<Incident, String> AssigneColumn;
	@FXML
	private TableColumn<Incident, String> mise_ajourColumn;
	@FXML
	private TableColumn<Incident, String> AppColumn;
	@FXML
	private TableColumn<Incident, Integer> IdColumn;
	
	@FXML
	private TableColumn<Incident, String>GraviteColumn;
	
	@FXML
	private MenuButton MenuButtonField;
	
	@FXML
	private TextField rechercherField;
	
	private Responsable user;
	@SuppressWarnings("unused")
	private Stage dialogStage;
	
	
	
	
	
	
	public Responsable getUser() {
		return user;
	}

	public void setUser(Responsable user) {
		this.user = user;
		MenuButtonField.setText( user.getLogin());
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	
	

	public ResponsableUIController() {
	}

	@FXML
	private void initialize() {
		// Initialise la table des utilisateurs
		
		// Initialise la table des Incident
		
				IdColumn.setCellValueFactory(cellData -> new  SimpleObjectProperty<Integer>(cellData.getValue().getId()));
				EtatColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEtat()));
				DescriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
				mise_ajourColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOpendate()));
				GraviteColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGravite()));
				AppColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApplication()));
				DateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Date>(cellData.getValue().getDate()));
				//AssigneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDeveloppeur().getPrenom()));

			
				IncidentTable.setItems(GDApplication.getInstance().getDataSource().getincIdents());
				
				

				rechercher();


	}
	
	
	@SuppressWarnings("static-access")
	@FXML
	private void handleCommentaire() {

		Incident incident = IncidentTable.getSelectionModel().getSelectedItem();
		int selectedIndex = IncidentTable.getSelectionModel().getSelectedIndex();
		
		if (incident != null) {
			boolean validerClicked = GDApplication.getInstance().showIncidentCommentaireUI(incident);
			if (validerClicked) {

				
				try {
					GDApplication.getInstance().getDataSource().UpdateIncident(incident, selectedIndex);
					GDApplication.getInstance().getUtilitaire().displayNotification(" Envoie de Commentaire  avec succès !");
					
				} catch (UMSDBException e) {
					// TODO Auto-generated catch block
					GDApplication.getInstance().getUtilitaire().displayErrorMessage("Error : " + e.getMessage());
				}

			

			}
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Aucune sélection");
			alert.setHeaderText("Aucun Tiket n'a été sélectionné !");
			alert.setContentText("Veuillez choisir un Tiket svp !.");
			alert.showAndWait();
		}
	}
	
	
	@SuppressWarnings("static-access")
	@FXML
	private void handleModifierEtat() {

		Incident selectedIncident = IncidentTable.getSelectionModel().getSelectedItem();
		int selectedIndex = IncidentTable.getSelectionModel().getSelectedIndex();

		if (selectedIncident != null) {
			boolean validerClicked = GDApplication.getInstance().showModifierEtatUI(selectedIncident);
			if (validerClicked) {

				
				try {
					GDApplication.getInstance().getDataSource().UpdateIncident(selectedIncident, selectedIndex);
					GDApplication.getInstance().getUtilitaire().displayNotification(" Incident update  avec succès !");
				} catch (UMSDBException e) {
					// TODO Auto-generated catch block
					GDApplication.getInstance().getUtilitaire().displayErrorMessage("Error : " + e.getMessage());
				}

				IncidentTable.refresh();

			}
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Aucune sélection");
			alert.setHeaderText("Aucun Ticket n'a été sélectionné !");
			alert.setContentText("Veuillez choisir un Ticket svp !.");
			alert.showAndWait();
		}
	}
	
	@SuppressWarnings("static-access")
	@FXML
	private void handleAssigner() {

		Incident selectedIncident = IncidentTable.getSelectionModel().getSelectedItem();
		int selectedIndex = IncidentTable.getSelectionModel().getSelectedIndex();

		if (selectedIncident != null) {
			boolean validerClicked = GDApplication.getInstance().showAssignerUI(selectedIncident);
			if (validerClicked) {
				Long rapporteur =(long) 0;
				Long developpeur = selectedIncident.getDeveloppeur().getId();

				
				try {
					GDApplication.getInstance().getDataSource().UpdateIncident(selectedIncident, selectedIndex);
					GDApplication.getInstance().getUtilitaire().displayNotification(" Assigner succès !");
					int id = idIncident();
					String note=  "Un nouvel incident avec come "+idIncident()+" a ete créé";
					String etat= "non_lu";
					Notif notif = new Notif(rapporteur,developpeur, id,selectedIncident.getEtat(),note, GDApplication.getInstance().getUtilitaire().getCurrentTime(),etat,etat,etat);
					
					GDApplication.getInstance().getDataSource().AddNotif(notif);
				} catch (UMSDBException e) {
				// TODO Auto-generated catch block
					GDApplication.getInstance().getUtilitaire().displayErrorMessage("Error : " + e.getMessage());
				}
			

				IncidentTable.refresh();

			}
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Aucune sélection");
			alert.setHeaderText("Aucun Ticket n'a été sélectionné !");
			alert.setContentText("Veuillez choisir un Ticket svp !.");
			alert.showAndWait();
		}
	}

	
	private void rechercher() {

		FilteredList<Incident> filteredData = new FilteredList<>(
				GDApplication.getInstance().getDataSource().getincIdents(), b -> true);

		rechercherField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(incident -> {

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (incident.getApplication().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}
				if (Long.valueOf(user.getId()).toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}
				return false;

			});
		});

		SortedList<Incident> sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(IncidentTable.comparatorProperty());

		IncidentTable.setItems(sortedData);

	}

	
	public int idIncident() {
		 ObservableList<Incident> incidents = FXCollections.observableArrayList();
		 Incident incident = null;
			incidents= GDApplication.getInstance().getDataSource().getincIdents();
			incident = incidents.get(incidents.size()-1);
		int id_incident = incident.getId();	
		return id_incident;
	}
	
	@FXML
	private void ChangePassword() {

		GDApplication.getInstance().initChangePassword();

	}

}
