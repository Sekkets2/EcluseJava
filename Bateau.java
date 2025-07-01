import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Bateau extends JLabel {
	private int posXBa = 0;
	private int posYBa = 0;
	public String Position;
	public boolean MoteurEnPannee = false;
	 int FenetreCompx = 0;
	 int FenetreCompy = 0;
	 int vitesse;
	 int VitesseBoucle;
	 GestionComposantBateau G;

	Bateau(int posXBa, int posYBa, String Position, GestionEcluseLogique gestionnaire) {
		this.posXBa = posXBa;
		this.posYBa = posYBa;
		this.Position = Position;

		setBounds(posXBa, posYBa, 160, 160);
		
		if(Position.equals("Amont")) {	
		ImageIcon icon = new ImageIcon("Boat.png");
		setIcon(icon);
		}
		else if(Position == "Aval") {
			ImageIcon icon = new ImageIcon("BoatAval.png");
			setIcon(icon);
		}
		
		G = new GestionComposantBateau(FenetreCompx,FenetreCompy, this, gestionnaire);
		this.addMouseListener(

				new MouseAdapter() {

					public void mouseClicked(MouseEvent ev) {
						FenetreCompx =  ev.getLocationOnScreen().x;
						FenetreCompy = ev.getLocationOnScreen().y;
						G.setLocation(FenetreCompx, FenetreCompy);
						G.setVisible(true);
					}
				});
	}

	public int getposXBa() {

		return this.posXBa;
	}

	public int getposYBa() {

		return this.posYBa;
	}

	public void setposXBa(int x) {

		this.posXBa = x;
	}

	public void setposYBa(int y) {

		this.posYBa = y;
	}

	
	public void ReActiverBoutonBateau(JButton j) {
		j.setEnabled(true);
	};
	
	public void DesactiverBouton(JButton j) {
		j.setEnabled(false);
	};

}
