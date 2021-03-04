package annuaire.arbrebinaire;

public class Stagiaires implements Comparable<Stagiaires> {

	public String nom;
	public String prenom; 
	public String departement;
	public String formation;
	public String annee;



	public Stagiaires() {
		super();
	}

	public Stagiaires(String nom, String prenom, String departement, String formation,
			String annee) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.annee = annee;
		this.formation = formation;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}



	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}


	@Override
	public String toString() {

		final StringBuilder attributs = new StringBuilder("Stagiaire [");

		attributs.append("nom= ").append(nom);
		attributs.append(",  prenom= ").append(prenom);
		attributs.append(",  departement= ").append(departement);
		attributs.append(",  formation= ").append(formation);
		attributs.append(",  annee= ").append(annee);
		attributs.append(']');

		return attributs.toString();
	}

	public int compareTo (Stagiaires other) {
		
		System.out.println("Compare " + this + " with " + other);

		int tmpName = this.nom.compareToIgnoreCase(other.nom);
		if (tmpName != 0) {
			System.out.println("tmpName is " + Integer.toString(tmpName));
			return tmpName;
		}
		int tmpForename = this.prenom.compareToIgnoreCase(other.prenom);
		if (tmpForename != 0) {
			System.out.println("tmpForename is " + Integer.toString(tmpForename));
			return tmpForename;		
		}
		int tmpDep = this.departement.compareToIgnoreCase(other.departement);
		if (tmpDep != 0) {
			System.out.println("tmpForename is " + Integer.toString(tmpDep));
			return tmpDep;
		}
		int tmpForm = this.formation.compareToIgnoreCase(other.formation);
		if (tmpForm != 0) {
			System.out.println("tmpForm is " + Integer.toString(tmpForm));
			return tmpForm;
		}
		int tmpYear = this.annee.compareToIgnoreCase(other.annee);
		System.out.println("tmpYear is " + Integer.toString(tmpYear));
		return tmpYear;
	}


}
