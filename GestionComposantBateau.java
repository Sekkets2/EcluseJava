
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GestionComposantBateau extends JFrame {
	GestionEcluseLogique gestionnaire = new GestionEcluseLogique();
	public int vitesse;
	public JPanel PanBouton;
	Bateau bat;
	public GestionComposantBateau(int x, int y, Bateau bat, GestionEcluseLogique  gestionnaire) {
		this.bat = bat;
		this.gestionnaire = gestionnaire;
			setTitle("GestionBateau");
			setAlwaysOnTop(true);
			setSize(500, 100);
			setLocation(x, y);
			setResizable(false);
			setDefaultCloseOperation(HIDE_ON_CLOSE);
			setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));  // Faire borderlayout puis Flowlayout
			PanBouton = new JPanel();
			PanBouton.setLayout(new BoxLayout(PanBouton, BoxLayout.X_AXIS));
			this.add(PanBouton);
			this.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent ev) {
					setVisible(false);
				}
			});
			SliderVitesse();
			if (bat.Position.equals("Amont")) {
				BoutonSortirAmont();  // Le bateau est en amont
				BoutonSortirAscenseurAval();
				ReparerLeMoteurBateau(bat);
			} else if (bat.Position.equals("Aval")) {
				BoutonSortirAval();   // Crée cette méthode pour gérer Aval
				BoutonSortirAscenseurAmont();
				ReparerLeMoteurBateau(bat);
			}
	}

	public void SliderVitesse() {

		if(vitesse < 1) {
			vitesse = 2;
		}
		JSlider SliderVitesse = new JSlider(SwingConstants.HORIZONTAL, 1, 4, vitesse);
		JLabel labelSliderbateau = new JLabel("Vitesse du bateau :");
		SliderVitesse.setFocusable(false);
		SliderVitesse.addChangeListener(
				new ChangeListener() {

					@Override
					public void stateChanged(ChangeEvent e) {
						bat.vitesse = SliderVitesse.getValue();	
					}
					
				}
				);
		this.add(labelSliderbateau);
		this.add(SliderVitesse);
	}
	
	public void BoutonSortirAmont() {
		JButton SortirAm = new JButton("VersAscenseur");
		SortirAm.addActionListener( new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Thread AvancerBateau = new Thread(new Runnable() {
			
				public void run() {
					while(gestionnaire.DeplacerBateauAmontVersAscenseur(bat) == true) {
						SwingUtilities.invokeLater(new Runnable() {
		                    public void run() {
		                    	gestionnaire.PanneMoteurBateau(bat, 1, 1000);
								bat.setBounds(bat.getposXBa(), bat.getposYBa(),bat.getWidth(), bat.getHeight());
								repaint();  // Redessine
		                    }
						});
					}
				}

			});
				AvancerBateau.start();
			}
		});	
		PanBouton.add(SortirAm, BorderLayout.CENTER);
	}
	public void BoutonSortirAval() {
		JButton SortirAv = new JButton("Vers AscenseurAVAL");
		SortirAv.addActionListener( new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Thread AvancerBateauAV = new Thread(new Runnable() {
			
				public void run() {
					while(gestionnaire.DeplacerBateauAvalVersAs(bat) == true) {
						SwingUtilities.invokeLater(new Runnable() {
		                    public void run() {
		                    	gestionnaire.PanneMoteurBateau(bat, 1, 1000);
								bat.setBounds(bat.getposXBa(), bat.getposYBa(),bat.getWidth(), bat.getHeight());
								repaint();  // Redessine
		                    }
						});
					}
				}

			});
				AvancerBateauAV.start();
			}
		});	
		repaint();
		PanBouton.add(SortirAv);
	}
	
	public void BoutonSortirAscenseurAval() {
		JButton SortirAsc = new JButton("VersAval");
		SortirAsc.addActionListener( new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Thread AvancerBateauAsc = new Thread(new Runnable() {
			
				public void run() {
					while(gestionnaire.DeplacerBateauAmontAscenseurVersAval(bat) == true) {
							SwingUtilities.invokeLater(new Runnable() {
			                    public void run() {
			                    	gestionnaire.PanneMoteurBateau(bat, 1, 1000);
									bat.setBounds(bat.getposXBa(), bat.getposYBa(),bat.getWidth(), bat.getHeight());
									repaint();  // Redessine
			                    }
							});
					}
					gestionnaire.SupprimerBateau(gestionnaire.batAmont, FenetreEcluse.PanelCenter);
				}

			});
				AvancerBateauAsc.start();
			}
		});	
		PanBouton.add(SortirAsc, BorderLayout.CENTER);
	}
	
		public void BoutonSortirAscenseurAmont() {
		JButton SortirAscVersAmont = new JButton("VersAval");
		SortirAscVersAmont.addActionListener( new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Thread AvancerBateauAscAmont = new Thread(new Runnable() {
			
				public void run() {
					while(gestionnaire.DeplacerBateauAvalAscVersAmont(bat) == true) {
							SwingUtilities.invokeLater(new Runnable() {
			                    public void run() {
			                    	gestionnaire.PanneMoteurBateau(bat, 1, 1000);
									bat.setBounds(bat.getposXBa(), bat.getposYBa(),bat.getWidth(), bat.getHeight());
									repaint();  // Redessine
			                    }
							});
					}
					gestionnaire.SupprimerBateau(gestionnaire.batAval, FenetreEcluse.PanelCenter);
				}

			});
				AvancerBateauAscAmont.start();
			}
		});	
		PanBouton.add(SortirAscVersAmont, BorderLayout.CENTER);
		
	}
		public void ReparerLeMoteurBateau(Bateau bat) {
			
			JButton BoutonReparation = new JButton("Réparer le moteur");
			BoutonReparation.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Thread ReparerMoteur = new Thread(new Runnable() {
				
					public void run() {
						if(bat.MoteurEnPannee == true) {
						System.out.println("Thread reparation bateau lancee");
						bat.MoteurEnPannee = false;
						remove(BoutonReparation);
						}
						else System.out.println("Le moteur n'est pas en panne...");
					}

				});
					ReparerMoteur.start();
				}
			});
				PanBouton.add(BoutonReparation, BorderLayout.CENTER);
				repaint();
		}
	
}
