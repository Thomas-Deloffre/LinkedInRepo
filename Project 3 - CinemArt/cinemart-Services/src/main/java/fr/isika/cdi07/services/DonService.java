package fr.isika.cdi07.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.cdi07.dao.DonRepository;
import fr.isika.cdi07.dao.ProjetRepository;
import fr.isika.cdi07.dao.UtilisateurRepository;
import fr.isika.cdi07.model.don.Don;
import fr.isika.cdi07.model.don.DonStatut;
import fr.isika.cdi07.model.projet.Projet;
import fr.isika.cdi07.model.utilisateur.Utilisateur;

@Service
public class DonService {

	@Autowired
	private ProjetRepository projetRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private DonRepository donRepository;

	public DonService(UtilisateurRepository repository) {
		this.utilisateurRepository = repository;
	}
	
	public Don enregistrerDon(int montantDon, Projet projet, Utilisateur utilisateur) {
		Don don = new Don(montantDon, projet, utilisateur);
		donner(don, projet);
		donRepository.save(don);
		projetRepository.save(projet);
		return don;
	}

	public Don donner(Don don, Projet projet) {
		if(don.getMontant() < 1) {
			throw new IllegalArgumentException("Impossible de faire un don inferieur a un euro!");
		}
		else if(don.getMontant() < 1000) {
			projet.augmenteCagnotte(don.getMontant());
			don.setDonStatut(DonStatut.DONE);
		}
		else {
			projet.augmenteCagnotte(don.getMontant());
			don.setDonStatut(DonStatut.PENDING);
		}
		return don;
	}

	public List<Don> getDonUtilisateur(Long idUtilisateur) {
		 List<Don> listeDon = donRepository.findByUtilisateurIdUtilisateur(idUtilisateur);
		return listeDon;
	}
}
