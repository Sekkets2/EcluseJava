import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PorteEcluse extends JPanel {

	private int Width = 0;
	private int Height = 0;
	public int posX;
	public int posY;
	public boolean PorteOuverte = false;
	public boolean FeuVert = false;
	Color Marron = new Color(0.4f, 0.3f, 0);

	PorteEcluse(int posX, int posY, int Width, int Height) {
		this.posX = posX;
		this.posY = posY;
		this.Width = Width;
		this.Height = Height;
		setOpaque(false);   // Change le fond de blanc a transparent
		setBounds(posX, posY, Width, Height);

	}

	public void ouvrirPorte() {
		this.PorteOuverte = true;
	}
	
	public void fermerPorte() {
		this.PorteOuverte = false;
	}
	
	public void paintComponent(Graphics gPorte) {
		super.paintComponent(gPorte);
		if(PorteOuverte == false) {
		gPorte.setColor(Marron);
		gPorte.fillRect(0, 70, Width, Height);
		gPorte.setColor(Color.black);
		}
		else if(PorteOuverte == true && this.posX <= 400) {
			gPorte.setColor(Marron);
			gPorte.fillRect(0, 200, Width, Height);
			gPorte.fillRect(0, 70, Width, 20);
			gPorte.setColor(Color.black);
		}
		else if(PorteOuverte == true && this.posX >= 600) {
			gPorte.setColor(Marron);
			gPorte.fillRect(0, 400, Width, Height);
			gPorte.fillRect(0, 70, Width, 200);
			gPorte.setColor(Color.black);
		}
		
			// Gestion couleur des feux  //
		int[] FeuBicoloreX = { 40, 40, 10, 10, 20, 20, 30, 30 };
		int[] FeuBicoloreY = { 45, 0, 0, 45, 45, 70, 70, 45 };
		gPorte.drawPolyline(FeuBicoloreX, FeuBicoloreY, 8);
		gPorte.fillPolygon(FeuBicoloreX, FeuBicoloreY, 8);
		if (FeuVert == false) {
			gPorte.setColor(Color.red);
		} else {
			gPorte.setColor(Color.gray);}
			gPorte.drawOval(17, 5, 15, 15);
			gPorte.fillOval(17, 5, 15, 15);

			if (FeuVert == true) {
				gPorte.setColor(Color.green);
			} else {
				gPorte.setColor(Color.gray);}
			gPorte.drawOval(17, 25, 15, 15);
			gPorte.fillOval(17, 25, 15, 15);
			
		}
	}
