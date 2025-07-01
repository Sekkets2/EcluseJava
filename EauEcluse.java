import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class EauEcluse extends JPanel {

	private int posY = 0;
	private int posX = 0;
	private int Width = 0;
	private int Height = 0;
	private int EauY = 0;
	public int VitEauAsc = 2;
	

	EauEcluse(int posX, int posY, int Width, int Height) {
		this.posX = posX;
		this.EauY = posY;
		this.posY = posY;
		this.Width = Width;
		this.Height = Height;
	    setBounds(posX, posY, Width, Height);

	}

	public void paintComponent(Graphics gEau) {
		super.paintComponent(gEau);
		gEau.setColor(Color.BLUE);
		gEau.fillRect(0, 0, Width, Height);
	}


	public int getposEauY() {
		return EauY;
	}
	

	public int getposY() {
		return posY;
	}
	
	public void setposY(int Y) {
		this.posY = Y;
	}
	public void setposEauY(int Y) {
		this.EauY = Y;
	}

	public int getposX() {
		return posX;
	}
	
	public void setposX(int X) {
		this.posX = X;
	}
}
