package com.gd.controller;


import javax.swing.JOptionPane;

import com.gd.db.UMSDBException;
import com.gd.model.Administrateur;
import com.gd.model.Developpeur;
import com.gd.model.Rapporteur;
import com.gd.model.Responsable;
import com.gd.model.Utilisateur;
import com.gd.run.GDApplication;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AdminUIController {

	@FXML
	private TableView<Utilisateur> userTable;

	@FXML
	private TableColumn<Utilisateur, String> nomColumn;
	@FXML
	private TableColumn<Utilisateur, String> prenomColumn;
	@FXML
	private TableColumn<Utilisateur, String> loginColumn;
	@FXML
	private TableColumn<Utilisateur, String> emailColumn;
	@FXML
	private TableColumn<Utilisateur, String> roleColumn;

	@FXML
	private Label nomLabel;

	@FXML
	private Label prenomLabel;

	@FXML
	private Label emailLabel;

	@FXML
	private Label loginLabel;

	@FXML
	private Label roleLabel;

	@FXML
	private TextField rechercherField;
	
	@FXML
	private MenuButton MenuButtonField;
	
	private Utilisateur user;
	@SuppressWarnings("unused")
	private Stage dialogStage;
	
	

	

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
		MenuButtonField.setText( user.getLogin());
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	

	@FXML
	private void initialize() {
		// Initialise la table des utilisateurs

		nomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
		prenomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));
		loginColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLogin()));
		emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
		roleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));
		

	
		userTable.setItems(GDApplication.getInstance().getDataSource().getUsers());
		
		

		rechercher();

	}
	
	
	
	



	/**
	 * Called when the user clicks on the Supprimer button.
	 */
	@FXML
	private void handleSupprimerUser() {

		int selectedIndex = userTable.getSelectionModel().getSelectedIndex();

		if (selectedIndex >= 0) {
			try {

				GDApplication.getInstance().getDataSource().DeleteUser(selectedIndex);

			} catch (UMSDBException e) {

				e.printStackTrace();

			}
			;
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning ...");
			alert.setHeaderText("Alerte !");
			alert.setContentText("Veuillez sélectionner un utilisateur svp !");
			alert.showAndWait();
		}
	}

	@FXML
	private void handleNouveauUser() {

		Administrateur user = new Administrateur();
		boolean validerClicked = GDApplication.getInstance().showUserEditUI(user);

		if (validerClicked) {

			String role = GDApplication.getInstance().isRole();
			
			if(role == null) {

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning ...");
				alert.setHeaderText("Alerte !");
				alert.setContentText("Veuillez sélectionner Role svp !");
				alert.showAndWait();

			} else if (role.equals("ADMINISTRATEUR")) {

				Administrateur admin = new Administrateur();
				admin.setNom(user.getNom());
				admin.setPrenom(user.getPrenom());
				admin.setEmail(user.getEmail());
				admin.setLogin(user.getNom());
				admin.setPassword(user.getPassword());
				try {
					GDApplication.getInstance().getDataSource().AddUser(admin);
				} catch (UMSDBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (role.equals("DEVELOPPEUR")) {

				Developpeur dev = new Developpeur();
				dev.setNom(user.getNom());
				dev.setPrenom(user.getPrenom());
				dev.setEmail(user.getEmail());
				dev.setLogin(user.getNom());
				dev.setPassword(user.getPassword());
				try {
					GDApplication.getInstance().getDataSource().AddUser(dev);
				} catch (UMSDBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (role.equals("RESPONSABLE")) {

				Responsable res = new Responsable();
				res.setNom(user.getNom());
				res.setPrenom(user.getPrenom());
				res.setEmail(user.getEmail());
				res.setLogin(user.getNom());
				res.setPassword(user.getPassword());
				try {
					GDApplication.getInstance().getDataSource().AddUser(res);
				} catch (UMSDBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}else if (role.equals("RAPPORTEUR")) {

				Rapporteur rap = new Rapporteur();
				rap.setNom(user.getNom());
				rap.setPrenom(user.getPrenom());
				rap.setEmail(user.getEmail());
				rap.setLogin(user.getNom());
				rap.setPassword(user.getPassword());
				try {
					GDApplication.getInstance().getDataSource().AddUser(rap);
				} catch (UMSDBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
			else {

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning ...");
				alert.setHeaderText("Alerte !");
				alert.setContentText("Veuillez sélectionner Role svp !");
				alert.showAndWait();

			}
			
			

		}

	}

	@FXML
	private void ChangePassword() {

		GDApplication.getInstance().initChangePassword();

	}

	@FXML
	private void handleModifierUser() {

		Utilisateur selectedUser = userTable.getSelectionModel().getSelectedItem();
		int selectedIndex = userTable.getSelectionModel().getSelectedIndex();

		if (selectedUser != null) {
			boolean validerClicked = GDApplication.getInstance().showUserEditUI(selectedUser);
			if (validerClicked) {
				
				try {
					GDApplication.getInstance().getDataSource().UpdateUser(selectedUser, selectedIndex);
				} catch (UMSDBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				userTable.refresh();

			}
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Aucune sélection");
			alert.setHeaderText("Aucun utilisateur n'a été sélectionné !");
			alert.setContentText("Veuillez choisir un utilisateur svp !.");
			alert.showAndWait();
		}
	}

	private void rechercher() {

		FilteredList<Utilisateur> filteredData = new FilteredList<>(
				GDApplication.getInstance().getDataSource().getUsers(), b -> true);

		rechercherField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(user -> {

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (user.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}
				if (Long.valueOf(user.getId()).toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}
				return false;

			});
		});

		SortedList<Utilisateur> sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(userTable.comparatorProperty());

		userTable.setItems(sortedData);

	}

	@FXML
	private void handlerechercher() {

		// SimpleStringProperty id = new
		// SimpleStringProperty(rechercherField.getText());
		String response = JOptionPane.showInputDialog(null, "Veuillez saisir l'id l'utilisateur a rechercher :",
				"Rechercher un utilisaleur ....", JOptionPane.QUESTION_MESSAGE);

		try {
			Integer id = Integer.parseInt(response);
			try {
				Utilisateur user = GDApplication.getInstance().getDataSource().ReadUser(id);

				if (user == null) {
					JOptionPane.showMessageDialog(null, "L' utilisateur rechercher n. existe pas !",
							"Rechercher un utilisaleur ....", JOptionPane.INFORMATION_MESSAGE);

				} else {
					GDApplication.getInstance().showUserEditUI(user);
				}
			} catch (UMSDBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Veuillez saisir nombre entier svp !", e.getClass().toString(),
					JOptionPane.ERROR_MESSAGE);
		}
		

	}

}
