import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class SolEcluseAval extends JPanel {
	
	private int posY = 0;
	Color Marron = new Color(0.4f, 0.3f, 0);
	public Color couleurTuyau = Color.white;

	SolEcluseAval(int posY, int Width, int Height) {
		this.posY = posY;
		this.setPreferredSize(new Dimension(Width, Height));
	}
	
	public void ModifCouleurTuyauAval(EauEcluse EauAscenseur, EauEcluse EauAval ) {
		if(EauAscenseur.getposEauY() != EauAval.getposY())
		{this.couleurTuyau = Color.blue;}
		else this.couleurTuyau = Color.white;
	}
	
	public void paintComponent(Graphics gSol) {
		super.paintComponent(gSol);
		int[] TuyauX = {175,190,190,260,260,275,275,175};
		int[] TuyauY = {posY,posY,posY+20,posY+20,posY,posY,posY+35,posY+35};
		gSol.setColor(Color.gray);
		gSol.fillRect(0, posY, this.getWidth(), this.getHeight() / 2);
		gSol.setColor(Color.blue);
		gSol.fillRect(0, 0, this.getWidth(), this.getHeight() / 2);
		gSol.setColor(Marron);
		gSol.fillRect(199, 0, 50, this.getHeight() / 2);
		gSol.setColor(Color.black);
		gSol.drawPolyline(TuyauX, TuyauY, 8);
		gSol.setColor(couleurTuyau);
		gSol.fillPolygon(TuyauX, TuyauY, 8);
	}
}
