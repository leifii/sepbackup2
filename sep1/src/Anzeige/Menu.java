/**
 * @author ${Mark}
 *
 * 
 */
package Anzeige;
/* Die Klasse Menü visualisiet die Komplette Menüführung 
 * Zusätzlich initialisiert sie die Clients mit den passenden Ip-Adressen und Roboter Modi
 * Sowohl der KeyListener als der ActionListener(für die Buttons) befinden sich in dieser Klasse 
 * Jedes Menü wird über ein eigenes JPanel realisiert
 * Methoden zum erstellen der Menüs heißen "erzeuge"+ das passende Menü. Diese Methoden stehen auch alle im Interface IMenu
 * Das Spielfeld Panel (thorbensPanel) wird von der Klasse AnzeigeSpielfeld gestaltet 
 * 
 * 
 * Die Methoden "create(String a)" und "eurezugeSpielfeld" wurden 1zu1 von Thorben übernehmen .
 * Die Methode "setzeAnzeigeSpielfeld" von Thorben übernommen und verändert...veränderungen sind extra Kommentiert
 * 
 */

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import KommunikationPC.Clientinit;

import KommunikationPC.QueueHandler;
import Positionen.Position;
import PowerUps.PowerUp;
import Anzeige.AnzeigeSpielfeld;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;



public class Menu extends JFrame implements IMenu , ActionListener, KeyListener  {
//	private static final long serialVersionUID = 3498199861043935813L; //WIESO??? Manchmal gibts ne Fehlermeldung das das fehlt manchmal nicht ? weiß jemand eine Lösung ?
	
	
	public int leben =10;
	public static boolean pause = true ;
	public static int richtung = 0 ; 
	public boolean inputFinished = false;
	public static boolean kollidiertSepman  = false;
	public static boolean kollidiertTracer  = false;
	public static boolean kollidiertDefender=false;
	public static boolean kollidiertRandom  = false;
	public String[] roboterBelegung = new String[4];
	public String[] roboterIPs = new String[4];
	public String comboBoxInhalt[] = {"Bitte Modus wählen", "SepMAN", "Verfolger", "Verteidiger", "Verpeilter"};
	public JPanel display = new JPanel();                                                //Deklaration aller Objekte und Variablen
	public JPanel startDisplay = new JPanel();
	public JPanel siegDisplay = new JPanel();
	public JPanel niederlageDisplay = new JPanel();
	public JPanel spielvorbereitungsDisplay = new JPanel();
	public JPanel thorbensPanel= new JPanel();
	public JPanel pausenDisplay = new JPanel();
	public JLabel lbUeberschrift = new JLabel("SEPMAN");
	public JLabel lbBeschriftung1= new JLabel("ROBOTER 1:");
	public JLabel lbBeschriftung2= new JLabel("ROBOTER 2:");
	public JLabel lbBeschriftung3= new JLabel("ROBOTER 3:");
	public JLabel lbBeschriftung4= new JLabel("ROBOTER 4:");
	public JLabel lbIp1Beschriftung = new JLabel("1. IP-Adresse");
	public JLabel lbip1Punkt1= new JLabel(".");
	public JLabel lbip1Punkt2= new JLabel(".");
	public JLabel lbip1Punkt3= new JLabel(".");
	public JLabel lbip2Beschriftung= new JLabel("2. IP-Adresse");
	public JLabel lbip2Punkt1= new JLabel(".");
	public JLabel lbip2Punkt2= new JLabel(".");
	public JLabel lbip2Punkt3= new JLabel(".");
	public JLabel lbip3Beschriftung= new JLabel("3. IP-Adresse");
	public JLabel lbip3Punkt1= new JLabel(".");
	public JLabel lbip3Punkt2= new JLabel(".");
	public JLabel lbip3Punkt3= new JLabel(".");
	public JLabel lbip4Beschriftung= new JLabel("4. IP-Adresse");
	public JLabel lbip4Punkt1= new JLabel(".");
	public JLabel lbip4Punkt2= new JLabel(".");
	public JLabel lbip4Punkt3= new JLabel(".");
	public JLabel lbHinweis1 = new JLabel("Bitte positioniere die Roboter richtig !!");
	public JTextField tfIp1Feld1 = new JTextField("192");
	public JTextField tfIp1Feld2 = new JTextField("168");
	public JTextField tfIp1Feld3 = new JTextField("2");
	public JTextField tfIp1Feld4 = new JTextField("106");
	public JTextField tfIp2Feld1 = new JTextField("192");
	public JTextField tfIp2Feld2 = new JTextField("168");
	public JTextField tfIp2Feld3 = new JTextField("2");
	public JTextField tfIp2Feld4 = new JTextField("1");
	public JTextField tfIp3Feld1 = new JTextField("192");
	public JTextField tfIp3Feld2 = new JTextField("168");
	public JTextField tfIp3Feld3 = new JTextField("3");
	public JTextField tfIp3Feld4 = new JTextField("1");
	public JTextField tfIp4Feld1 = new JTextField("192");
	public JTextField tfIp4Feld2 = new JTextField("168");
	public JTextField tfIp4Feld3 = new JTextField("4");
	public JTextField tfIp4Feld4 = new JTextField("1");
	public JButton btnAuswahlfenster = new JButton();
	public JButton btnStart = new JButton();
	public JButton btnEnde = new JButton();
	public JButton btnEnde2 = new JButton();
	public JButton btnZurueck = new JButton();
	public JButton btnStartfinal = new JButton();
	public JButton btnSpielfortsetzen = new JButton();
	public JButton btnNeuesSpiel = new JButton();
	public JComboBox cbAuswahl1= new JComboBox(comboBoxInhalt);
	public JComboBox cbAuswahl2= new JComboBox(comboBoxInhalt);
	public JComboBox cbAuswahl3= new JComboBox(comboBoxInhalt);
	public JComboBox cbAuswahl4= new JComboBox(comboBoxInhalt);
	
