package application.controleurs;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javax.swing.JComboBox;
import annuaire.arbrebinaire.*;
import application.models.StagiaireModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class GestionRechercheControleur implements Initializable{

	private VuePrincipaleControleur vuePrincipaleControleur;

	@FXML
	private TextField nomTextField;

	private Stage rechercheAvStagiaireStage;

	private StagiaireModel modeleGlobalStagiaire;

	private ArbreBin<Stagiaires> arbre;

	private ObservableList<Stagiaires> listeDynamiqueStagiaire;

	@FXML
	private TableView<Stagiaires> stagiaireTable;

	@FXML
	private TextField prenomTextField;

	@FXML
	private ComboBox<String> departementComboB;

	@FXML
	private ComboBox<String> formationComboB;

	@FXML
	private ComboBox<String> anneeComboB;

	@FXML
	private Button validateBtn;;

	@FXML
	private Button resetBtn;

	@FXML
	private Button closeRechBtn;


	public  GestionRechercheControleur(VuePrincipaleControleur vuePrincipaleControleur,ArbreBin<Stagiaires> arbre) {
		this.vuePrincipaleControleur = vuePrincipaleControleur;
		this.arbre = arbre;
	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Stagiaires stagiaire = new Stagiaires();
		modeleGlobalStagiaire = new StagiaireModel(arbre);
		HashSet<String> hashDep = new HashSet<String>();
		HashSet<String> hashForm = new HashSet<String>();
		HashSet<String> hashYear = new HashSet<String>();

		List<String> listTempD = new ArrayList<String>();
		List<String> listTempF = new ArrayList<String>();
		List<String> listTempY = new ArrayList<String>();
		List<Stagiaires> globalTemp = modeleGlobalStagiaire.getStagiaires();
		for (int i=0; i<globalTemp.size(); i++) {
			Stagiaires stag = globalTemp.get(i);
			hashDep.add(stag.getDepartement());
			hashForm.add(stag.getFormation());
			hashYear.add(stag.getAnnee());
		}

		Iterator<String> depIt = hashDep.iterator();
		while (depIt.hasNext()) {		
			listTempD.add(depIt.next());
		}
		Iterator<String> formIt = hashForm.iterator();
		while (formIt.hasNext()) {		
			listTempF.add(formIt.next());
		}
		Iterator<String> yearIt = hashYear.iterator();
		while (yearIt.hasNext()) {		
			listTempY.add(yearIt.next());
		}

		java.util.Collections.sort(listTempD);
		java.util.Collections.sort(listTempF);
		java.util.Collections.sort(listTempY);

		departementComboB.setItems(FXCollections.observableList(listTempD));
		formationComboB.setItems(FXCollections.observableList(listTempF));
		anneeComboB.setItems(FXCollections.observableList(listTempY));


		validateBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				String erreurs = validInput();
				if (erreurs.isEmpty()) {
				
				String researchName = nomTextField.getText();
				String researchForename = prenomTextField.getText();
				String comboBDep = departementComboB.getSelectionModel().getSelectedItem();
				String comboBForm = formationComboB.getSelectionModel().getSelectedItem();
				String comboBYear = anneeComboB.getSelectionModel().getSelectedItem();
				List<Stagiaires> listTemp = new ArrayList<Stagiaires>();
				List<Stagiaires> globalTemp = modeleGlobalStagiaire.getStagiaires();

				for (int i=0; i<globalTemp.size(); i++) {
					Stagiaires stag = globalTemp.get(i);
					if (researchName.isEmpty()==false && 
							researchName.trim().compareToIgnoreCase(stag.getNom().trim()) != 0) {
						continue;
					}
					if (researchForename.isEmpty()==false &&
							researchForename.trim().compareToIgnoreCase(stag.getPrenom().trim()) !=0) {
						continue;
					}
					if (departementComboB.getSelectionModel().getSelectedIndex() != -1 &&
							comboBDep.trim().compareTo(stag.getDepartement().trim()) !=0) {
						continue;
					}
					if (formationComboB.getSelectionModel().getSelectedIndex() != -1 &&
							comboBForm.trim().compareTo(stag.getFormation().trim()) !=0) {
						continue;
					}
					if (anneeComboB.getSelectionModel().getSelectedIndex() != -1 &&
							comboBYear.trim().compareTo(stag.getAnnee().trim()) !=0) {
						continue;
					}
					listTemp.add(stag);
				}
				
				vuePrincipaleControleur.listeDynamiqueStagiaire.clear();
				vuePrincipaleControleur.listeDynamiqueStagiaire.addAll(listTemp);				
				closeRechStage();
				}
				
				
				else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Erreurs de saisie : ");
					alert.setContentText(erreurs);
					alert.show();
				}

			}});

		resetBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				reset();
			}
		});

		closeRechBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				closeRechStage();
			}
		});

	}

	public void showSearchStagiaire(Region rootPane) {
		Scene scene = new Scene(rootPane, rootPane.getPrefWidth(), rootPane.getPrefHeight());
		this.rechercheAvStagiaireStage = new Stage();
		this.rechercheAvStagiaireStage.setTitle("Recherche d'un stagiaire existant");
		this.rechercheAvStagiaireStage.setScene(scene);
		this.rechercheAvStagiaireStage.show();
	}

	private String validInput () {
		StringBuilder errorsBuilder = new StringBuilder();

		String nom = nomTextField.getText();
		for (char c : nom.toCharArray()) {
			if (Character.isDigit(c)) {
				errorsBuilder.append("Le nom du stagiaire ne doit pas contenir de chiffres\n");
			}
		}

		String prenom = prenomTextField.getText();
		for (char c : prenom.toCharArray()) {
			if (Character.isDigit(c)) {
				errorsBuilder.append("Le prenom du stagiaire ne peut pas contenir de chiffres\n");
			}
		}
			return errorsBuilder.toString();
		}

	

		private void closeRechStage() {
			rechercheAvStagiaireStage.close();
		}

		private void reset() {
			nomTextField.clear();
			prenomTextField.clear();
			departementComboB.getSelectionModel().select(0);
			formationComboB.getSelectionModel().select(0);
			anneeComboB.getSelectionModel().select(0);		
		}

	}

