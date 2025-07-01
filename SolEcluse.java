import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class SolEcluse extends JPanel {

	private int posY = 0;
	public Color couleurTuyau = Color.white;

	SolEcluse(int posY, int Width, int Height) {
		this.posY = posY;
		this.setPreferredSize(new Dimension(Width, Height));
	}
	
	public void ModifCouleurTuyauAmont(EauEcluse EauAscenseur, EauEcluse EauAmont ) {
		if(EauAscenseur.getposEauY() != EauAmont.getposY())
		{this.couleurTuyau = Color.blue;}
		else this.couleurTuyau = Color.white;
	}
	public void paintComponent(Graphics gSol) {
		super.paintComponent(gSol);
		int[] TuyauX = {320,335,335,434,434,320};
		int[] TuyauY = {posY,posY,posY+20,posY+20,posY+35,posY+35};
		gSol.setColor(Color.gray);
		gSol.fillRect(0, posY, this.getWidth(), this.getHeight());  // Sol
		
		gSol.setColor(Color.black);
		gSol.drawPolyline(TuyauX, TuyauY, 6);
		gSol.setColor(couleurTuyau);
		gSol.fillPolygon(TuyauX, TuyauY, 6);
	}
}