	// aus thorbens müll
	ImageIcon iiSepman=new ImageIcon("Sepman.png");                     // Hinzugefügt von Mark
	JLabel jSepman = new JLabel(iiSepman);                              // Hinzugefügt von Mark
	ImageIcon iiRandom=new ImageIcon("zufall.png");                      // Hinzugefügt von Mark
	JLabel jRandom = new JLabel(iiRandom);                              // Hinzugefügt von Mark
	ImageIcon iiGuard=new ImageIcon("Verteidiger.png");                  // Hinzugefügt von Mark 
	JLabel jGuard = new JLabel(iiGuard);                                // Hinzugefügt von Mark
	ImageIcon iiTracker=new ImageIcon("Verfolger.png");                  // Hinzugefügt von Mark
	JLabel jTracker = new JLabel(iiTracker);                            // Hinzugefügt von Mark
	
	 int[] pos=new int[4];
	 int Sepman;
	 int Defender;
	 int Random;
	 int Tracer;
	 int Größe;
	 public int Länge;
	 public int Breite;
	 Plane[] Spielbrett;
	 Planeinit Spiel;
	
	static QueueHandler queue;	//Ergänzt durch Tristan! QueueHandler, um Befehle in das ByteArray zu laden. Initialisierung in setClients()
	public static PowerUp powerUp;     //Ergänzt durch Tristan! Objekt vom Typ "PowerUp". Initialisierung in setClients()
	
	

	/*
	 *  Konstruktor	
	 */
	public Menu() {
		super();
		initComponents();

	}
/*
 * TEIL 1 
 * Hier werden die einzelnen JPanels für das Menü erstellt. 
 * 
 * 
 * 
 * 
 * In Teil 2 laufen die Spielaktionen ab
 * 
 * 
 * 
 * 
 * 	
 */
	
/*
 *  Initialisierung des Fenster + Aufrufen des Startbildschirms*
 *  
 */
public void initComponents() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(1000,720);
	setTitle("SEPman");
	setzeAuswahldisplay ();
//	setzeStartbildschirm(); 
//	setzeNiederlageBildschirm();
//	setzeSiegbildschirm();
//	setzePausenBildschrim();
	validate();
}


/*
 *  Diese Methoden erstell den Startbildschirm 
 * 
 */
public void setzeStartbildschirm(){
	startDisplay.setLayout(null);
	startDisplay.setVisible(true);
	startDisplay.setBackground(Color.gray);
	startDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));	
	
	btnAuswahlfenster.setText("START");
	btnAuswahlfenster.setFont(new Font("Arial", Font.BOLD, 50));
	btnAuswahlfenster.setBounds(240, 200, 500, 75);
	btnAuswahlfenster.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));      
	btnAuswahlfenster.addActionListener(this);
	startDisplay.add(btnAuswahlfenster);
	
	lbUeberschrift.setVerticalAlignment(SwingConstants.TOP);
	lbUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
	lbUeberschrift.setFont(new Font("Arial", Font.BOLD, 100));
	lbUeberschrift.setBounds(140, 20 , 700, 100);
	startDisplay.add(lbUeberschrift);
    getContentPane().add(startDisplay);
}

/*
 *  Methode zum erstellen des Auswahldisplays
 * 
 */
