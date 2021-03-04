package application.controleurs;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import annuaire.arbrebinaire.*;
import application.models.StagiaireModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class GestionStagiairesControleur implements Initializable{

	private VuePrincipaleControleur vuePrincipaleControleur;
	private Stage ajoutStagiaireStage;


	public static final String CHEMIN_FICHIER = "C:\\Users\\spect\\Desktop\\test\\";

	@FXML
	private TextField nomTextField;

	@FXML
	private TextField prenomTextField;

	@FXML
	private TextField departementTextField;

	@FXML
	private TextField formationTextField;

	@FXML
	private TextField anneeTextField;

	@FXML
	private Button createBtn;

	@FXML
	private Button resetBtn;

	@FXML
	private Button closeBtn;

	private ArbreBin<Stagiaires> arbre;

	public GestionStagiairesControleur(VuePrincipaleControleur vuePrincipaleControleur, ArbreBin<Stagiaires> arbre) {
		this.vuePrincipaleControleur = vuePrincipaleControleur;
		this.arbre = arbre;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {




		createBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					addStagiaire();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

		resetBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				reset();
			}
		});

		closeBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				closeStage();
			}
		});
	}



	public void addStagiaire () throws IOException {

		String erreurs = validateInput();
		if (erreurs.isEmpty()) {

			Stagiaires stagiaire = new Stagiaires();
			StagiaireModel mod = new StagiaireModel(arbre);
			stagiaire.setNom(nomTextField.getText());
			stagiaire.setPrenom(prenomTextField.getText());
			stagiaire.setDepartement(departementTextField.getText());
			stagiaire.setFormation(formationTextField.getText());
			stagiaire.setAnnee(anneeTextField.getText());
			System.out.println("YOYOYOYOYOYO " + nomTextField.getText());
			mod.verifStagiaire(stagiaire);
			arbre.ajouterNoeudApp(stagiaire);
			vuePrincipaleControleur.updateModele(stagiaire);
			vuePrincipaleControleur.updateVue();

			closeStage();
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Erreurs de saisie : ");
			alert.setContentText(erreurs);
			alert.show();

		}
	}

	public void showAddStagiaire(Region rootPane) {
		Scene scene = new Scene(rootPane, rootPane.getPrefWidth(), rootPane.getPrefHeight());
		this.ajoutStagiaireStage = new Stage();
		this.ajoutStagiaireStage.setTitle("Ajout d'un nouveau stagiaire");
		this.ajoutStagiaireStage.setScene(scene);
		this.ajoutStagiaireStage.show();
	}




	public void closeStage () {
		ajoutStagiaireStage.close();
	}

	private void reset() {
		nomTextField.clear();
		prenomTextField.clear();
		departementTextField.clear();
		formationTextField.clear();
		anneeTextField.clear();		
	}






	private String validateInput () {


		StringBuilder errorsBuilder = new StringBuilder();
		String nom = nomTextField.getText();
		for (char c : nom.toCharArray()) {
			if (Character.isDigit(c)) {
				errorsBuilder.append("Le nom du stagiaire ne doit pas contenir de chiffres\n");
			}
		}
		if (nom == null || nom.trim().isEmpty()) {
			errorsBuilder.append("Le nom du stagiaire doit etre renseigne\n");
		}


		String prenom = prenomTextField.getText();
		for (char c : prenom.toCharArray()) {
			if (Character.isDigit(c)) {
				errorsBuilder.append("Le prenom du stagiaire doit etre renseigne\n");
			}
		}
		if (prenom == null || prenom.trim().isEmpty()) {
			errorsBuilder.append("Le prenom du stagiaire doit etre renseigne\n");
		}


		String department = departementTextField.getText();
		if (department == null || department.trim().isEmpty()) {
			errorsBuilder.append("Le departement du stagiaire doit etre renseigne\n");
		} else {
			try {
				Double.valueOf(department);
			} catch (NumberFormatException e) {
				errorsBuilder.append("Le departement du stagiaire doit etre une valeur numerique\n");
			}
		}


		String formation = formationTextField.getText();
		if (formation == null || formation.trim().isEmpty()) {
			errorsBuilder.append("La formation du stagiaire doit etre renseignee\n");
		}

		String year = anneeTextField.getText();
		if (year == null || year.trim().isEmpty()) {
			errorsBuilder.append("L'annee du stagiaire doit etre renseignee\n");
		} else {
			try {
				Double.valueOf(year);
			} catch (NumberFormatException e) {
				errorsBuilder.append("L'annee du stagiaire doit etre une valeur numerique\n");
			}	
		}

		return errorsBuilder.toString();
	}

}
