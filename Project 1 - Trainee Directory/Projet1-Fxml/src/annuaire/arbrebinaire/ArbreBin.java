package annuaire.arbrebinaire;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ArbreBin<E extends Comparable<E>> {
	public static class Noeud<T> {
		private T clef;
		private Noeud<T> gauche;
		private Noeud<T> droit;
		private Long posD; // 0x0 0x1 0xF 
		private Long posG; //             
		private Long pos;

		public Noeud<T> getGauche() {
			return gauche;
		}

		public void setGauche(Noeud<T> noeud) {
			this.gauche = noeud;
		}

		public Noeud<T> getDroit() {
			return droit;
		}

		public void setDroit(Noeud<T> droit) {
			this.droit = droit;
		}

		public T getClef() {
			return clef;
		}

		public void setClef(T stagiaire) {
			this.clef = stagiaire;
		}

		public Long getPosD() {
			return posD;
		}

		public void setPosD(Long posD) {
			this.posD = posD;
		}

		public Long getPosG() {
			return posG;
		}

		public void setPosG(Long posG) {
			this.posG = posG;
		}

		public Long getPos() {
			return pos;
		}

		public void setPos(Long pos) {
			this.pos = pos;
		}

		public Noeud() {
			super();
			clef = null;
			gauche = null;
			droit = null;
			posG = (long) -1;
			posD = (long) -1;
			pos = (long) -1;
		}

		public Noeud(T clef) {
			super();
			this.clef = clef;
			gauche = null;
			droit = null;
			posG = (long) -1;
			posD = (long) -1;
			pos= (long) -1;
		}

		@Override
		public String toString() {
			return "Noeud [clef=" + clef + "]";
		}
	}

	private List<Stagiaires> listeStagiaires = new ArrayList<Stagiaires>();
	private Noeud<E> racine;

	public void lireFichierBin(String CheminFichier) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(CheminFichier, "r");
		this.setRacine(ReadNode(raf, (long) 0));
		
	}

	public void setRacine(Noeud<E> racine) {
		this.racine = racine;
	}

	public Noeud<E> getRacine() {
		return racine;
	}

	public Noeud<E> ReadNode(RandomAccessFile raf, Long pos) throws IOException {
		Noeud<E> n = readAndCreateNoeud(raf, pos);
		if (n.getPosG() != -1) {
			n.setGauche(ReadNode(raf, n.getPosG())); // C'est un parcours infixe ! 
		} 
		listeStagiaires.add((Stagiaires) n.clef);
		if (n.getPosD() != -1) {	
			n.setDroit(ReadNode(raf, n.getPosD()));
		}
		return n;
	}

	
	// 139 = 5 * (23 * 2) + 3 * 8 = (nb attributes stagiaire) * (size attribute stagiaire * sizeof(char)) + (nb pos) * sizeof(long)
	public void ajouterNoeud(E valeur, RandomAccessFile raf) throws IOException {
		long positionValeur = raf.length() / 139; 
		Noeud<E> courant = racine;
		if (courant == null) {
			racine = new Noeud<E>(valeur);
			racine.setClef(valeur);
			racine.setPosG((long)-1);
			racine.setPosD((long)-1);
			racine.setPos((long)0);
			ecritureNoeud((Noeud<E>)racine, raf, positionValeur);
		}
		else {
			boolean trouve = false;
			while (!trouve) { // !true = false et !false = true
				int test = valeur.compareTo(courant.clef);
				if (test == 0) {
					trouve = true;					
				} else if (test < 0) {
					if (courant.gauche == null) {
						raf.seek((courant.pos*139)+115);
						raf.writeLong( positionValeur);						
						Noeud<E> noeudAjout = new Noeud<E>(valeur);
						noeudAjout.setPos(positionValeur);
						noeudAjout.setPosG((long)-1);
						noeudAjout.setPosD((long)-1);
						courant.gauche = noeudAjout;
						ecritureNoeud(noeudAjout, raf, positionValeur);
						courant.setPosG(positionValeur);
						trouve = true;
					} else {
						courant = courant.gauche;
					}
				} else {
					if (courant.droit == null) {
						raf.seek((courant.pos * 139) + 123 );
						raf.writeLong(positionValeur);
						Noeud<E> noeudAjout = new Noeud<E>(valeur);
						noeudAjout.setPos(positionValeur);
						noeudAjout.setPosG((long)-1);
						noeudAjout.setPosD((long)-1);
						courant.droit = noeudAjout;
						courant.setPosD(positionValeur);
						ecritureNoeud(noeudAjout, raf, positionValeur);
						trouve = true; //
					} else {
						courant = courant.droit;
					}
				}
			}
		}
	}

	public void ecrireArbreBin(String CheminFichier) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(CheminFichier, "rw");
		raf.setLength(0);
		DumpArbreBinprefixe(this.getRacine(), raf);
	}

	public void DumpArbreBinprefixe(Noeud<E> r, RandomAccessFile raf) throws IOException {
		if (r != null) {
			r.pos = raf.length() / 139;
			ecritureNoeud(r, raf, r.pos);
			if (r.gauche != null) {
				r.posG = raf.length() / 139;
				DumpArbreBinprefixe(r.gauche, raf);
			}
			raf.seek(raf.length());
			if (r.droit != null) {
				r.posD = raf.length() / 139;
				DumpArbreBinprefixe(r.droit, raf);
			}
			ecritureNoeud(r, raf, r.pos);
		}
	}

	public void infixe(Noeud<E> r) {
		if (r != null) {
			infixe(r.gauche);
			System.out.println(r.clef + " , ");
			infixe(r.droit);
		}
	}

	public void postfixe(Noeud<E> r) {
		if (r != null) {
			postfixe(r.gauche);
			postfixe(r.droit);
			System.out.println(r.clef + " , ");
		}
	}

	public Noeud<E> rechercher(Noeud<E> noeud, E valRech) {
		Noeud<E> courant = noeud;
		while (valRech.compareTo(courant.clef) != 0) {
			if (valRech.compareTo(courant.clef) < 0) {
				courant = courant.gauche;
			} else {
				courant = courant.droit;
			}
			if (courant == null)
				return null;
		}
		return courant;
	}

	public E recherche(Noeud<E> Noeud, E ValRech) {
		Noeud<E> no = rechercher(Noeud, ValRech);
		if (no != null)
			return no.clef;
		return null;
	}
	

	public void ecritureStagiaire(Stagiaires st, RandomAccessFile fichierBinaireRaf) {
		try {
		
			fichierBinaireRaf.seek(fichierBinaireRaf.length());
			fichierBinaireRaf.writeChars(st.nom);

			fichierBinaireRaf.writeChars(st.prenom);			
			fichierBinaireRaf.writeChars(st.departement);		
			fichierBinaireRaf.writeChars(st.formation);			
			fichierBinaireRaf.writeChars(st.annee);

			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void ecritureNoeud(Noeud<E> n, RandomAccessFile raf, Long pos) {
		try {
			raf.seek(pos * 139);
			write(raf, (Stagiaires) n.clef);
			raf.writeLong(n.posG); // 115
			raf.writeLong(n.posD); // 123
			raf.writeLong(n.pos); // 131
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Noeud<E> readAndCreateNoeud(RandomAccessFile raf, Long pos) throws IOException {

		raf.seek(pos* 139);
		Noeud<E> node = new Noeud<E>();

		Stagiaires st = Read(raf, (int)(pos*139));
		node.setClef((E) st);

		long posg = raf.readLong();
		long posd = raf.readLong();
		long posi = raf.readLong();
		node.setPosG(posg);
		node.setPosD(posd);
		node.setPos(posi);
		return node;
	}

	public String ReadEntry(RandomAccessFile raf, int size) throws IOException {
		String entry = "";
		for (int i = 0; i < size; i++) {
			char c = (char) raf.readByte();
			entry = entry + c;			
		}
		String tmp = entry.trim();
		return tmp;
	}

	public Stagiaires Read(RandomAccessFile raf, int position) throws IOException {


		//		for(int position = 0;position<raf.length();position+=110) {
		raf.seek(position);
		Stagiaires stagiaire = new Stagiaires();
		String name = ReadEntry(raf, 23);
		stagiaire.setNom(name);
		String prenom = ReadEntry(raf, 23);
		stagiaire.setPrenom(prenom);
		String departement = ReadEntry(raf, 23);
		stagiaire.setDepartement(departement);
		String formation = ReadEntry(raf, 23);
		stagiaire.setFormation(formation);
		String annee = ReadEntry(raf,  23);
		stagiaire.setAnnee(annee);

		return stagiaire;
	}


	public int write(RandomAccessFile raf, Stagiaires stagiaire) throws IOException {
		raf.writeBytes(remplacerPar(stagiaire.nom, 23));
		raf.writeBytes(remplacerPar(stagiaire.prenom, 23));
		raf.writeBytes(remplacerPar(stagiaire.departement, 23));
		raf.writeBytes(remplacerPar(stagiaire.formation, 23));
		raf.writeBytes(remplacerPar(stagiaire.annee, 23));
		return 0;
	}

	public String remplacerPar(String chaine, int taille) {
		//System.out.println("chaine was \""+ chaine + "\"");
		int size = taille - chaine.length();
		for (int i = 0; i < size; i++) {
			chaine += " ";
		}
		//System.out.println("chaine is \"" + chaine + "\"");

		return chaine;
	}

	public List<E> getListeStagiaires() {
		List<E> l = new ArrayList<E>();
		generateList(racine, l);
		return l;
	}

	public void generateList(Noeud<E> n, List<E> l) {
		if (n != null) {
			generateList(n.gauche, l);
			l.add(n.getClef());
			generateList(n.droit, l);
		}
	}

	public Noeud<E> getMax(Noeud<E> n) {
		Noeud<E> tmp = n;
		while (tmp.droit != null) {
			tmp = tmp.droit;
		}
		return tmp;
	}
	
	public Noeud<E> replaceNode(Noeud<E> n) {
		if (n.droit == null && n.gauche == null) {
			System.out.println("Return null to delete node");
			return null;
		}
		if (n.droit == null && n.gauche != null) {
			System.out.println("Return gauche to delete node");
		    return n.gauche;
		}
		if (n.droit != null && n.gauche == null) {
			System.out.println("Return droit to delete node");
			return n.droit;
		}
		System.out.println("remove max gauche to delete node");
		Noeud<E> max = getMax(n.gauche);
		n.setClef(max.getClef());
		n.gauche = deleteNode(n.gauche, n.getClef());
		return n;
	}
	
	public Noeud<E> deleteNode(Noeud<E> n, E value) {
		if (n == null) {
			return null;
		}
		int tmp = n.clef.compareTo(value);
		if (tmp == 0) {
            return replaceNode(n);
		} else if (tmp > 0) {
			n.gauche = deleteNode(n.gauche, value);
		} else {
			n.droit = deleteNode(n.droit, value);		
		}
		return n;
	}
	
	public void deleteStagiaire(E value) {
	   racine = deleteNode(racine, value);
	}

	public void ajouterNoeudApp(E valeur) throws IOException {

		Noeud<E> courant = racine;
		if (courant == null) {
			racine = new Noeud<E>(valeur);
			racine.setClef(valeur);

		}
		else {
			boolean trouve = false;
			while (!trouve) {
				int test = valeur.compareTo(courant.clef);
				if (test == 0) {
					trouve = true;						
				} else if (test < 0) {
					if (courant.gauche == null) {

						Noeud<E> noeudAjout = new Noeud<E>(valeur);

						courant.gauche = noeudAjout;						

						trouve = true;
					} else {
						courant = courant.gauche;
					}
				} else {
					if (courant.droit == null) {

						Noeud<E> noeudAjout = new Noeud<E>(valeur);

						courant.droit = noeudAjout;

						trouve = true; //
					} else {
						courant = courant.droit;
					}
				}
			}
		}
	}


}