public void setzeAuswahldisplay (){
    startDisplay.setVisible(false);
    spielvorbereitungsDisplay.setVisible(false);
    siegDisplay.setVisible(false);
	display.setVisible(true);
	display.setLayout(null);
	display.setBackground(Color.WHITE);
	display.setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));
	
	lbUeberschrift.setVerticalAlignment(SwingConstants.TOP);
	lbUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
	lbUeberschrift.setFont(new Font("Arial", Font.BOLD, 100));
	lbUeberschrift.setBounds(140, 20 , 700, 100);
	display.add(lbUeberschrift);
	
	btnStart.addActionListener(this);
	btnStart.setBounds(240, 140, 500, 75);
	btnStart.setText("Start");
	btnStart.setFont(new Font("Arial", Font.BOLD, 50));
	btnStart.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	btnStart.setBackground(Color.WHITE);
	display.add(btnStart);
	
	btnEnde.addActionListener(this);           
	btnEnde.setBounds(240, 235, 500, 75);
	btnEnde.setFont(new Font("Arial", Font.BOLD, 50));
	btnEnde.setText("Ende");
	btnEnde.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	btnEnde.setBackground(Color.white);
	display.add(btnEnde);
	
	lbBeschriftung1.setFont(new Font("Arial", Font.BOLD, 17));
	lbBeschriftung1.setBounds(50, 350, 110, 20);
	display.add(lbBeschriftung1);
	
	lbBeschriftung2.setFont(new Font("Arial", Font.BOLD, 17));
	lbBeschriftung2.setBounds(50, 390, 110, 20);
	display.add(lbBeschriftung2);
	lbBeschriftung3.setFont(new Font("Arial", Font.BOLD, 17));
	
	lbBeschriftung3.setBounds(50, 430, 110, 20);
	display.add(lbBeschriftung3);
	lbBeschriftung4.setFont(new Font("Arial", Font.BOLD, 17));
	
	lbBeschriftung4.setBounds(50, 470, 110, 20);
	display.add(lbBeschriftung4);
	
	cbAuswahl1.setSelectedIndex(0);
    cbAuswahl1.setBounds(170, 350, 200, 30);
	cbAuswahl1.addActionListener(this);               
	display.add(cbAuswahl1);
	
	cbAuswahl2.setSelectedIndex(0);
	cbAuswahl2.setBounds(170, 390, 200, 30);
	cbAuswahl2.addActionListener(this);               
	display.add(cbAuswahl2);
	
	cbAuswahl3.setSelectedIndex(0);
	cbAuswahl3.setBounds(170, 430, 200, 30);
	cbAuswahl3.addActionListener(this);                
	display.add(cbAuswahl3);
	
	cbAuswahl4.setSelectedIndex(0);
	cbAuswahl4.setBounds(170, 470, 200, 30);
	cbAuswahl4.addActionListener(this);               
	display.add(cbAuswahl4);
	
/*
 * 	1.Roboter IP-Auswahl Darstellung
 */
	lbIp1Beschriftung.setBounds(440, 350, 140, 20);
	lbIp1Beschriftung.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbIp1Beschriftung);
	
	lbip1Punkt1.setBounds(610, 350, 10, 20);
	lbip1Punkt1.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip1Punkt1);	
	
    lbip1Punkt2.setBounds(660, 350, 10, 20);
    lbip1Punkt2.setFont(new Font("Arial", Font.BOLD, 17));
    display.add(lbip1Punkt2);
    
	lbip1Punkt3.setBounds(710, 350, 10, 20);
	lbip1Punkt3.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip1Punkt3);
    
	tfIp1Feld1.setBounds(570, 350, 40, 20);
	tfIp1Feld1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp1Feld1);	
	
	tfIp1Feld2.setBounds(620, 350, 40, 20);
	tfIp1Feld2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp1Feld2);	
	
	tfIp1Feld3.setBounds(670, 350, 40, 20);
	tfIp1Feld3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp1Feld3);
	
	tfIp1Feld4.setBounds(720, 350, 40, 20);
	tfIp1Feld4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp1Feld4);
    
/*
 *     2. Roboter Ip Auswahl Darstellung
 */
	lbip2Beschriftung.setBounds(440, 390, 140, 20);
	lbip2Beschriftung.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip2Beschriftung);	
	
	lbip2Punkt1.setBounds(610, 390, 10, 20);
	lbip2Punkt1.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip2Punkt1);	
	
	lbip2Punkt2.setBounds(660, 390, 10, 20);
	lbip2Punkt2.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip2Punkt2);	
	
	lbip2Punkt3.setBounds(710, 390, 10, 20);
	lbip2Punkt3.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip2Punkt3);
	
	tfIp2Feld1.setBounds(570, 390, 40, 20);
	tfIp2Feld1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));	
	display.add(tfIp2Feld1);	
	
    tfIp2Feld2.setBounds(620, 390, 40, 20);
	tfIp2Feld2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    display.add(tfIp2Feld2);	
    
	tfIp2Feld3.setBounds(670, 390, 40, 20);
	tfIp2Feld3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp2Feld3);
	
	tfIp2Feld4.setBounds(720, 390, 40, 20);
	tfIp2Feld4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp2Feld4);
	
/*
 * 	   3.Roboter IP Auswahl Darstellung
 */
	
	lbip3Beschriftung.setBounds(440, 430, 140, 20);
	lbip3Beschriftung.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip3Beschriftung);	
	
	lbip3Punkt1.setBounds(610, 430, 10, 20);
	lbip3Punkt1.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip3Punkt1);	
	
	lbip3Punkt2.setBounds(660, 430, 10, 20);
	lbip3Punkt2.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip3Punkt2);	
	
	lbip3Punkt3.setBounds(710, 430, 10, 20);
	lbip3Punkt3.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip3Punkt3);
	
	tfIp3Feld1.setBounds(570, 430, 40, 20);
	tfIp3Feld1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp3Feld1);	
	
	tfIp3Feld2.setBounds(620, 430, 40, 20);
	tfIp3Feld2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp3Feld2);	
	
	tfIp3Feld3.setBounds(670, 430, 40, 20);
	tfIp3Feld3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp3Feld3);
	
	tfIp3Feld4.setBounds(720, 430, 40, 20);
	tfIp3Feld4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp3Feld4);
	
