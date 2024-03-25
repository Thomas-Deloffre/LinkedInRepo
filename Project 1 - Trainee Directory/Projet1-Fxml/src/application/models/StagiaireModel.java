package application.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import application.controleurs.GestionStagiairesControleur;
import annuaire.arbrebinaire.ArbreBin;
import annuaire.arbrebinaire.Stagiaires;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;



public class StagiaireModel {

	@FXML
	private TextField nom;

	@FXML
	private TextField prenom;

	@FXML
	private TextField departement;

	@FXML
	private TextField formation;

	@FXML
	private TextField annee;


	Alert alert = new Alert(AlertType.ERROR);

	private ArbreBin<Stagiaires> arbreBinaire;
	private List<Stagiaires> stagiaires;

	public static final String CHEMIN_FICHIER = "src\\annuaire\\arbrebinaire\\";

	public StagiaireModel(TextField nom, TextField prenom, TextField departement, TextField formation, TextField annee,
			List<Stagiaires> stagiaires) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.formation = formation;
		this.annee = annee;
	}


	public StagiaireModel(ArbreBin<Stagiaires> arbre) {
		arbreBinaire = arbre;
		this.stagiaires = new ArrayList<>();
	}

	public boolean verifStagiaire (Stagiaires stagiaire) throws IOException {
		String name = stagiaire.getNom();
		String forename = stagiaire.getPrenom();
		String department = stagiaire.getDepartement();
		String form = stagiaire.getFormation();
		String year = stagiaire.getAnnee();

		System.err.println("Vous avez saisi : " + name + " " + forename + " " + department + " " + form + " "+ year + " ");
		try {
			System.out.println("Validation1");
			validateName(name);
			validateForename(forename);
			validateDepartment(department);
			validateFormation(form);
			validateYear(year);
			System.out.println("validation2");
		}
		catch (Exception e)
		{String msg = e.getMessage();
		System.out.println(name);
		System.out.println(forename);
		System.out.println(department);
		System.out.println(form);
		System.out.println(year);
		alert.setHeaderText(msg + "Une des entr�es est incorrecte");
		alert.show();
		}

		return false;
	}
		
		public boolean verifModifStagiaire (Stagiaires stagiaire) throws IOException {
			String name = stagiaire.getNom();
			String forename = stagiaire.getPrenom();
			String department = stagiaire.getDepartement();
			String form = stagiaire.getFormation();
			String year = stagiaire.getAnnee();

			System.err.println("Vous avez saisi : " + name + " " + forename + " " + department + " " + form + " "+ year + " ");
			try {
				System.out.println("Validation1");
				validateName(name);
				validateForename(forename);				
				validateFormation(form);
				validateYear(year);
				System.out.println("validation2");
			}
			catch (Exception e)
			{String msg = e.getMessage();
			System.out.println(name);
			System.out.println(forename);
			System.out.println(department);
			System.out.println(form);
			System.out.println(year);
			alert.setHeaderText(msg + "Une des entrées est incorrecte");
			alert.show();
			}

			return false;


	}

	private void validateName(final String name) throws IOException {

		
		if(name == null) throw new IOException("Le nom ne doit pas être nul");
		if(name.trim().isEmpty()) throw new IOException("Le nom du stagiaire ne doit pas etre vide");
		
	}

	private void validateForename(final String forename) throws IOException {

		if(forename == null) throw new IOException("Le prenom du stagiaire ne doit pas etre nul");
		if(forename.trim().isEmpty()) throw new IOException("Le prenom du stagiaire ne doit pas etre vide");

	}

	private void validateDepartment(final String department) throws IOException {
		
		if(department.trim().isEmpty()) throw new IOException("Le departement ne doit pas �tre vide");
		if(department.length() != 2 && department.length() != 3 ) throw new IOException("Le departement ne peut pas �tre sup�rieur ou inf�rieur � 2 caract�res");

	}

	private void validateFormation(final String formation) throws IOException {

		if(formation == null) throw new IOException("L'intitule de formation ne doit pas etre nul");
		if(formation.trim().isEmpty()) throw new IOException("l'intitule de formation ne doit pas etre vide");
		if(formation.length() < 4 ) throw new IOException("L'intitule de formation ne peut pas etre inferieur a 3 caracteres");
		if(formation.length() > 16 ) throw new IOException("L'intitule de formation ne peut pas etre inferieur a 8 caracteres");
	}

	private void validateYear(final String year) throws IOException {

		if(year == null) throw new IOException("L'annee ne doit pas etre nulle");
		if(year.trim().isEmpty()) throw new IOException("L'annee ne doit pas etre vide");
		if(year.length() != 4 ) throw new IOException("l'annee ne peut pas etre superieure ou inferieure a 4 caracteres");
	}


	public List<Stagiaires> getStagiaires()  {
		return arbreBinaire.getListeStagiaires();
	}

	

}
