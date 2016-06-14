package Anzeige;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JLabel;

import javax.swing.JComboBox;
import javax.print.DocFlavor.STRING;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;




public class Menu extends JFrame implements IMenu , ActionListener{
//	private static final long serialVersionUID = 3498199861043935813L; //WIESO???
	
	boolean inputFinished = false;
	private String[] roboterBelegung = new String[4];
	public 	String comboBoxInhalt[] = {"Modus1", "Modus2", "Modus3", "Modus4"};
	private JPanel display = new JPanel();
	private JPanel startDisplay = new JPanel();
	private JPanel siegDisplay = new JPanel();
	private JPanel niederlageDisplay = new JPanel();
	private JPanel spielvorbereitungsDisplay = new JPanel();
	public JLabel lbUeberschrift = new JLabel("SEPMAN");
	public JLabel lbBeschriftung1= new JLabel("ROBOTER 1:");
	public JLabel lbBeschriftung2= new JLabel("ROBOTER 2:");
	public JLabel lbBeschriftung3= new JLabel("ROBOTER 3:");
	public JLabel lbBeschriftung4= new JLabel("ROBOTER 4:");
	private JLabel lbHinweis1 = new JLabel("Bitte positioniere die Roboter richtig !!");
	public JButton btnAuswahlfenster = new JButton();
	public JButton btnStart = new JButton();
	public JButton btnEnde = new JButton();
	public JButton btnZurueck = new JButton();
	public JButton btnStartfinal = new JButton();
	public JComboBox cbAuswahl1= new JComboBox(comboBoxInhalt);
	public JComboBox cbAuswahl2= new JComboBox(comboBoxInhalt);
	public JComboBox cbAuswahl3= new JComboBox(comboBoxInhalt);
	public JComboBox cbAuswahl4= new JComboBox(comboBoxInhalt);
	
	public Menu() {
		super();
		initComponents();
	}

public void initComponents() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(600,500);
	setTitle("SEPman");
	setzeStartbildschirm();
//	setzeSpielvorbereitungsdisplay();
//	setzeAuswahldisplay();


	
	validate();

}
public void setzeStartbildschirm(){
	startDisplay.setLayout(null);
	startDisplay.setVisible(true);
	startDisplay.setBackground(Color.gray);
	startDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));	
	
	btnAuswahlfenster.setText("START");
	btnAuswahlfenster.setBounds(210, 200, 200, 50);
	btnAuswahlfenster.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	btnAuswahlfenster.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setzeAuswahldisplay();
		}
	});
	startDisplay.add(btnAuswahlfenster);
	
	lbUeberschrift.setVerticalAlignment(SwingConstants.TOP);
	lbUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
	lbUeberschrift.setFont(new Font("Arial", Font.BOLD, 70));
	lbUeberschrift.setBounds(20,20 , 580, 70);
	startDisplay.add(lbUeberschrift);
    getContentPane().add(startDisplay);
}

public void setzeAuswahldisplay (){
    startDisplay.setVisible(false);
    spielvorbereitungsDisplay.setVisible(false);
	display.setVisible(true);
	display.setLayout(null);
	display.setBackground(Color.WHITE);
	display.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	
	lbUeberschrift.setVerticalAlignment(SwingConstants.TOP);
	lbUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
	lbUeberschrift.setFont(new Font("Arial", Font.BOLD, 50));
	lbUeberschrift.setBounds(20, 6, 580	, 50);
	display.add(lbUeberschrift);
	
	btnStart.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (eingabeKorrekt()){
			setzeSpielvorbereitungsdisplay();
			}
		}
	});
	btnStart.setSize(210, 50);
	btnStart.setLocation(215, 95);
	btnStart.setText("Start");
	btnStart.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(btnStart);
	btnEnde.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	});
	
	btnEnde.setSize(210, 50);
	btnEnde.setLocation(215, 155);
	btnEnde.setText("Ende");
	btnEnde.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	display.add(btnEnde);
	
	lbBeschriftung1.setFont(new Font("Arial", Font.BOLD, 15));
	lbBeschriftung1.setBounds(215, 225, 102, 18);
	display.add(lbBeschriftung1);
	
	lbBeschriftung2.setFont(new Font("Arial", Font.BOLD, 15));
	lbBeschriftung2.setBounds(215, 261, 102, 18);
	display.add(lbBeschriftung2);
	lbBeschriftung3.setFont(new Font("Arial", Font.BOLD, 15));
	
	lbBeschriftung3.setBounds(215, 300, 102, 16);
	display.add(lbBeschriftung3);
	lbBeschriftung4.setFont(new Font("Arial", Font.BOLD, 15));
	
	lbBeschriftung4.setBounds(215, 340, 102, 16);
	display.add(lbBeschriftung4);
	
	cbAuswahl1.setSelectedIndex(0);
	cbAuswahl1.setSize(114, 30);
	cbAuswahl1.setLocation(311, 215);
	cbAuswahl1.addActionListener(this);
	display.add(cbAuswahl1);
	
	cbAuswahl2.setSelectedIndex(1);
	cbAuswahl2.setSize(114, 30);
	cbAuswahl2.setLocation(311, 250);
	cbAuswahl2.addActionListener(this);
	display.add(cbAuswahl2);
	
	cbAuswahl3.setSelectedIndex(2);
	cbAuswahl3.setSize(114, 30);
	cbAuswahl3.setLocation(311, 290);
	cbAuswahl3.addActionListener(this);
	display.add(cbAuswahl3);
	
	cbAuswahl4.setSelectedIndex(3);
	cbAuswahl4.setSize(114, 30);
	cbAuswahl4.setLocation(311, 330);
	cbAuswahl4.addActionListener(this);
	display.add(cbAuswahl4);
	
	getContentPane().add(display);
}

