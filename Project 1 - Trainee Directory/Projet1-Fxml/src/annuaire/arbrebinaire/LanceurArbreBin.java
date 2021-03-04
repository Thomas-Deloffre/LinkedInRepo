//package annuaire.arbrebinaire;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.RandomAccessFile;
//import java.nio.ByteBuffer;
//import java.nio.channels.FileChannel;
//import java.util.ArrayList;
//import annuaire.arbrebinaire.*;
//
//
//
//public class LanceurArbreBin {
//	public static final String CHEMIN_FICHIER = "src\\annuaire\\arbrebinaire\\";
//
//	public static void  main(String[] args) throws IOException {
//		
//		ArrayList<Stagiaires> listeStagiaire = new ArrayList<Stagiaires>();
//		ArbreBin<Stagiaires> arbreBinaire = new ArbreBin<Stagiaires>();
//		FileInputStream fis = null;
//		FileOutputStream fos = null;
//		try {
//			fis = new FileInputStream(CHEMIN_FICHIER + "ListeStagiaire.txt");
//			//fos = new FileOutputStream(CHEMIN_FICHIER + "ListeStagiaireSortie.txt");
//			FileReader fr = new FileReader(CHEMIN_FICHIER + "ListeStagiaire.txt");
//			RandomAccessFile raf = new RandomAccessFile(CHEMIN_FICHIER + "ListeStagiaire.bin", "rw");
//			BufferedReader br = new BufferedReader(fr);
//			{
//				//int compteur = 1; 
//				Stagiaires stagiaire = new Stagiaires();
//				while ((br.ready())) {
//					for (int i =0; i<6; i++) {
//						String brl = br.readLine();
//
//						//System.out.println(i+brl);
//
//						if (i==0)  {
//							stagiaire.setNom(brl);
//
//						}
//						if (i==1)  {	
//							stagiaire.setPrenom(brl);
//
//						}
//						if (i==2)  {							
//							stagiaire.setDepartement(brl);
//
//						}
//						if (i==3)  {							
//							stagiaire.setFormation(brl);
//
//						}
//						if (i==4)  {							
//							stagiaire.setAnnee(brl);
//
//						}
//						if (i==5) {
//
//							listeStagiaire.add(stagiaire);	
//							arbreBinaire.ajouterNoeud(stagiaire);
//							//MyScript.remplacerPar(, 20);
//							//ListeStagiaire MyScript = new ListeStagiaire();
//
//							arbreBinaire.ecritureStagiaire ( stagiaire, raf.length());
//							stagiaire = new Stagiaires ();
//							//listeStagiaire.remove(index)						}
//						}
//					}
//				}
//				br.close();
//				raf.close();
//			}
//		}
//		catch (IOException ioe) {
//			System.out.println("Erreur d'ouverture de fichier");
//			ioe.printStackTrace();
//
//		}
//		finally {
//			if (fis != null) fis.close();
//			if (fos != null) fos.close();
//		}
//
//		
//
//		//arbreBinaire.infixe(arbreBinaire.getRacine());
//		System.out.println("-----------");
//
//		System.out.println(arbreBinaire.getRacine().getClef());
//
//		arbreBinaire.ecrireArbreBin(CHEMIN_FICHIER + "ListeStagiaire.bin");
//		
//
//	}
//}