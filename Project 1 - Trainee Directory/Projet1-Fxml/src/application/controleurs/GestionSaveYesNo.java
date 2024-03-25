package application.controleurs;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import annuaire.arbrebinaire.*;
import application.models.StagiaireModel;
import javafx.application.Platform;
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

public class GestionSaveYesNo implements Initializable{

	private VuePrincipaleControleur vuePrincipaleControleur;
	private Stage yesNoStage;

	

	@FXML
	private Button yesBtn;

	@FXML
	private Button noBtn;
	
	private ArbreBin<Stagiaires> arbre;
	
	
	

	public GestionSaveYesNo(VuePrincipaleControleur vuePrincipaleControleur, ArbreBin<Stagiaires> arbre) {
		this.vuePrincipaleControleur = vuePrincipaleControleur;
		this.arbre = arbre;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {




		yesBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				closeyesNoStage();
				try {
					vuePrincipaleControleur.saveAndclose();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});

		noBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				closeyesNoStage();
				try {
					vuePrincipaleControleur.closeApp();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	
	}

	public void showYesNo(Region rootPane) {
		Scene scene = new Scene(rootPane, rootPane.getPrefWidth(), rootPane.getPrefHeight());
		this.yesNoStage = new Stage();
		this.yesNoStage.setTitle("Sauvegarde du Fichier");
		this.yesNoStage.setScene(scene);
		this.yesNoStage.show();
	}
	private void closeyesNoStage() {
		yesNoStage.close();
	}

}
