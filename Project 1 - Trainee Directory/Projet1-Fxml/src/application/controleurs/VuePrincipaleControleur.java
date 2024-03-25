package application.controleurs;

import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import application.models.StagiaireModel;
import annuaire.arbrebinaire.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class VuePrincipaleControleur implements Initializable {



	private static final String VUE_AJOUT_STAGIAIRE_VIEW_PATH = "ressources/vue/VueSecondaireCreationStagiaire.fxml";
	private static final String VUE_RECH_AVANCEE_VIEW_PATH = "ressources/vue/VueSecondaireRechercheAvancee.fxml";
	private static final String VUE_MODIF_STAGIAIRE_VIEW_PATH = "ressources/vue/VueSecondaireModifStagiaire.fxml";
	private static final String VUE_SAVE_YES_NO_VIEW_PATH = "ressources/vue/afficherSaveYesNo.fxml";
	private static final String FICHIER_IMPRESSION_PDF = "ressources/vue/FichierImpression.pdf";
	private static final String FICHIER_DOC_PDF = "ressources/vue/DocUtilisateur.pdf";
	private static final int INDEX_COLONNE_NOM = 0;
	private static final int INDEX_COLONNE_PRENOM = 1;
	private static final int INDEX_COLONNE_DEPARTEMENT = 2;
	private static final int INDEX_COLONNE_FORMATION = 3;
	private static final int INDEX_COLONNE_ANNEE = 4;

	@FXML
	private Button nouveauStagiaireBtn;

	@FXML
	private Button supprimerStagiaireBtn;

	@FXML
	private Button modifierStagiaireBtn;

	@FXML
	private Button searchBtn;

	@FXML
	private Button okayClefBtn;

	@FXML
	private Button quitterAppBtn;

	@FXML
	private Button rechercheAvanceBtn;

	@FXML
	private Button refreshBtn;

	@FXML
	private TableView<Stagiaires> stagiaireTable;


	@FXML
	private TextField rechercheTextField;

	@FXML
	private PasswordField adminPasswordField;

	public Stagiaires deletedStagiaire;

	@FXML
	private MenuItem imprimer;

	@FXML
	private MenuItem documentation;


	private GestionStagiairesControleur gestionStagiairesControleur;
	private GestionRechercheControleur rechercheAvanceeControleur;
	private GestionModifStagiaireControleur modifStagiaireControleur;
	private StagiaireModel modeleGlobalStagiaire;
	public ObservableList<Stagiaires> listeDynamiqueStagiaire;
	private ArbreBin<Stagiaires> arbre;
	private GestionSaveYesNo gestionSaveYesno;


	public VuePrincipaleControleur(ArbreBin<Stagiaires> arbre) {
		this.arbre = arbre;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		modeleGlobalStagiaire = new StagiaireModel(this.arbre);

		initStagiairesTable();

		modifierStagiaireBtn.setDisable(true);
		supprimerStagiaireBtn.setDisable(true);



		nouveauStagiaireBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					afficherFenetreAjoutStagiaire();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		supprimerStagiaireBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				supprimerStagiaireSelectionne();
			}
		});

		
		searchBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String research = rechercheTextField.getText();
				if(research == null || research.isEmpty()) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setHeaderText("Erreur de saisie");
					alert.setContentText("Veuillez saisir un filtre de recherche");
					alert.show();
				} else {
					String upperCaseResearch = research.toUpperCase();
					List<Stagiaires> listTemp = new ArrayList<Stagiaires>();
					List<Stagiaires> globalTemp = modeleGlobalStagiaire.getStagiaires();
					for (int i = 0; i < globalTemp.size(); i++) {
						Stagiaires stag = globalTemp.get(i);

						if (upperCaseResearch.trim().compareToIgnoreCase(stag.getAnnee().trim()) == 0) {
							listTemp.add(stag);
						}  if (stag.getNom().equalsIgnoreCase(upperCaseResearch.trim()) || stag.getNom().contains(upperCaseResearch)) {
							listTemp.add(stag);
						}  if (stag.getPrenom().equalsIgnoreCase(upperCaseResearch.trim()) || stag.getPrenom().toUpperCase().contains(upperCaseResearch)) {
							listTemp.add(stag);
						}  if (upperCaseResearch.trim().compareToIgnoreCase(stag.getFormation().trim()) == 0) {
							listTemp.add(stag);
						}  if (upperCaseResearch.trim().compareToIgnoreCase(stag.getDepartement().trim()) == 0) {
							listTemp.add(stag);
						}
					}
					listeDynamiqueStagiaire.clear();
					listeDynamiqueStagiaire.addAll(listTemp);
					stagiaireTable.refresh();
				}
			}

		});



		refreshBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				listeDynamiqueStagiaire.clear();
				listeDynamiqueStagiaire.addAll(modeleGlobalStagiaire.getStagiaires());
				stagiaireTable.refresh();
			}
		});

		modifierStagiaireBtn.setOnAction(new EventHandler<ActionEvent>() {


			@Override
			public void handle(ActionEvent event) {

				if (stagiaireTable.getSelectionModel().getSelectedItem() != null)
				{
					try {
						afficherFenetreModifStag();
					} catch (IOException e) {
						e.printStackTrace();
					}	
				}
				else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Choisir un stagiaire");
					alert.setHeaderText("Aucun stagiaire n'est sélectionné");
					alert.show();

				}
			}
		});


		okayClefBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String admin = "1234";
				if (adminPasswordField.getText().compareTo(admin) ==0 ) {
					enableChanges();
				} else {				 
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Identification");
					alert.setHeaderText("Le mot de passe est incorrect !");
					alert.show();
				}
			}
		});

		rechercheAvanceBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					afficherFenetreRechAv();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		quitterAppBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					afficherSaveYesNo();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		 				
			}
		});

		imprimer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				imprimerPdf();
			}
		});

		documentation.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				openDoc(FICHIER_DOC_PDF);
			}
		});

	}


	@SuppressWarnings("unchecked")
	private void initStagiairesTable()  {


		System.out.println(stagiaireTable);
		System.out.println(stagiaireTable.getColumns().get(INDEX_COLONNE_ANNEE));

		TableColumn<Stagiaires, String> nomCol = (TableColumn<Stagiaires, String>) stagiaireTable.getColumns().get(INDEX_COLONNE_NOM);
		TableColumn<Stagiaires, String> prenomCol = (TableColumn<Stagiaires, String>) stagiaireTable.getColumns().get(INDEX_COLONNE_PRENOM);
		TableColumn<Stagiaires, String> departmentCol = (TableColumn<Stagiaires, String>) stagiaireTable.getColumns().get(INDEX_COLONNE_DEPARTEMENT);
		TableColumn<Stagiaires, String> formationCol = (TableColumn<Stagiaires, String>) stagiaireTable.getColumns().get(INDEX_COLONNE_FORMATION);
		TableColumn<Stagiaires, String> anneeCol = (TableColumn<Stagiaires, String>) stagiaireTable.getColumns().get(INDEX_COLONNE_ANNEE);

		
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		departmentCol.setCellValueFactory(new PropertyValueFactory<>("departement"));
		formationCol.setCellValueFactory(new PropertyValueFactory<>("formation"));
		anneeCol.setCellValueFactory(new PropertyValueFactory<>("annee"));

		listeDynamiqueStagiaire = FXCollections.observableArrayList(this.modeleGlobalStagiaire.getStagiaires());
		stagiaireTable.setItems(listeDynamiqueStagiaire);


	}

	void itemStateChanged(ItemEvent e) {

	}


	public void updateModele (Stagiaires stagiaire) throws IOException {
		modeleGlobalStagiaire.verifStagiaire(stagiaire);

	}
	
	public void updateModifModele (Stagiaires stagiaire) throws IOException {
		modeleGlobalStagiaire.verifModifStagiaire(stagiaire);

	}

	public void updateVue () {
		updateTable();	
	}


	public void afficherFenetreAjoutStagiaire() throws IOException {

		gestionStagiairesControleur = new GestionStagiairesControleur(this, arbre);
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(VUE_AJOUT_STAGIAIRE_VIEW_PATH));
		loader.setController(gestionStagiairesControleur);

		Pane rootPane = loader.load();

		gestionStagiairesControleur.showAddStagiaire(rootPane);
	}

	public void afficherFenetreRechAv() throws IOException {

		rechercheAvanceeControleur = new GestionRechercheControleur(this, arbre);
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(VUE_RECH_AVANCEE_VIEW_PATH));
		loader.setController(rechercheAvanceeControleur);

		Pane rootPane = loader.load();

		rechercheAvanceeControleur.showSearchStagiaire(rootPane);
	}

	public void afficherFenetreModifStag()  throws IOException {

		modifStagiaireControleur = new GestionModifStagiaireControleur(this, arbre);
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(VUE_MODIF_STAGIAIRE_VIEW_PATH));
		loader.setController(modifStagiaireControleur);
		deletedStagiaire = stagiaireTable.getSelectionModel().getSelectedItem();

		Pane rootPane = loader.load();

		modifStagiaireControleur.showModifStagiaire(rootPane);
	}


	public void afficherSaveYesNo() throws IOException {

		gestionSaveYesno = new GestionSaveYesNo(this,arbre);
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(VUE_SAVE_YES_NO_VIEW_PATH));
		loader.setController(gestionSaveYesno);

		Pane rootPane = loader.load();

		gestionSaveYesno.showYesNo(rootPane);
	}


	@FXML
	public void saveAndclose() throws IOException {
		String CHEMIN_FICHIER = "src\\annuaire\\arbrebinaire\\";
		arbre.ecrireArbreBin(CHEMIN_FICHIER + "ListeStagiaire.bin");
		Platform.exit();
	}
	
	@FXML
	public void closeApp() throws IOException {		
		Platform.exit();
	}
	

	private void supprimerStagiaireSelectionne() {
		Stagiaires stagiaire = stagiaireTable.getSelectionModel().getSelectedItem();
		System.out.println(stagiaire);		
		if(stagiaire != null) {
			arbre.deleteStagiaire(stagiaire);
			updateTable();

		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Suppression d'un stagiaire");
			alert.setHeaderText("Veuillez selectionner un stagiaire à supprimer");
			alert.show();
		}
	}

	private void updateTable() {
		listeDynamiqueStagiaire.clear();

		listeDynamiqueStagiaire.addAll(arbre.getListeStagiaires());


		stagiaireTable.refresh();
	}


	private void enableChanges() {

		modifierStagiaireBtn.setDisable(false);
		supprimerStagiaireBtn.setDisable(false);
	}


	String cheminAbsolu = System.getProperty("user.dir") + "\\src\\" + FICHIER_IMPRESSION_PDF;
	
	public void imprimerPdf() {
		try {
			boolean impression = imprimePdf(listeDynamiqueStagiaire);
			if(impression) {
				openFile(cheminAbsolu);
			}
		} catch (FileNotFoundException | DocumentException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("Erreur d'impression du fichier stagiaires:\n" + cheminAbsolu);
			alert.show();
		}
	}

	private void openFile(String filePath) {
		if(Desktop.isDesktopSupported()) {
			File pdfFile = new File(cheminAbsolu);
			try {
				Desktop.getDesktop().open(pdfFile);
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setHeaderText("Erreur d'ouverture du fichier stagiaires:\n" + cheminAbsolu);
				alert.show();
			}
		}
	}

	String cheminAbsolu2 = System.getProperty("user.dir") + "\\src\\" + FICHIER_DOC_PDF;
	
	private void openDoc(String filePath) {
		if(Desktop.isDesktopSupported()) {
			File pdfFile = new File(cheminAbsolu2);
			try {
				Desktop.getDesktop().open(pdfFile);
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setHeaderText("Erreur d'ouverture de la doc:\n" + cheminAbsolu2);
				alert.show();
			}
		}
	}

	private boolean imprimePdf(ObservableList<Stagiaires> stagiaires) throws DocumentException, FileNotFoundException {
		
		
		
		FileOutputStream fos = new FileOutputStream(new File(cheminAbsolu));
		Document doc = new Document();
		PdfWriter.getInstance(doc, fos);
		doc.open();

		doc.add(new Phrase("LISTE DES STAGIAIRES\n"));
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		doc.add(new Phrase(String.format("Générée le %s \n", formatter.format(localDateTime))) );	
		doc.add(new Phrase("Nombre de stagiaires: " + listeDynamiqueStagiaire.size() + "\n" ));

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100);
		PdfPCell cell1 = new PdfPCell(new Phrase("Nom"));
		PdfPCell cell2 = new PdfPCell(new Phrase("Prénom"));
		PdfPCell cell3 = new PdfPCell(new Phrase("Département"));
		PdfPCell cell4 = new PdfPCell(new Phrase("Formation"));
		PdfPCell cell5 = new PdfPCell(new Phrase("Année"));

		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);
		table.addCell(cell5);

		for (Stagiaires stagiaireTemp : stagiaires) {
			table.addCell(new Phrase(stagiaireTemp.getNom()));
			table.addCell(new Phrase(stagiaireTemp.getPrenom()));
			table.addCell(new Phrase(stagiaireTemp.getDepartement()));
			table.addCell(new Phrase(stagiaireTemp.getFormation()));
			table.addCell(new Phrase(stagiaireTemp.getAnnee()));

		}
		doc.add(table);
		doc.close();
		return true;
	}




}