/*
 * 	4. Roboter IP Auswahl Darstellung
 */
	
	lbip4Beschriftung.setBounds(440, 470, 140, 20);
	lbip4Beschriftung.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip4Beschriftung);	
	
	lbip4Punkt1.setBounds(610, 470, 10, 20);
	lbip4Punkt1.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip4Punkt1);	
	
	lbip4Punkt2.setBounds(660, 470, 10, 20);
	lbip4Punkt2.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip4Punkt2);	
	
	lbip4Punkt3.setBounds(710, 470, 10, 20);
	lbip4Punkt3.setFont(new Font("Arial", Font.BOLD, 17));
	display.add(lbip4Punkt3);
	
	tfIp4Feld1.setBounds(570, 470, 40, 20);
	tfIp4Feld1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp4Feld1);	
	
	tfIp4Feld2.setBounds(620, 470, 40, 20);
	tfIp4Feld2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp4Feld2);	
	
	tfIp4Feld3.setBounds(670, 470, 40, 20);
	tfIp4Feld3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp4Feld3);
	
	tfIp4Feld4.setBounds(720, 470, 40, 20);
	tfIp4Feld4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(tfIp4Feld4);
	getContentPane().add(display);
}

/*
 * 
 *  Methode zum erstellen des Spielvorbereitungsdisplay
 */
public void setzeSpielvorbereitungsdisplay(){
	display.setVisible(false);
	startDisplay.setVisible(false);
	siegDisplay.setVisible(false);
	niederlageDisplay.setVisible(false);
	
	spielvorbereitungsDisplay.setLayout(null);
	spielvorbereitungsDisplay.setVisible(true);
	spielvorbereitungsDisplay.setBackground(Color.WHITE);
	spielvorbereitungsDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));
	
	btnZurueck.addActionListener(this);
	spielvorbereitungsDisplay.add(btnZurueck);
	btnZurueck.setBounds(200, 435, 500, 75);
	btnZurueck.setFont(new Font("Arial", Font.BOLD, 50));
	btnZurueck.setText("Zurück");
	btnZurueck.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	
	spielvorbereitungsDisplay.add(btnStartfinal);
	btnStartfinal.setBounds(200, 340, 500, 75);
	btnStartfinal.setFont(new Font("Arial", Font.BOLD, 50));
	btnStartfinal.setText("Spiel starten !!");
	btnStartfinal.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	btnStartfinal.addActionListener(this);
	
	spielvorbereitungsDisplay.add(lbHinweis1);
	lbHinweis1.setBounds(40, 100, 400, 100);
	lbHinweis1.setFont(new Font("Arial", Font.BOLD, 20));
	lbHinweis1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));

	getContentPane().add(spielvorbereitungsDisplay);
}


/*
 * Methode zum erstellen des Siegdisplay
 * 
 */
public void setzeSiegbildschirm(){
	display.setVisible(false);
	startDisplay.setVisible(false);
	siegDisplay.setLayout(null);
	siegDisplay.setVisible(true);
	siegDisplay.setBackground(Color.GREEN);
	siegDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));
	
    getContentPane().add(siegDisplay);
    
	spielvorbereitungsDisplay.add(	btnEnde2 );
	btnEnde2.setBounds(120, 370, 330, 100);
	btnEnde2.setText("Spiel Beenden");
	btnEnde2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	btnEnde2.setBackground(Color.WHITE);
	btnEnde2.addActionListener(this); 
	siegDisplay.add(btnEnde2);
	
	spielvorbereitungsDisplay.add(	btnNeuesSpiel );
	btnNeuesSpiel.setBounds(550, 370, 330, 100);
	btnNeuesSpiel.setText("Neues Spiel");
	btnNeuesSpiel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	btnNeuesSpiel.setBackground(Color.WHITE);
	btnNeuesSpiel.addActionListener(this);
	siegDisplay.add(btnNeuesSpiel);
}

/*
 * Methode zum erstellen des Niederlagedisplays(non-Javadoc)
 * 
 */
public void setzeNiederlageBildschirm(){
	display.setVisible(false);
	startDisplay.setVisible(false);
	niederlageDisplay.setLayout(null);
	niederlageDisplay.setVisible(true);
	niederlageDisplay.setBackground(Color.RED);
	niederlageDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));
	
	niederlageDisplay.add(	btnEnde2 );
	btnEnde2.setBounds(120, 370, 330, 100);
	btnEnde2.setText("Spiel Beenden");
	btnEnde2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	btnEnde2.addActionListener(this); 
	
	niederlageDisplay.add(	btnNeuesSpiel );
	btnNeuesSpiel.setBounds(0, 370, 330, 100);
	btnNeuesSpiel.setText("Neues Spiel");
	btnNeuesSpiel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	btnNeuesSpiel.addActionListener(this);

    getContentPane().add(niederlageDisplay);

}
/*
 * Methode zum erstellen des Pausendisplays
 *
 */
