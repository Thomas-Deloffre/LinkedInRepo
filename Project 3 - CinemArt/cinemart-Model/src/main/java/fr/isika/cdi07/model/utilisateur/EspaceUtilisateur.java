package fr.isika.cdi07.model.utilisateur;

import fr.isika.cdi07.model.projet.Projet;

public class EspaceUtilisateur {

	private Long donateur;
	private Long projet;

	public EspaceUtilisateur(Utilisateur donateur, Projet projet) {
		this.donateur = donateur.getIdUtilisateur();
		this.projet = projet.getIdProjet();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EspaceUtilisateur other = (EspaceUtilisateur) obj;
		if (donateur == null) {
			if (other.donateur != null)
				return false;
		} else if (!donateur.equals(other.donateur))
			return false;
		if (projet == null) {
			if (other.projet != null)
				return false;
		} else if (!projet.equals(other.projet))
			return false;
		return true;
	}

}