public void setzeSpielvorbereitungsdisplay(){
	display.setVisible(false);
	startDisplay.setVisible(false);
	siegDisplay.setVisible(false);
	niederlageDisplay.setVisible(false);
	
	spielvorbereitungsDisplay.setLayout(null);
	spielvorbereitungsDisplay.setVisible(true);
	spielvorbereitungsDisplay.setBackground(Color.WHITE);
	spielvorbereitungsDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	
	btnZurueck.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setzeAuswahldisplay();
		}
	});
	spielvorbereitungsDisplay.add(btnZurueck);
	btnZurueck.setBounds(200, 400, 240, 50);
	btnZurueck.setText("Zurück");
	btnZurueck.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	
	spielvorbereitungsDisplay.add(btnStartfinal);
	btnStartfinal.setBounds(200, 340, 240, 50);
	btnStartfinal.setText("Roboter positioniert ,Spiel starten !!");
	btnStartfinal.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	
	spielvorbereitungsDisplay.add(lbHinweis1);
	lbHinweis1.setBounds(100, 100, 300, 100);
	lbHinweis1.setFont(new Font("Arial", Font.BOLD, 15));
	lbHinweis1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));

	
	getContentPane().add(spielvorbereitungsDisplay);
}

public void setzeSiegbildschirm(){
	display.setVisible(false);
	startDisplay.setVisible(false);
	siegDisplay.setLayout(null);
	siegDisplay.setVisible(true);
	siegDisplay.setBackground(Color.GRAY);
	siegDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	
    getContentPane().add(siegDisplay);
}

public void setzeNiederlageBildschirm(){
	
}

public void setzePausenBildschrim(){
	
}

public boolean eingabeKorrekt(){
	boolean check = false;
	int k = 0;
	for (int i = 0; i< 3; i++){
		if(roboterBelegung[k]== roboterBelegung[i]){
			System.out.println(roboterBelegung[i]);
			check = true;
		}
	}
	return check;
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if (e.getSource()== cbAuswahl1){
		JComboBox cb =(JComboBox)e.getSource();
		String msg= (String)cb.getSelectedItem();
		this.roboterBelegung[0]= "Modus 2";
		this.roboterBelegung[1]= "Modus 2";
		this.roboterBelegung[2]= "Modus 2";
		this.roboterBelegung[3]= "Modus 2";
		
//		switch(msg){
//		case "Modus 1" : roboterBelegung[0] = "Modus 1";
//		break;
//		case "Modus 2" : roboterBelegung[0] = "Modus 2";
//		break;
//		case "Modus 3" : roboterBelegung[0] = "Modus 3";
//		break;
//		case "Modus 4" : roboterBelegung[0] = "Modus 4";
//		break;
//		}
	}
	else if (e.getSource()== cbAuswahl2){
		JComboBox cb =(JComboBox)e.getSource();
		String msg= (String)cb.getSelectedItem();
		switch(msg){
		case "Modus 1" : roboterBelegung[1] = "Modus 1";
		break;
		case "Modus 2" : roboterBelegung[1] = "Modus 2";
		break;
		case "Modus 3" : roboterBelegung[1] = "Modus 3";
		break;
		case "Modus 4" : roboterBelegung[1] = "Modus 4";
		break;
		}	
	}
	else if (e.getSource()== cbAuswahl3){
		JComboBox cb =(JComboBox)e.getSource();
		String msg= (String)cb.getSelectedItem();
		switch(msg){
		case "Modus 1" : roboterBelegung[2] = "Modus 1";
		break;
		case "Modus 2" : roboterBelegung[2] = "Modus 2";
		break;
		case "Modus 3" : roboterBelegung[2] = "Modus 3";
		break;
		case "Modus 4" : roboterBelegung[2] = "Modus 4";
		break;
		}		
	}
	else if (e.getSource()== cbAuswahl4){
		JComboBox cb =(JComboBox)e.getSource();
		String msg= (String)cb.getSelectedItem();
		switch(msg){
		case "Modus 1" : roboterBelegung[3] = "Modus 1";
		break;
		case "Modus 2" : roboterBelegung[3] = "Modus 2";
		break;
		case "Modus 3" : roboterBelegung[3] = "Modus 3";
		break;
		case "Modus 4" : roboterBelegung[3] = "Modus 4";
		break;
		}	
	}	
}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(
							UIManager.getSystemLookAndFeelClassName()); //Hiermit wird erreicht das das Programm unter 
                                                                        //jedem Betriebsystem genau so aussieht wie es 
					 													//der Anwender von nativen Anwendungen kennt
					Menu menu1 = new Menu();
					menu1.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public String[] getRoboterBelegung() {
		return roboterBelegung;
	}
	public void setRoboterBelegung(String[] roboterBelegung) {
		this.roboterBelegung = roboterBelegung;
	}

}