public void setzePausenBildschrim(){
	display.setVisible(false);
	startDisplay.setVisible(false);
	thorbensPanel.setVisible(false);
	pausenDisplay.setLayout(null);
	pausenDisplay.setVisible(true);
	pausenDisplay.setBackground(Color.LIGHT_GRAY);
	pausenDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	
	pausenDisplay.add(lbHinweis1);
	lbHinweis1.setBounds(100, 100, 300, 100);
	lbHinweis1.setFont(new Font("Arial", Font.BOLD, 15));
	lbHinweis1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	
	pausenDisplay.add(btnSpielfortsetzen );
	btnSpielfortsetzen.setBounds(200, 340, 600, 200);
	btnSpielfortsetzen.setText("Spiel fortsetzen !!");
	btnSpielfortsetzen.setFont(new Font("Arial", Font.BOLD, 55));
	btnSpielfortsetzen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	btnSpielfortsetzen.setForeground(Color.GREEN);
	btnSpielfortsetzen.addActionListener(this); 
	
    getContentPane().add(pausenDisplay);
	
}

public void setzeAnzeigeSpielfeld () throws IOException{
	create("Spielfeld.txt");
	setBounds(100, 100, (Länge+1)*150, (Breite+1)*150);
	 
	thorbensPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	thorbensPanel.setLayout(null);
	
	JLabel[] ele=erzeugeSpielfeld();

	/*
	 * Hinzufügen des Sepmanns und der Geister
	 */

	jSepman.setBounds(ele[Sepman].getX() , ele[Sepman].getY(), 65, 65);   //Mark
	thorbensPanel.add(jSepman);                                              //Mark

	jRandom.setBounds(ele[Random].getX() , ele[Random].getY(), 60, 55);   //Mark
	thorbensPanel.add(jRandom);                                              //Mark
	
	jGuard.setBounds(ele[Defender].getX() , ele[Defender].getY(), 60, 55);   //Mark
	thorbensPanel.add(jGuard);                                                 //Mark
	   
	jTracker.setBounds(ele[Tracer].getX() , ele[Tracer].getY(), 60, 55);   //Mark
	thorbensPanel.add(jTracker);                                               //Mark
	
	/*
	 * Hinzufügen der PowerUps
	 */
	
	ImageIcon iiPowerup = new ImageIcon("Powerup.png");   // Hinzugefügt von Mark
	
	JLabel lbPowerUp1 = new JLabel(iiPowerup);  // Hinzugefügt von Mark
	lbPowerUp1.setBounds(ele[5].getX(), ele[5].getY(), 55, 55);  // Hinzugefügt von Mark
	thorbensPanel.add(lbPowerUp1);                // Hinzugefügt von Mark
		
	JLabel lbPowerUp2 = new JLabel(iiPowerup);  // Hinzugefügt von Mark
	lbPowerUp2.setBounds(ele[16].getX(), ele[16].getY(), 55, 55);  // Hinzugefügt von Mark
	thorbensPanel.add(lbPowerUp2);                // Hinzugefügt von Mark
		
	JLabel lbPowerUp3 = new JLabel(iiPowerup);  // Hinzugefügt von Mark
	lbPowerUp3.setBounds(ele[19].getX(), ele[19].getY(), 55, 55);  // Hinzugefügt von Mark
	thorbensPanel.add(lbPowerUp3);                // Hinzugefügt von Mark
		
	JLabel lbPowerUp4 = new JLabel(iiPowerup);  // Hinzugefügt von Mark
	lbPowerUp4.setBounds(ele[30].getX(), ele[30].getY(), 55, 55);  // Hinzugefügt von Mark
	thorbensPanel.add(lbPowerUp4);                // Hinzugefügt von Mark
	
	
	
	ImageIcon Herz=new ImageIcon("herz.gif");
	
	JLabel Leben1=new JLabel(Herz);
	Leben1.setSize(50, 50);
	Leben1.setLocation(10, 10);
	thorbensPanel.add(Leben1);
	
	JLabel Leben2=new JLabel(Herz);       
	Leben2.setSize(50, 50);
	Leben2.setLocation(60, 10);
	thorbensPanel.add(Leben2);
	
	JLabel Leben3=new JLabel(Herz);
	Leben3.setSize(50, 50);
	Leben3.setLocation(110, 10);
	thorbensPanel.add(Leben3);
	
	ImageIcon linie1=new ImageIcon("Linie.png");
	JLabel line1=new JLabel(linie1);
	
	ImageIcon linie2=new ImageIcon("Linie2.png");
	JLabel line2=new JLabel(linie2);
	
	for(int z=0;z<Größe;z++)
	{
		if(Spielbrett[z].getNord()==true)
		{
			line2=new JLabel(linie2);
			line2.setLocation(ele[z].getX()+5, ele[z].getY()-100); // Überarbeitet von Mark
			line2.setSize(50, 100);
			thorbensPanel.add(line2);				
		}
		if(Spielbrett[z].getOst()==true)
		{
			line1=new JLabel(linie1);
			line1.setLocation(ele[z].getX()+55, ele[z].getY()); // Überarbeitet von Mark
			line1.setSize(100, 50);
			thorbensPanel.add(line1);
		}		
	}
	
}

