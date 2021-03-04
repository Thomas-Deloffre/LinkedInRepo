package application.controleurs;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import annuaire.arbrebinaire.*;
import application.models.StagiaireModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class GestionModifStagiaireControleur implements Initializable{

	private VuePrincipaleControleur vuePrincipaleControleur;
	private Stage modifStagiaireStage;

	public static final String CHEMIN_FICHIER = "C:\\Users\\spect\\Desktop\\test\\";


	
	@FXML
	private TableView<Stagiaires> stagiaireTable;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField surnameTextField;

	@FXML
	private TextField departmentTextField;

	@FXML
	private TextField formTextField;

	@FXML
	private TextField yearTextField;

	@FXML
	private Button validBtn;;

	@FXML
	private Button rstBtn;

	@FXML
	private Button clsBtn;

	private ArbreBin<Stagiaires> arbre;
	
	private ObservableList<Stagiaires> listeDynamiqueStagiaire;

	public GestionModifStagiaireControleur(VuePrincipaleControleur vuePrincipaleControleur, ArbreBin<Stagiaires> arbre) {
		this.vuePrincipaleControleur = vuePrincipaleControleur;
		this.arbre = arbre;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {




		validBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					modifStagiaire();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

		rstBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				rst();
			}
		});

		clsBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				closStage();
			}
		});
	}



	public void modifStagiaire () throws IOException {

		String erreurs = validInput();
		if (erreurs.isEmpty()) {
			Stagiaires deletedStagiaire = vuePrincipaleControleur.deletedStagiaire;		
			System.out.println(deletedStagiaire);	
			Stagiaires savedStagiaire = deletedStagiaire;
			System.out.println(savedStagiaire);
			if(deletedStagiaire != null) {
				arbre.deleteStagiaire(deletedStagiaire);	
				Stagiaires newStagiaire = new Stagiaires();
				
				newStagiaire.setNom(nameTextField.getText());
				if (newStagiaire.getNom().isEmpty()) {					
					newStagiaire.setNom(savedStagiaire.getNom());
				}
				newStagiaire.setPrenom(surnameTextField.getText());
				if (newStagiaire.getPrenom().isEmpty()) {					
					newStagiaire.setPrenom(savedStagiaire.getPrenom());
				}
				newStagiaire.setDepartement(departmentTextField.getText());
				if (newStagiaire.getDepartement().isEmpty()) {					
					newStagiaire.setDepartement(savedStagiaire.getDepartement());
				}
				
				newStagiaire.setFormation(formTextField.getText());
				if (newStagiaire.getFormation().isEmpty()) {					
					newStagiaire.setFormation(savedStagiaire.getFormation());
				}
				newStagiaire.setAnnee(yearTextField.getText());
				if (newStagiaire.getAnnee().isEmpty()) {					
					newStagiaire.setAnnee(savedStagiaire.getAnnee());
				}
				
				//System.out.println("YOYOYOYOYOYO " + nameTextField.getText());			
				arbre.ajouterNoeudApp(newStagiaire);				
				vuePrincipaleControleur.updateModifModele(newStagiaire);
				vuePrincipaleControleur.updateVue();
				closStage();
				
			}

		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Erreurs de saisie : ");
			alert.setContentText(erreurs);
			alert.show();

		}
	}

	public void showModifStagiaire(Region rootPane) {
		Scene scene = new Scene(rootPane, rootPane.getPrefWidth(), rootPane.getPrefHeight());
		this.modifStagiaireStage = new Stage();
		this.modifStagiaireStage.setTitle("Modification du profil stagiaire");
		this.modifStagiaireStage.setScene(scene);
		this.modifStagiaireStage.show();
	}




	public void closStage () {
		modifStagiaireStage.close();
	}

	private void rst() {
		nameTextField.clear();
		surnameTextField.clear();
		departmentTextField.clear();
		formTextField.clear();
		yearTextField.clear();		
	}






	private String validInput () {


		StringBuilder errorsBuilder = new StringBuilder();
		String nom = nameTextField.getText();
		for (char c : nom.toCharArray()) {
			if (nom != null && Character.isDigit(c)) {
				errorsBuilder.append("Le nom du stagiaire ne doit pas contenir de chiffres\n");
			}
		}


		String prenom = surnameTextField.getText();
		for (char c : prenom.toCharArray()) {
			if (prenom != null && Character.isDigit(c)) {
				errorsBuilder.append("Le nom du stagiaire ne doit pas contenir de chiffres\n");
			}
		}

		String department = departmentTextField.getText();
		if (department.isEmpty()==false) {
			try {
				Double.valueOf(department);
			} catch (NumberFormatException e) {
				errorsBuilder.append("Le departement du stagiaire doit etre une valeur numérique\n");
			}
		}

		String year = yearTextField.getText();	
		if (year.isEmpty()==false) {
			try {
				Double.valueOf(year);
			} catch (NumberFormatException e) {
				errorsBuilder.append("L'annee du stagiaire doit etre une valeur numérique\n");
			}	
		}


		return errorsBuilder.toString();
	}


}
