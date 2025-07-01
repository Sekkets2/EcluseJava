import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GestionEcluseLogique {

	public FenetreEcluse Fenetre;
	public boolean BateauAmontCreer = false;
	public boolean BateauAvalCreer = false;
	public EauEcluse EauAscenseur;
	public EauEcluse EauAmont;
	public EauEcluse EauAval;
	public PorteEcluse PorteAmont; // MODIF A 50 LE 100
	public PorteEcluse PorteAval; // MODIF A 50 LE 100
	public SolEcluse SolEcluseAmont = new SolEcluse(0, 434, 100);
	public SolEcluseAval SolEcluseAv = new SolEcluseAval(50, 750, 100);
	public Bateau batAmont;
	public Bateau batAval;
	private int VitesseBoucle;

	public GestionEcluseLogique(FenetreEcluse Fenetre) {
		this.Fenetre = Fenetre;
		EauAscenseur = new EauEcluse(433, 610, 200, 300);
		EauAmont = new EauEcluse(0, 500, 383, 310);
		EauAval = new EauEcluse(683, 750, 501, 50);
		PorteAmont = new PorteEcluse(383, 300, 50, 500); // MODIF A 50 LE 100
		PorteAval = new PorteEcluse(633, 350, 50, 450); // MODIF A 50 LE 100
	}
	public GestionEcluseLogique() {
		EauAscenseur = new EauEcluse(433, 610, 200, 300);
		EauAmont = new EauEcluse(0, 500, 383, 310);
		EauAval = new EauEcluse(683, 750, 501, 50);
		PorteAmont = new PorteEcluse(383, 300, 50, 500); // MODIF A 50 LE 100
		PorteAval = new PorteEcluse(633, 350, 50, 450); // MODIF A 50 LE 100
	}
	
	public void FermerLesPortes() {

			PorteAmont.fermerPorte();
	    	PorteAval.fermerPorte();
	}
	
	public boolean DeplacerBateauAmontVersAscenseur(Bateau bat) {
		while(bat.MoteurEnPannee == false) {
		if(PorteAmont.FeuVert == true && PorteAmont.PorteOuverte == true) {
		if (bat.getposXBa() < 450) {
				bat.setposXBa(bat.getposXBa() + 1);
				ChoixVitesseBoucle(bat);
			try {
				Thread.sleep(VitesseBoucle);
			} 	catch (InterruptedException err) {
				err.printStackTrace();
			}
			return true;
			}
		}
		return false;
		}
		return false;
			
	}
	
	public boolean DeplacerBateauAmontAscenseurVersAval(Bateau bat) {
		while(bat.MoteurEnPannee == false) {
		if(PorteAval.FeuVert == true && PorteAval.PorteOuverte == true) {
		if (bat.getposXBa() >= 450 && bat.getposXBa() < 1200) {

			bat.setposXBa(bat.getposXBa() + 1);
			ChoixVitesseBoucle(bat);
		try {  
			Thread.sleep(VitesseBoucle);
		} 	catch (InterruptedException err) {
			err.printStackTrace();
		}
		return true;
		
	}
		}
		return false;
		}
		return false;
	}
	
	public boolean DeplacerBateauAvalVersAs(Bateau bat) {
		while(bat.MoteurEnPannee == false) {
		if(PorteAval.FeuVert == true && PorteAval.PorteOuverte == true) {
		if (bat.getposXBa() >= 450 && bat.getposXBa() < 1200 ) {
			bat.setposXBa(bat.getposXBa() - 1);
			ChoixVitesseBoucle(bat);
			try {
				Thread.sleep(VitesseBoucle);
			} 	catch (InterruptedException err) {
				err.printStackTrace();
			}
			return true;
		}	
		}
		return false;
		}
		return false;
	}
	
	public boolean DeplacerBateauAvalAscVersAmont(Bateau bat) {
		while(bat.MoteurEnPannee == false) {
		if(PorteAmont.FeuVert == true && PorteAmont.PorteOuverte == true && bat.getposXBa()<500) {
		if (bat.getposXBa() >= -155) {
			bat.setposXBa(bat.getposXBa() - 1);
			ChoixVitesseBoucle(bat);
			try {
				Thread.sleep(VitesseBoucle);
			} 	catch (InterruptedException err) {
				err.printStackTrace();
			}
			return true;
		}
	}
		return false;
		}
		return false;
	}
	
	int ChoixVitesseBoucle(Bateau bat) {
	
	switch (bat.vitesse) {
	case 1:
			VitesseBoucle = 30;
			break;
	case 2: VitesseBoucle = 20;
			break;
	case 3: VitesseBoucle = 10;
			break;
	case 4: VitesseBoucle = 5;
			break;
	default: VitesseBoucle = 30;
			break;
	}
	return VitesseBoucle;
}
	public void GestionFeuxEcluse() {
		if (EauAscenseur.getposEauY() == EauAmont.getposY()) {
			PorteAmont.ouvrirPorte();
			PorteAmont.FeuVert = true;
			}
		else if (EauAscenseur.getposEauY() == EauAval.getposY()) {
			PorteAval.ouvrirPorte();
			PorteAval.FeuVert = true;
			}
		else {
			PorteAval.FeuVert = false;
			PorteAmont.FeuVert = false;
			}
	}
	
	public boolean MonteeNiveauDeLeau() {
	    int j = EauAscenseur.getposEauY();

	    if (j > EauAmont.getposY()) {
	        j--;
	        EauAscenseur.setposEauY(j); 
	        return true; 
	    } else {
	        return false; 
	    }
	}
	public boolean DescendreNiveauDeLeau() {           /// Contient la descente de l'eau, l'ouverture des portes et la gestion des feux
	    int j = EauAscenseur.getposEauY();

	    if (j < EauAval.getposY()) {
	        j++;
	        EauAscenseur.setposEauY(j); 
	        return true; 
	    } else {
	        return false; 
	    }
	}        
	
	public void DescendreBateau() {
		if(batAmont != null) {
			if (batAmont.getposXBa() >= 425 && batAmont.getposXBa() <= 583) {
				batAmont.setposYBa(batAmont.getposYBa() + 1);
				batAmont.setBounds(batAmont.getposXBa(), batAmont.getposYBa(), batAmont.getWidth(),batAmont.getHeight());
			}
		}
		if(batAval != null) {
			if (batAval.getposXBa() >= 425 && batAval.getposXBa() <= 583) {
				batAval.setposYBa(batAval.getposYBa() + 1);
				batAval.setBounds(batAval.getposXBa(), batAval.getposYBa(),batAval.getWidth(), batAval.getHeight());
						
			}
			else {return;};
		}
	}

	public void MonterBateau() {
		if(batAmont != null) {
			if (batAmont.getposXBa() >= 425 && batAmont.getposXBa() <= 583) {
				batAmont.setposYBa(batAmont.getposYBa() - 1);
				batAmont.setBounds(batAmont.getposXBa(), batAmont.getposYBa(), batAmont.getWidth(),batAmont.getHeight());
			}
		}
		if(batAval != null) {
			if (batAval.getposXBa() >= 425 && batAval.getposXBa() <= 583) {
				batAval.setposYBa(batAval.getposYBa() - 1);
				batAval.setBounds(batAval.getposXBa(), batAval.getposYBa(),batAval.getWidth(), batAval.getHeight());
						
			}
			else {return;};
		}
	}

	public void SupprimerBateau(Bateau bat, JPanel pan) {
		if(bat.getposXBa() >= 1200) {
			pan.remove(bat);
			bat.G.dispose();
			System.out.println("Suppression bateau amont");
			BateauAmontCreer = false;
			bat.setposXBa(50);
			bat.setposYBa(350);
			Fenetre.CreerBateauAval.setEnabled(true);
			bat.repaint();
		}
		if(bat.getposXBa() < -139) {
			pan.remove(bat);
			bat.G.dispose();
			BateauAvalCreer = false;
			bat.setposXBa(950);
			bat.setposYBa(660);
			Fenetre.CreerBateauAmont.setEnabled(true);
			bat.repaint();
		}
	}	
	
	public void PanneMoteurBateau(Bateau bat, int min, int max) { 
		int NombreRDM = 0;
		int plage = (max - min) + 1;
		NombreRDM = (int) (plage * Math.random()) + min;   // Nombre aleatoire entier entre Max et Min.
		if(NombreRDM == 5) {
			System.out.println("Moteur tombe en panne");
			bat.MoteurEnPannee = true;
		}
	}

	public void afficherbateauamontecluselogique() {
		System.out.println("batAmont"+batAmont);
	}
	
	public Bateau CreerBateauEnAval() {
		
		if (BateauAvalCreer == false) {
			this.batAval = new Bateau(950, 660, "Aval", this);
			BateauAvalCreer = true;
			return batAval;
		}
		return null;
	}
	
	public Bateau CreerBateauEnAmont() {

		if (BateauAmontCreer == false) {
			this.batAmont = new Bateau(50, 350, "Amont", this);
			BateauAmontCreer = true;
			return batAmont;
		}
			return null;
	}
	

}
