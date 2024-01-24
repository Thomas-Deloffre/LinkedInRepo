package fr.isika.cdi07.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.isika.cdi07.dao.ProfilRepository;
import fr.isika.cdi07.dao.UtilisateurRepository;
import fr.isika.cdi07.model.utilisateur.Profil;
import fr.isika.cdi07.model.utilisateur.Utilisateur;

@Service
public class UtilisateurService implements IUtilisateurService {

	@Autowired
	private UtilisateurRepository utilisateurRepo;
	@Autowired
	private ProfilRepository profilRepo;

	public UtilisateurService(UtilisateurRepository uRepo) {
		this.utilisateurRepo = uRepo;
	}

	public Utilisateur createUtilisateur(String pseudo, String motDePasse, String nom,
			String prenom, String email){
		Utilisateur utilisateur;
		if(motDePasse.isEmpty()){
			throw new IllegalArgumentException("Impossible d'ajouter un utilisateur sans mdp!");
		} else if(pseudo.isEmpty()) {
			throw new IllegalArgumentException("Impossible d'ajouter un utilisateur sans login!");
		}
		else {
			utilisateur = recupereUtilisateur(pseudo, motDePasse);
			if(utilisateur == null) {		
				Profil profil = new Profil(nom, prenom, email);
				profilRepo.save(profil);
				utilisateur = new Utilisateur(pseudo, motDePasse, profil);
				utilisateurRepo.save(utilisateur);
				profil.setUtilisateur(utilisateur);
				profilRepo.save(profil);
				utilisateur = recupereUtilisateur(pseudo, motDePasse);
			}
			else {
				System.out.println("L'utilisateur : " + utilisateur.getPseudo() + " avec le mdp : " + utilisateur.getMotDePasse()
				+ " existe deja!!");
			}
		}
		return utilisateur;
	}
	
	public Utilisateur modifierUtilisateur(Utilisateur utilisateur) {
		profilRepo.save(utilisateur.getProfil());
		utilisateurRepo.save(utilisateur);
		return utilisateur;
	}
	
	public Utilisateur recupereUtilisateur(String pseudo, String motDePasse) {
		List<Utilisateur> listUtilisateur = (List<Utilisateur>) utilisateurRepo.findByPseudo(pseudo);
		Utilisateur utilisateur = null;
		if(listUtilisateur.size() != 0) {
			utilisateur = listUtilisateur.get(0);
		}
		return utilisateur;
	}

	@Override
	public List<Utilisateur> getAllUtilisateur() {
		return (List<Utilisateur>) utilisateurRepo.findAll(); // tu definis la nature de lobje
	}


}