package com.gd.run;

import java.io.IOException;
import com.gd.db.UMSDBException;
import com.gd.model.Developpeur;
import com.gd.model.Incident;
import com.gd.model.Rapporteur;
import com.gd.model.Responsable;
import com.gd.model.Utilisateur;
import com.gd.run.GDApplication;
import com.gd.service.DataSource;
import com.gd.util.PasswordUtil;
import com.gd.util.Utilitaire;
import com.gd.controller.AdminUIController;
import com.gd.controller.AjouterUserUIController;
import com.gd.controller.AssignerUIController;
import com.gd.controller.CommentaireUIController;
import com.gd.controller.CreerIncidentController;
import com.gd.controller.DeveloppeurUIController;
import com.gd.controller.ModifierEtatController;
import com.gd.controller.RapporteurUIController;
import com.gd.controller.ResponsableUIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GDApplication extends Application {

	private Stage primaryStage;
	private Stage AdminStage;
	private Stage RespoStage;
	private Stage RapporteurStage;
	private Stage DevStage;


	private DataSource dataSource;
	private PasswordUtil passwordUtil;
	private Utilitaire utilitaire;
	private static GDApplication instance;
	private String role;

	@Override
	public void start(Stage primaryStage) {
		instance = this;
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Gestion des Incidents");
		try {
			dataSource = new DataSource();
			
			passwordUtil = new PasswordUtil();
			initLoginLayout();
			
			

		} catch (UMSDBException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public PasswordUtil getPasswordUtil() {
		return passwordUtil;
	}
	public Utilitaire getUtilitaire() {
		return utilitaire;
	}

	public DataSource getDataSource() {
		return dataSource;
	}
	
	
	// Interface Administrateur
    public void initLoginLayout() {
			// TODO Auto-generated method stub
			try {
				// Load root layout from fxml file.
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(GDApplication.class.getResource("../ui/Login.fxml"));
				AnchorPane page = (AnchorPane) loader.load();

				// Show the scene containing the root layout.
				Scene scene = new Scene(page);
				primaryStage = new Stage();
				

				primaryStage.setScene(scene);
				
				primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
	

	// Interface Administrateur
	public void initAdministrateurLayout(Utilisateur user) {
		// TODO Auto-generated method stub
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GDApplication.class.getResource("../ui/AdministrateurUI.fxml"));
			BorderPane page = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(page);
			AdminStage = new Stage();
			primaryStage.hide();

			AdminStage.setScene(scene);
			AdminUIController controller = loader.getController();
			controller.setDialogStage(AdminStage);
			controller.setUser(user);
			AdminStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	// Interface Developeur
    public void initDevelopeurLayout(Developpeur user) {
			// TODO Auto-generated method stub
			try {
				// Load root layout from fxml file.
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(GDApplication.class.getResource("../ui/DeveloppeurUI.fxml"));
				BorderPane page = (BorderPane) loader.load();

				// Show the scene containing the root layout.
				Scene scene = new Scene(page);
				DevStage = new Stage();
				primaryStage.hide();

				DevStage.setScene(scene);
				DeveloppeurUIController controller = loader.getController();
				controller.setDialogStage(DevStage);
				controller.setUser(user);
				DevStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	// Interface Responsable
	public void initResponsableLayout(Responsable user) {
		// TODO Auto-generated method stub
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GDApplication.class.getResource("../ui/ResponsableUI.fxml"));
			BorderPane page = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(page);
			RespoStage = new Stage();
			primaryStage.hide();

			RespoStage.setScene(scene);
			ResponsableUIController controller = loader.getController();
			controller.setDialogStage(RespoStage);
			controller.setUser(user);

			RespoStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Interface Rapporteur
	public void initRapporteurLayout(Rapporteur user) {
		// TODO Auto-generated method stub
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GDApplication.class.getResource("../ui/RapporteurUI.fxml"));
			BorderPane page = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(page);
			RapporteurStage = new Stage();
			primaryStage.hide();

			RapporteurStage.setScene(scene);
			RapporteurUIController controller = loader.getController();
			controller.setDialogStage(RapporteurStage);
			controller.setUser(user);

			RapporteurStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Interface changer Password
	public void initChangePassword() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GDApplication.class.getResource("../ui/ChangePassword.fxml"));
			AnchorPane page;
			page = (AnchorPane) loader.load();
			// Create the dialog Stage.
			Stage StagePassword = new Stage();
			StagePassword.setTitle("Changer Votre Mot de passe");
			StagePassword.initModality(Modality.WINDOW_MODAL);
			StagePassword.initOwner(AdminStage);
			StagePassword.setResizable(false);
			StagePassword.setScene(new Scene(page));

			// Show the dialog and wait until the user closes it
			StagePassword.showAndWait();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// show User Edit Ui
	public boolean showUserEditUI(Utilisateur user) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GDApplication.class.getResource("../ui/AjouterUser.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Ajouter un utilisateur");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(AdminStage);
			dialogStage.setResizable(false);

			dialogStage.setScene(new Scene(page));

			AjouterUserUIController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setUser(user); // Set the user into the controller.
			
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			role = controller.isRole();

			return controller.isValiderClicked();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	// show Incident Edit Ui
	public boolean showIncidentEditUI(Incident incident) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GDApplication.class.getResource("../ui/CreerIncidentUI.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Ajouter un Incident");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(RapporteurStage);
			dialogStage.setResizable(false);

			dialogStage.setScene(new Scene(page));

			CreerIncidentController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setIncident(incident); // Set the user into the controller.
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isValiderClicked();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	// show Incident Commentaire Ui
	public boolean showIncidentCommentaireUI(Incident incident) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GDApplication.class.getResource("../ui/CommenterUI.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Ajouter un Commentaire");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(RapporteurStage);
			dialogStage.setResizable(false);

			dialogStage.setScene(new Scene(page));

			CommentaireUIController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setIncident(incident); //
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isValiderClicked();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	// show Incident Etat Ui
	public boolean showModifierEtatUI(Incident incident) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GDApplication.class.getResource("../ui/ModifierEtatUI.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Modifier Etat");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(RespoStage);
			dialogStage.setResizable(false);

			dialogStage.setScene(new Scene(page));

			ModifierEtatController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setIncident(incident);
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isValiderClicked();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	
	// show Incident Etat Ui
		public boolean showAssignerUI(Incident incident) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(GDApplication.class.getResource("../ui/AssignerUI.fxml"));
				AnchorPane page = (AnchorPane) loader.load();
				// Create the dialog Stage.
				Stage dialogStage = new Stage();
				dialogStage.setTitle("Modifier Etat");
				dialogStage.initModality(Modality.WINDOW_MODAL);
				dialogStage.initOwner(RespoStage);
				dialogStage.setResizable(false);

				dialogStage.setScene(new Scene(page));

				AssignerUIController controller = loader.getController();
				controller.setDialogStage(dialogStage);
				controller.setIncident(incident);
				// Show the dialog and wait until the user closes it
				dialogStage.showAndWait();

				return controller.isValiderClicked();
			} catch (IOException e) {
				System.err.println(e.getMessage());
				return false;
			}
		}

	public String isRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static GDApplication getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}

}
