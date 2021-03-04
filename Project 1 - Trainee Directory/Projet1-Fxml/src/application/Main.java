package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import annuaire.arbrebinaire.ArbreBin;
import annuaire.arbrebinaire.Stagiaires;
import application.controleurs.VuePrincipaleControleur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage vueprincipaleProjet1;
	@Override
	public void start(Stage primaryStage) {
		vueprincipaleProjet1 = primaryStage;
		try {
			ArbreBin<Stagiaires> arbre = creerFichierBinaire();
			VuePrincipaleControleur Annuaire = new VuePrincipaleControleur(arbre);
			FXMLLoader loader = new FXMLLoader(
					getClass().getClassLoader().getResource("ressources/vue/VuePrincipaleProjet1.fxml"));
			loader.setController(Annuaire);
			Pane root = loader.load();
			Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
			vueprincipaleProjet1.setScene(scene);
			vueprincipaleProjet1.setTitle("Annuaire Stagiaire");
			vueprincipaleProjet1.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static final String CHEMIN_FICHIER = "src\\annuaire\\arbrebinaire\\";

	private ArbreBin<Stagiaires> creerFichierBinaire() throws IOException {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		String textFilePath = CHEMIN_FICHIER + "ListeStagiaire.txt";
		String binFilePath = CHEMIN_FICHIER + "ListeStagiaire.bin";
		File binFile = new File(binFilePath);

		try {
			ArbreBin<Stagiaires> arbreBinaire = new ArbreBin<Stagiaires>();
			if (binFile.exists() == false) {
				fis = new FileInputStream(CHEMIN_FICHIER + "ListeStagiaire.txt");
				FileReader listeStagiaireReader = new FileReader(CHEMIN_FICHIER + "ListeStagiaire.txt");
				BufferedReader listeStagBr = new BufferedReader(listeStagiaireReader);
				RandomAccessFile fichierBinaireRaf = new RandomAccessFile(CHEMIN_FICHIER + "ListeStagiaire.bin", "rw");
				fichierBinaireRaf.setLength(0);
				{
					Stagiaires stagiaire = new Stagiaires();
					while ((listeStagBr.ready())) {
						for (int i = 0; i < 6; i++) {
							String brl = listeStagBr.readLine();

							if (i == 0) {
								stagiaire.setNom(brl);

							}
							if (i == 1) {
								stagiaire.setPrenom(brl);

							}
							if (i == 2) {
								stagiaire.setDepartement(brl);

							}
							if (i == 3) {
								stagiaire.setFormation(brl);

							}
							if (i == 4) {
								stagiaire.setAnnee(brl);

							}
							if (i == 5) {
								
								arbreBinaire.ajouterNoeud(stagiaire, fichierBinaireRaf);
								
								stagiaire = new Stagiaires();
							}
						}
					}
				}
				listeStagBr.close();
				fichierBinaireRaf.close();
			}

			arbreBinaire.lireFichierBin(CHEMIN_FICHIER + "ListeStagiaire.bin");
			return arbreBinaire;
		}
		catch (IOException ioe) {
			System.out.println("Erreur d'ouverture de fichier");
			ioe.printStackTrace();
		} finally {
			if (fis != null)
				fis.close();
			if (fos != null)
				fos.close();
		}
		return null;
	}

}