/*
 * TEIL 2 
 * Hier laufen alle "Aktionen ab" 
 *   - Key/Action Listener
 *   - Schreiben in die Modi/IP Arrays
 *   - Eingabe Überprüfung
 *   - Spielfeld Steuerung
 *   - Main Methode
 *   - Initialisierung der Clients
 */
/*
 *  Überprüfung ob die Roboterauswahl vollständig ist und keine Modi doppelt ausgewählt wurden , funktioniert noch nicht korrekt
 */
public boolean eingabeKorrekt() {
	boolean check = true;
	int k = 0;
	for (int i = 0; i< 4; i++){
		if( roboterBelegung[i]== null ){
			check = false;
		}
	}
	for (int i = 0; i < roboterBelegung.length-1; ++i){
		  for (int j = i+1; j < roboterBelegung.length; ++j){
		    if (roboterBelegung[i] == roboterBelegung[j]){      // Abfrage ob kein Modus doppelt ausgewählt wurde 
		    	check = false;
		    }
		  }
	}
	return  check;
}

/*
 *  Action Listener
 * 
 */
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if (e.getSource()== cbAuswahl1){
		JComboBox cb =(JComboBox)e.getSource();
		String msg= (String)cb.getSelectedItem();
		modiSchreiben(0,msg);
	}
	
	else if (e.getSource()== cbAuswahl2){
		JComboBox cb =(JComboBox)e.getSource();
		String msg= (String)cb.getSelectedItem();
		modiSchreiben(1,msg);	
	}
	
	else if (e.getSource()== cbAuswahl3){
		JComboBox cb =(JComboBox)e.getSource();
		String msg= (String)cb.getSelectedItem();
		modiSchreiben(2,msg);
	}
	
	else if (e.getSource()== cbAuswahl4){
		JComboBox cb =(JComboBox)e.getSource();
		String msg= (String)cb.getSelectedItem();
        modiSchreiben(3,msg);

		}					
	else if (e.getSource()== btnEnde ){
		dispose();
	}
	else if (e.getSource()== btnEnde2 ){
		dispose();
	}
	else if (e.getSource() == btnAuswahlfenster){
		setzeAuswahldisplay(); 
	}
	else if( e.getSource()	== btnStart){
		if(eingabeKorrekt()){
		setzeSpielvorbereitungsdisplay();
		}
		else{
			lbUeberschrift.setText("FEHLERHAFTE EINGABE");
			lbUeberschrift.setForeground(Color.RED);
			lbUeberschrift.setFont(new Font("Arial", Font.BOLD, 45));
		}
	}
	else if(e.getSource()== btnZurueck){
		setzeAuswahldisplay();  
	}
	else if(e.getSource() ==btnStartfinal){
		try {
			ipsSchreiben();                                                                   
			setClients();
			spielfeld();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	else if(e.getSource() ==btnSpielfortsetzen ){
		try {
			spielfeld();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	else if (e.getSource()== btnNeuesSpiel){
		setzeSpielvorbereitungsdisplay();
	}
}

public void modiSchreiben (int i ,String auswahl){
	switch(auswahl){
	case "SepMAN" : roboterBelegung[i] = "Sepman";
	break;
	case "Verfolger" : roboterBelegung[i] = "Tracer";
	break;
	case "Verteidiger" : roboterBelegung[i] = "Defender";
	break;
	case "Verpeilter" : roboterBelegung[i] = "Random";
	break;
	}
}

/*
*IP`S Aus den Textfeldern in das Array "roboterip" schreiben
*/
public void ipsSchreiben(){
	roboterIPs[0] =tfIp1Feld1.getText() + "." + tfIp1Feld2.getText() +"." +  tfIp1Feld3.getText()+ "."+ tfIp1Feld4.getText();
	roboterIPs[1] =tfIp2Feld1.getText() + "." + tfIp2Feld2.getText() +"." +  tfIp2Feld3.getText()+ "."+ tfIp2Feld4.getText();
	roboterIPs[2] =tfIp3Feld1.getText() + "." + tfIp3Feld2.getText() +"." +  tfIp3Feld3.getText()+ "."+ tfIp3Feld4.getText();
	roboterIPs[3] =tfIp4Feld1.getText() + "." + tfIp4Feld2.getText() +"." +  tfIp4Feld3.getText()+ "."+ tfIp4Feld4.getText();
			
}

/*
 * Anzeige des Spielfelds
 * KeyListener für das Spielfed
 * Hilsmethoden um das Spielfeld anzuzeigen von Thorben : 
 * "create"
 * "erzeugeSpielfeld"
 * 
 */

public void spielfeld() throws IOException{
	this.spielvorbereitungsDisplay.setVisible(false);
	this.pausenDisplay.setVisible(false);
	pause = false; 
	setzeAnzeigeSpielfeld();
	add(thorbensPanel);
	thorbensPanel.setVisible(true);
	thorbensPanel.setBackground(Color.white);
	setSize(1000, 1000);
	thorbensPanel.addKeyListener(this);
    thorbensPanel.setFocusable(true);	
}

public void keyPressed(KeyEvent l) {
	// TODO Auto-generated method stub
	if(l.getKeyCode() == KeyEvent.VK_UP){
	     richtung(4);
	     System.out.println(richtung);
	     
	     queue.addToQueue((byte) 4);////Ergänzt durch Tristan! Fügt Richtungsanweisung dem ByteArray hinzu.
		}
		else if(l.getKeyCode() == KeyEvent.VK_LEFT){
			richtung(5);
			System.out.println(richtung);
			
			queue.addToQueue((byte) 5);////Ergänzt durch Tristan! Fügt Richtungsanweisung dem ByteArray hinzu.
 
		}
		
		else if(l.getKeyCode() == KeyEvent.VK_DOWN){
			richtung(6);
			System.out.println(richtung);
			
			queue.addToQueue((byte) 6);////Ergänzt durch Tristan! Fügt Richtungsanweisung dem ByteArray hinzu.
 
		}
		
		else if(l.getKeyCode() == KeyEvent.VK_RIGHT){
			richtung(7);
			System.out.println(richtung);
			
			queue.addToQueue((byte) 7);////Ergänzt durch Tristan! Fügt Richtungsanweisung dem ByteArray hinzu.

		}

		else if(l.getKeyCode() == KeyEvent.VK_SPACE){
			richtung(2);
			System.out.println(richtung); 
	
		}	
		else if (l.getKeyCode()== KeyEvent.VK_X){
			System.out.println("x"); 
			
			System.out.println("xx"); 
		}
}
/*
 * 
 * wird nicht gentutz
 */
@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
/*
 * Wird nicht genutzt
 * 
 */
@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}


/*
 * Main Mehtoden 
 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu menu1 = new Menu();
					menu1.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
/* Methoden für Belgung der Richtungsvariable
 * 
*/
	public void richtung(int i){
		switch(i){
		case 4 : richtung =4;
		break;	
		case 6 :richtung =6;
		break;		
		case 5 :richtung =5;
		break;		
		case 7 :richtung =7;
		break;		
		case 2 :richtung =2;
		break;
		}
	}
/*
 *  
 */
@Override
public  int getTaste() {
	// TODO Auto-generated method stub
		return richtung;
	}

	/*
	 * Starten der Kommunikation	
	 */
public void setClients() throws IOException{
	
     Clientinit client1 = new  Clientinit();
     client1.clientInit(this.roboterIPs[0], this.roboterBelegung[0], 18415,1);
     queue = client1.queueHandler.getQueueHandler(); ////Ergänzt durch Tristan! Fügt Referenz zum QueueHandler hinzu.
     powerUp = new PowerUp();                        ////Ergänzt durch Tristan! Erzeugt ein neues Objekt des Typs PowerUp
     powerUp.setPowerUp(powerUp);                    ////Ergänzt durch Trustan! Ermöglicht Zugriff auf Menu.powerUp aus anderen Klassen!
}
/*
 * Permanente abfrage der 4 Kollisionsvariabeln solange das Spiel läuft
 */
public void kollisionserkennung(){

	while(pause == false){
		if ( kollidiertSepman ==true ){
			if (leben>1){
			setzePausenBildschrim();
			leben -=1;
			}
			else{
				setzeNiederlageBildschirm();
			}
		}
		else if( kollidiertTracer ==true){
			if (leben>1){
				setzePausenBildschrim();
				leben -=1;
				}
				else{
					setzeNiederlageBildschirm();
				}
		}
		else if (kollidiertDefender == true){
			if (leben>1){
				setzePausenBildschrim();
				leben -=1;
				}
				else{
					setzeNiederlageBildschirm();
				}
		}
		else if(kollidiertRandom ==true)
			if (leben>1){
				setzePausenBildschrim();
				leben -=1;
				}
				else{
					setzeNiederlageBildschirm();
				}
	}
}

/*
 * Kollision
 * 
 */
public void kollision () {
	if(leben >1){
		setzePausenBildschrim();
		leben -=1;
	}
	else{
		setzeNiederlageBildschirm();
	}
	
}


public void Positionstracking (){
	this.Sepman   = Position.getPosSepman();
	this.Tracer   = Position.getPosTracer();
	this.Defender = Position.getPosDefender();
	this.Random   = Position.getPosRandom();	

}





public JLabel[] erzeugeSpielfeld()
{
	int zahl=1;
	int i=0;
	int k=0;
	ImageIcon iiKnoten = new ImageIcon("Knoten.png"); // Hinzugefügt von Mark 
	JLabel[] ele=new JLabel[Größe];
	for(JLabel j:ele)
	{
			if(k>=Breite)
			{
				k=0;
				i++;
			}
				j=new JLabel(iiKnoten); // Hinzugefügt von Mark 
				j.setBounds(100+k*150,100+i*150,55,55);
				thorbensPanel.add(j);
			zahl++;
			ele[zahl-2]=j;
			k++;	
	}
	return ele;
}
public void create(String a) throws IOException
{
	FileReader fr = new FileReader(a);
    BufferedReader br = new BufferedReader(fr);
    String hilfs = null;
    int[] name=new int[1];
    boolean[] Nord=new boolean[1];
    boolean[] Süd=new boolean[1];
    boolean[] West=new boolean[1];
    boolean[] Ost=new boolean[1];
    boolean[] power=new boolean[1];
    
    
    
    int u=0;
    hilfs=br.readLine();
	while(hilfs.contains("end")!=true){
    hilfs=br.readLine();
    if(hilfs.contains("#")==true)
    		{
    	
    		}
    else if(hilfs.contains("x")==true)
    {
    	char[] ch=hilfs.toCharArray();
    	char hilfs1=ch[0];
    	char hilfs2=ch[1];
    	char hilfs3=ch[3];
    	char hilfs4=ch[4];
    	String s1=String.valueOf(hilfs1)+String.valueOf(hilfs2);
    	String s2=String.valueOf(hilfs3)+String.valueOf(hilfs4);
    	Breite=Integer.parseInt(s1);
    	Länge=Integer.parseInt(s2);
    }
    else if(hilfs.length()<11 && hilfs.contains("end")==false&&hilfs.length()>=2)
    {
    	Größe= Integer.parseInt(hilfs);
    	name=new int[Größe];
    	Nord=new boolean[Größe];
 	    Süd=new boolean[Größe];
 	    West=new boolean[Größe];
 	    Ost=new boolean[Größe];
 	    power=new boolean[Größe];
    	
    }
    else if(hilfs.length()==11)
    {
    	char[] ch=hilfs.toCharArray();
    	char hilfs1=ch[0];
    	char hilfs2=ch[2];
    	char hilfs3=ch[4];
    	char hilfs4=ch[6];
    	char hilfs5=ch[8];
    	char hilfs6=ch[10];
    	String s=String.valueOf(hilfs1);
    	name[u]=Integer.parseInt(s);
    	String y="y";
    	
    	if(String.valueOf(hilfs2).contains(y)==true)
    		Nord[u]=true;
    	else
    		Nord[u]=false;
    	
    	if(String.valueOf(hilfs3).contains(y)==true)
    		Süd[u]=true;
    	else
    		Süd[u]=false;
    	
    	if(String.valueOf(hilfs5).contains(y)==true)
    		Ost[u]=true;
    	else
    		Ost[u]=false;
    	
    	if(String.valueOf(hilfs4).contains(y)==true)
    		West[u]=true;
    	else
    		West[u]=false;
    	
    	if(String.valueOf(hilfs6).contains(y)==true)
    		power[u]=true;
    	else
    		power[u]=false;
    	
    	u++;
    	//name[u]=Integ
    }
    else if(hilfs.length()==12)
    {
    	char[] ch=hilfs.toCharArray();
    	char hilfs1=ch[0];
    	char hilfs11=ch[1];
    	char hilfs2=ch[3];
    	char hilfs3=ch[5];
    	char hilfs4=ch[7];
    	char hilfs5=ch[9];
    	char hilfs6=ch[11];
    	String y="y";
    	String s=String.valueOf(hilfs1)+String.valueOf(hilfs11);
    	name[u]=Integer.parseInt(s);
    	
    	if(String.valueOf(hilfs2).contains(y)==true)
    		Nord[u]=true;
    	else
    		Nord[u]=false;
    	if(String.valueOf(hilfs3).contains(y)==true)
    		Süd[u]=true;
    	else
    		Süd[u]=false;
    	if(String.valueOf(hilfs5).contains(y)==true)
    		Ost[u]=true;
    	else
    		Ost[u]=false;
    	if(String.valueOf(hilfs4).contains(y)==true)
    		West[u]=true;
    	else
    		West[u]=false;
    	if(String.valueOf(hilfs6).contains(y)==true)
    		power[u]=true;
    	else
    		power[u]=false;
    	
    	u++;
    }
    else if(hilfs.length()==14)
    {
    	char[] ch=hilfs.toCharArray();
    	char hilfs1=ch[0];
    	char hilfs11=ch[1];
    	char hilfs2=ch[3];
    	char hilfs22=ch[4];
    	char hilfs3=ch[6];
    	char hilfs33=ch[7];
    	char hilfs4=ch[9];
    	char hilfs44=ch[10];
    	
    	String s1=String.valueOf(hilfs1)+String.valueOf(hilfs11);
    	Defender=Integer.parseInt(s1);
    	pos[3]=Defender;
    	String s2=String.valueOf(hilfs2)+String.valueOf(hilfs22);
    	Sepman=Integer.parseInt(s2);
    	pos[0]=Sepman;
    	String s3=String.valueOf(hilfs3)+String.valueOf(hilfs33);
    	Tracer=Integer.parseInt(s3);
    	pos[2]=Tracer;
    	String s4=String.valueOf(hilfs4)+String.valueOf(hilfs44);
    	Random=Integer.parseInt(s4);
    	pos[1]=Random;
    }
	}
	
	Spiel=new Planeinit(name,Nord,Süd,Ost,West,power,pos);//so ists richtig
	Spielbrett=Spiel.getSpiel();
    br.close();
}
}
