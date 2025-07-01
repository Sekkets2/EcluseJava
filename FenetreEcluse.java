import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.lang.Thread;

public class FenetreEcluse extends JFrame {
	// FenetreEcluse version séparer
	public static JPanel PanelCenter = new JPanel();
	JButton DescendreAval;
	JButton CreerBateauAmont;
	JButton CreerBateauAval;
	JButton MonterAmont;
	String[] VitEau = {"Vitesse de l'eau...","Lent","Moyen","Rapide","Maximum"};
	JComboBox VitesseEau;
	int VitesseBoucleEau = 20;
	int xfen = 0;
	int yfen = 0;
	GestionEcluseLogique gestionnaire = new GestionEcluseLogique(this);

	FenetreEcluse() {
		super("Fonctionnement d'une ecluse");
		setFocusable(true);
		setSize(1200, 1000);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(BarreMenu(), BorderLayout.NORTH);
		this.getContentPane().add(CreationPanEcluse(), BorderLayout.SOUTH);
		setVisible(true);
	}

	private JPanel BarreMenu() { /// CREATION BARRE MANIPULATION ECLUSE

		JPanel Menu = new JPanel();


		Menu.setBackground(Color.red);
		Menu.setLayout(new BoxLayout(Menu, BoxLayout.X_AXIS));
		Menu.setPreferredSize(new Dimension(1200, 100));

		MonterAmont = new JButton("Valve Amont");
		Menu.add(MonterAmont);

		DescendreAval = new JButton("Valve Aval");
		Menu.add(DescendreAval);

		CreerBateauAmont = new JButton("Ajouter bateau Amont");
		Menu.add(CreerBateauAmont);

		CreerBateauAval = new JButton("Ajouter bateau Aval");
		Menu.add(CreerBateauAval);
		
		VitesseEau = new JComboBox(VitEau);
		Dimension tailleBox = new Dimension(150, 26);
		VitesseEau.setMaximumSize(tailleBox);
		Menu.add(VitesseEau);
		///////////////////////// ECOUTEUR EVENEMENT ///////////////////////////////////

		CreerBateauAmont.addActionListener( /// ECOUTEUR D'ACTION CREATION BATEAU AMONT

				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Bateau bateauAmont = gestionnaire.CreerBateauEnAmont();
						if(bateauAmont != null) {
							CreerBateauAval.setEnabled(false);
							PanelCenter.add(bateauAmont);
							gestionnaire.batAmont = bateauAmont;		
							repaint();
						requestFocusInWindow(); // Renvoie le focus à la fenêtre pour le KeyListener
					}else {
						System.out.println("Bateau déjà créé...");
					}
					}
				});

		CreerBateauAval.addActionListener( /// ECOUTEUR D'ACTION CREATION BATEAU AVAL

				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
								Bateau bateauAval = gestionnaire.CreerBateauEnAval();
								if(bateauAval != null) {
								CreerBateauAmont.setEnabled(false);
								PanelCenter.add(bateauAval);
								gestionnaire.batAval = bateauAval;
								repaint();
								requestFocusInWindow(); // Renvoie le focus à la fenêtre pour le KeyListener
						}else {
							System.out.println("Bateau déjà créé...");
						}
						}
				});

		MonterAmont.addActionListener(

				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MonterAmont.setEnabled(false);
						DescendreAval.setEnabled(false);
						Thread MonteeNiveauEau = new Thread(new Runnable() {

							public void run() {
								gestionnaire.FermerLesPortes(); // Ferme les portes
								while (gestionnaire.MonteeNiveauDeLeau() == true) { // Tant que le niveau n'est pas
																					// atteint
									gestionnaire.GestionFeuxEcluse(); /// Gère les feux
									gestionnaire.SolEcluseAmont.ModifCouleurTuyauAmont(gestionnaire.EauAscenseur,
											gestionnaire.EauAmont);
									gestionnaire.MonterBateau();
									SwingUtilities.invokeLater(new Runnable() {
										public void run() {
											gestionnaire.EauAscenseur.setBounds(gestionnaire.EauAscenseur.getX(),
													gestionnaire.EauAscenseur.getposEauY(),
													gestionnaire.EauAscenseur.getWidth(),
													gestionnaire.EauAscenseur.getHeight());
											repaint(); // Redessine
										}
									});

									try {
										Thread.sleep(VitesseBoucleEau); // Vitesse de l'ascenseur
									} catch (InterruptedException err) {
										err.setStackTrace(null);
									}
								}
								MonterAmont.setEnabled(true);
								DescendreAval.setEnabled(true);
							}
						});
						MonteeNiveauEau.start();
						requestFocusInWindow(); // Renvoie le focus à la fenêtre
					}
				});

		DescendreAval.addActionListener(

				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MonterAmont.setEnabled(false);
						DescendreAval.setEnabled(false);
						Thread DescendreNiveauEau = new Thread(new Runnable() {

							public void run() {
								gestionnaire.FermerLesPortes();
								while (gestionnaire.DescendreNiveauDeLeau() == true) {
									gestionnaire.GestionFeuxEcluse();
									gestionnaire.DescendreBateau();
									gestionnaire.SolEcluseAv.ModifCouleurTuyauAval(gestionnaire.EauAscenseur,
											gestionnaire.EauAval);
									SwingUtilities.invokeLater(new Runnable() {
										public void run() {
											gestionnaire.EauAscenseur.setBounds(gestionnaire.EauAscenseur.getX(),
													gestionnaire.EauAscenseur.getposEauY(),
													gestionnaire.EauAscenseur.getWidth(),
													gestionnaire.EauAscenseur.getHeight());
											repaint();
										}
									});

									try {
										Thread.sleep(VitesseBoucleEau);
									} catch (InterruptedException err) {
										err.setStackTrace(null);
									}
								}
								MonterAmont.setEnabled(true);
								DescendreAval.setEnabled(true);
							}
						});
						DescendreNiveauEau.start();
						requestFocusInWindow(); // Renvoie le focus à la fenêtre pour le KeyListener
					}
				});

		VitesseEau.addActionListener(
				
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String VitesseChoisi = (String) VitesseEau.getSelectedItem();
						
							switch (VitesseChoisi) {
							case "Lent":
								System.out.println("Lent");
								VitesseBoucleEau = 500;
								System.out.println(VitesseBoucleEau);
									break;
							case "Moyen": 
								System.out.println("Moyen");
								VitesseBoucleEau = 30;
								System.out.println(VitesseBoucleEau);
									break;
							case "Rapide": 
								System.out.println("Rapide");
								VitesseBoucleEau = 10;
								System.out.println(VitesseBoucleEau);
									break;
							case "Maximum": 
								System.out.println("Maximum");
								VitesseBoucleEau = 5;
								System.out.println(VitesseBoucleEau);
									break;
							default: VitesseBoucleEau = 5;
									break;
							}
					}
				}
	);
		
		return Menu;
	}

	private JPanel CreationPanEcluse() { /// CREATION SOL DE L'ECLUSE
		JPanel PanelS = new JPanel();
		PanelS.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		PanelS.add(CreationEcluse(), gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		PanelS.add(gestionnaire.SolEcluseAmont, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		PanelS.add(gestionnaire.SolEcluseAv, gbc);

		return PanelS;
	}

	private JPanel CreationEcluse() { /// CREATION DE l'ECLUSE -> PORTE, EAU
		PanelCenter.setLayout(null);
		PanelCenter.setPreferredSize(new Dimension(1184, 800));

		PanelCenter.add(gestionnaire.EauAmont);
		PanelCenter.add(gestionnaire.PorteAmont);
		PanelCenter.add(gestionnaire.EauAscenseur);
		PanelCenter.add(gestionnaire.PorteAval);
		PanelCenter.add(gestionnaire.EauAval);
		return PanelCenter;
	}

	public void AcCreationBateauAval() {
		CreerBateauAval.setEnabled(true);
	}
	public void AcCreationBateauAmont() {
		CreerBateauAmont.setEnabled(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paint(g);
	}

}
