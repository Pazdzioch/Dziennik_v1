    package Dziennik;
    
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
    
import java.util.Scanner;
import java.util.Timer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;

import org.omg.CORBA.Bounds;

import com.mysql.jdbc.StringUtils;

import java.util.List;

    public class TwojeKlasy 
    {	  
		private static JButton bModulKlas, bModulOcen, bModulObecnosci, bHome, bWyloguj, bSzukaj;
    	private static JLabel lblInformacje, lblRokSzkolny, lblAktualnyPrzedmiot, lblObecnychStudentow, lblSeparator,lblSeparator2, lblTime,lblDay, lblVersion;
      
    	String strTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    	String strDay = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
  
    	final String zalogowanoJako = "Zalogowano jako: ";
		public String imie, nazwisko, wychowankowie, nazwaFolderuUzytkownika, persona;
		public String[] uczeKlasy, wszystkieKlasy;
		public String[] args = {};
      
    	public JMenuBar createJMenuBar()  
    	{
    		JMenuBar mainMenuBar;
    		JMenu menuPlik, menuWidok, menuNowy, menuPrzejdz, menuNarzedzia, menuPomoc;
    		JMenuItem NowyMenuItem, OtworzBazeMenuItem, ZapiszMenuItem, Odswie¿MenuItem, DrukujMenuItem, WylogujMenuItem, WyjdzMenuItem ;
    		
    		mainMenuBar = new JMenuBar();
    		menuPlik = new JMenu("Plik");
    		mainMenuBar.add(menuPlik);
    		
    		menuNowy = new JMenu("Nowy");
    		menuPlik.add(menuNowy);
    		
    		NowyMenuItem = new JMenuItem("Utwórz plik danych");
    		menuNowy.add(NowyMenuItem);
    		
    		ZapiszMenuItem = new JMenuItem("Zapisz");
    		menuPlik.add(ZapiszMenuItem);
    		menuPlik.addSeparator();

    		Odswie¿MenuItem = new JMenuItem("Odswie¿");
    		menuPlik.add(Odswie¿MenuItem);
    		menuPlik.addSeparator();

    		DrukujMenuItem = new JMenuItem("Drukuj..");
    		menuPlik.add(DrukujMenuItem);
    		menuPlik.addSeparator();

    		WylogujMenuItem = new JMenuItem("Wyloguj");
    		menuPlik.add(WylogujMenuItem);
    		
    		WyjdzMenuItem = new JMenuItem("Zapisz i WyjdŸ");	
    		menuPlik.add(WyjdzMenuItem);
    		
    		menuNarzedzia = new JMenu("Narzedzia");
    		mainMenuBar.add(menuNarzedzia);
    		
    		menuWidok = new JMenu("Widok");
    		mainMenuBar.add(menuWidok);
    		
    		menuPomoc = new JMenu("Pomoc");
    		mainMenuBar.add(menuPomoc);
    		return mainMenuBar;
    	}
   
    	private void createGui(String folderName, String firstName, String lastName, String[] listOfAllClass, String[] listClassIteach, String KlasaWychowankowie) 
    	{
//			tworzenie podkreslenia
//    		Font font = nazwaKonkretnegoObiektu.getFont();
//    		Map attributes = font.getAttributes();
//    		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
//    		bWyloguj.setFont(font.deriveFont(attributes));
    		
    		nazwaFolderuUzytkownika = folderName;
    		imie = firstName; 
    		nazwisko = lastName;
    		wszystkieKlasy = listOfAllClass;
    		uczeKlasy = listClassIteach;
    		wychowankowie = KlasaWychowankowie;
    		persona = imie + " " + nazwisko; 
    		
    		System.out.println("folder name: " + folderName);
    		
    		//frame settings..
    		JFrame frame = new JFrame("Dziennik Elektroniczny - Twoje klasy");
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		TwojeKlasy app = new TwojeKlasy();
    		frame.setJMenuBar(app.createJMenuBar());
    		frame.setSize(1362, 724);
     		frame.setResizable(false);
     		wysrodkujOkno(frame);
    	    
     		//stworzyc metode 'create sep; return sep'
     		JButton bSep = new JButton();
    		bSep.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/seppion.png")));
    		bSep.setOpaque(false);
    		bSep.setBorder(null);
    		bSep.setContentAreaFilled(false);
    		bSep.setBorderPainted(false);
    		
    		JButton bSep2 = new JButton();
    		bSep2.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/seppion.png")));
    		bSep2.setOpaque(false);
    		bSep2.setBorder(null);
    		bSep2.setContentAreaFilled(false);
    		bSep2.setBorderPainted(false);
    		
    		JButton bSep3 = new JButton();
    		bSep3.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/seppion.png")));
    		bSep3.setOpaque(false);
    		bSep3.setBorder(null);
    		bSep3.setContentAreaFilled(false);
    		bSep3.setBorderPainted(false);
    		
    		JButton bSep4 = new JButton();
    		bSep4.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/seppion.png")));
    		bSep4.setOpaque(false);
    		bSep4.setBorder(null);
    		bSep4.setContentAreaFilled(false);
    		bSep4.setBorderPainted(false);
    		
    		Color blueColor = new Color( 0, 0, 0);
    		Color hrefColor = new Color( 31, 69, 252);
     		
    		/**
     	    * Create Panel1
     		*/
    		JPanel p1 = new JPanel();
    		p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS)); // ustawienie ikon w lini poziomej
    		p1.setBackground(Color.WHITE);
   
    		//tworzenie komponentow do panelu1
    		ImageIcon seppion = new ImageIcon("/res/seppion.png");
    		
    		JLabel lblLogo = new JLabel();
    		lblLogo.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/logo4m.png")));
    		lblLogo.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
    		lblLogo.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent evt)
                {
                	System.out.println("Powrót do g³ównego okna. "); 
                }
            });
    		
    		JLabel lblMojeKlasy = new JLabel("Moje Klasy"); 
    		lblMojeKlasy.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
    		
    		JLabel lblDaneZalogowanego = new JLabel(persona);
    		lblDaneZalogowanego.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/usericonm.png")));
      
    		JLabel lblMojProfil = new JLabel("Mój profil");
   			lblMojProfil.setForeground(blueColor);
   			lblMojProfil.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
   			
    		bWyloguj = new JButton("Wyloguj");
    		bWyloguj.setBackground(new Color(255, 255, 255));
    		bWyloguj.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/logout.png")));
    		bWyloguj.setSize(30, 60);
    		bWyloguj.setBorder(null);
    		bWyloguj.setForeground(blueColor); 
    		bWyloguj.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
    		bWyloguj.addActionListener(new ActionListener() {			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				frame.dispose();
    				JOptionPane.showMessageDialog(null, "Wylogowano.");
    				Logowanie.runClassLogowanie();
    			}
    		});
    		 
 
    		//Dodanie komponentów do panelu1
    		p1.add(lblLogo); // dodanie logo z funkcj¹ maj¹ce funkcje powrotu do glownego okna kontekstowego
    		p1.add(Box.createHorizontalGlue());	// tworzenie  obiektów w orientacji od prawej do lewej
    		//p1.add(Box.createRigidArea(new Dimension(35,0)));
 
    		p1.add(Box.createRigidArea(new Dimension(	3,0)));
 
    		p1.add(Box.createRigidArea(new Dimension(1,0))); 
    		p1.add(Box.createRigidArea(new Dimension(115,0)));
    		p1.add(bSep2);
    		p1.add(lblMojeKlasy);
    		p1.add(bSep3);
    		p1.add(lblMojProfil);
    		p1.add(Box.createRigidArea(new Dimension(5,0)));
    		p1.add(bSep);
    		p1.add(Box.createRigidArea(new Dimension(5,0)));
    		p1.add(lblDaneZalogowanego);
    		p1.add(Box.createRigidArea(new Dimension(5,0)));
   
    		p1.add(Box.createRigidArea(new Dimension(5,0)));
    		p1.add(bWyloguj); // ikona pierwsza od konca 
    		p1.setBorder(BorderFactory.createLineBorder((new Color(245,245,255))));
    		/**
    		 * Create Panel2
    		 */
     		JPanel p2 = new JPanel();
     		p2.setVisible(true);
     		p2.setBorder(new EmptyBorder(10,30,0,0));
    		p2.setBounds(1,81,1360,50);
    		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));  
    		p2.setBackground(new Color(239,239,239));
		 
    		JLabel lblInformacje = new JLabel("Informacje ");
    		//lblTwojeKlasy.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/klasIcon.png")));
    		lblInformacje.setFont(new Font("default", Font.BOLD, 20));
    		p2.add(lblInformacje);
   	  	
    			
    		/**
			*Zwiêkszenie daty o jeden dzien
			*/
			    	String untildate= strDay ;//can take any date in current format    
					SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MM-yyyy" );   
					//SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" ); 
					Calendar cal = Calendar.getInstance();    
					try 
					{
						cal.setTime( dateFormat.parse(untildate));
					} 
					catch (ParseException e) 
					{
						e.printStackTrace();
					}    
					cal.add( Calendar.DATE, 1 );    
					String convertedDate=dateFormat.format(cal.getTime());    
					  
					cal.add( Calendar.DATE, 1 );    
					String convertedDate2=dateFormat.format(cal.getTime());    
		
     		//Tworzenie dolnego panelu informacyjnego
     		JPanel pDolny = new JPanel( new FlowLayout(FlowLayout.CENTER)); 
     		
     		JLabel lblTime = new JLabel("Dziœ jest " + strDay + ", " +  strTime);
     		pDolny.add(lblTime);
     		 
     		//Tworzenie panelu lewego
     		JPanel pLeft = new JPanel();
      		pLeft.setPreferredSize(new Dimension(150, 300));
     		pLeft.setLayout(new BoxLayout(pLeft, BoxLayout.Y_AXIS)); // ustawienie ikon w lini poziomej
     		pLeft.setBackground(new Color(245,245,255));
     		pLeft.setBorder(BorderFactory.createEmptyBorder(111,20,10,10)); 
     	 
     		JLabel lblMojeKlasyPanelLeft = new JLabel("Moje klasy");
     		lblMojeKlasyPanelLeft.setForeground(new Color(76,61,248)); 
     		lblMojeKlasyPanelLeft.setFont(new Font("default", Font.ROMAN_BASELINE, 17));
     		lblMojeKlasyPanelLeft.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
     		pLeft.add(lblMojeKlasyPanelLeft); 
   
     		//DODANIE odstepu miedzy elementami w lewym panelu
     		pLeft.add(Box.createRigidArea(new Dimension(1,55)));
     		
     		JLabel lblMoiUczniowie = new JLabel("Moi uczniowie");
     		lblMoiUczniowie.setFont(new Font("default", Font.ROMAN_BASELINE, 17));
     		lblMoiUczniowie.setForeground(Color.BLACK); 
     		lblMoiUczniowie.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
     		pLeft.add(lblMoiUczniowie); 
     		
     		
     		//tworzenie scrollPane
    		/**
			*Tworzenie panelu "cetralnego"
			*/
    		JPanel pCenter = new JPanel();
    		pCenter.setBackground(Color.white);	
    		pCenter.setLayout(null);
     		
    		JLabel lblKlasyKtoreUczysz = new JLabel("Klasy które uczysz");
     		lblKlasyKtoreUczysz.setBounds(90,50,220,100);
     		lblKlasyKtoreUczysz.setForeground(new Color(200,200,200));
     		lblKlasyKtoreUczysz.setFont(new Font("default", Font.BOLD, 16));
     		pCenter.add(lblKlasyKtoreUczysz);
     		
     		//dodanie dlugiego separatora
     		JButton bSeparator = new JButton();
     		bSeparator.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/sepPoziomy.png")));
     		bSeparator.setOpaque(false);
     		bSeparator.setBorder(null);
     		bSeparator.setContentAreaFilled(false);
     		bSeparator.setBorderPainted(false);
     		bSeparator.setBounds(0, 70, 900, 100);
     		pCenter.add(bSeparator);
     		
      		JLabel lblDodajKlase = new JLabel("Dodaj now¹ klaseee");
      		lblDodajKlase.setBounds(130,100,220,100);
     		lblDodajKlase.setForeground(new Color(69,55,215));
     		lblDodajKlase.setFont(new Font("default", Font.ITALIC, 16));
     		lblDodajKlase.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/addNew.png")));
     		lblDodajKlase.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent evt)
                {
                	System.out.println("Powrót do g³ównego okna. "); 
                	frame.disable();
                	AddNewClass.main(args);
                }
            });
     		lblDodajKlase.setCursor(new Cursor(Cursor.HAND_CURSOR));
     		pCenter.add(lblDodajKlase);
     		
     		JButton bSeparator2 = new JButton();
     		bSeparator2.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/sepPoziomy.png")));
     		bSeparator2.setOpaque(false);
     		bSeparator2.setBorder(null);
     		bSeparator2.setContentAreaFilled(false);
     		bSeparator2.setBorderPainted(false);
     		bSeparator2.setBounds(85, 135, 350, 100);
     		pCenter.add(bSeparator2);
     		 
     		// punkty w których bêd¹ tworzone JLabel. 
     		int x = 130;
     		int y = 180;
     		//jesli uzytkownik ma klase to stworz JLabels i dodaj te klasy
     		if (uczeKlasy != null) 
     		{
     			int ilosc_Klas = uczeKlasy.length;
     			JLabel[] labels = new JLabel[ilosc_Klas];
      			for (int s = 0; s <uczeKlasy.length ; s++)
      			{
      				labels[s] = new JLabel();
      				labels[s].setBounds(x,y,340,50);
      				labels[s].setText("Klasa "+ uczeKlasy[s]);
      				//labels[s].setForeground(new Color(55,45,168));
      				labels[s].setFont(new Font("default", Font.PLAIN, 16));
      				labels[s].setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/editclas.png")));
	   				pCenter.add(labels[s]);
	   				y += 30;
      			}
     		}		
 
     		JLabel lblJestesWychowawcaKlasy = new JLabel("Jesteœ wychowawc¹ klasy ");
     		lblJestesWychowawcaKlasy.setBounds(90,y+50,220,100);
     		lblJestesWychowawcaKlasy.setForeground(new Color(200,200,200));
     		lblJestesWychowawcaKlasy.setFont(new Font("default", Font.BOLD, 16));
     		pCenter.add(lblJestesWychowawcaKlasy);
     		y+=50;
     		
     		//dodanie dlugiego separatora
     		JButton bSeps = new JButton();
     		bSeps.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/sepPoziomy.png")));
     		bSeps.setOpaque(false);
     		bSeps.setBorder(null);
     		bSeps.setContentAreaFilled(false);
     		bSeps.setBorderPainted(false);
     		bSeps.setBounds(0, y+20, 900, 100);
     		pCenter.add(bSeps);
     		
      		JLabel lblDodajKlaseWychowankow = new JLabel("Dodaj klasê któr¹ wychowujesz");
      		lblDodajKlaseWychowankow.setBounds(130,y+50,320,100);
      		lblDodajKlaseWychowankow.setForeground(new Color(69,55,215));
      		lblDodajKlaseWychowankow.setFont(new Font("default", Font.ITALIC, 16));
      		lblDodajKlaseWychowankow.setIcon(new ImageIcon(Wyszukiwanie.class.getResource("/res/addNew.png")));
      		lblDodajKlaseWychowankow.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
     		pCenter.add(lblDodajKlaseWychowankow);
     		
     		//Dodanie paneli do okna
    		frame.add(p1, BorderLayout.NORTH);
    		frame.add(p2);
    		frame.add(pLeft, BorderLayout.WEST);
    		frame.add(pCenter, BorderLayout.CENTER);
    		frame.setVisible(true);
    		frame.add(pDolny, BorderLayout.SOUTH);	
 	
    	} // ---koniec: private void create GUI(String str) 
        
		// main 
    	public static final void main(String[] args) throws InterruptedException 
    	{ 
    		javax.swing.SwingUtilities.invokeLater(new Runnable()
    		{
    		public void run()
    			{
    			  
    				TwojeKlasy gm = new TwojeKlasy(); 
    				gm.wczytajDane("Michal Klich", null, null, null, null, null);
    			} 
    		});
    	}
    		 
//    	public static boolean czyNauczycielMaJakiesKlasy(String[] arr) {
//     
//    		if (arr == null) {
//    		  System.out.println("Nauczyciel nie posiada ¿adnych klas."); 
//    		}
//    		return arr;
//    	}
    	
    		public void wczytajDane(String folderName, String firstName, String lastName, String[] listOfAllClass, String[] listClassIteach, String KlasaWychowankowie)
    		{  
    			//sprawdzenie czy nauczyciel posiada klasy(aby odpowiednio zaladowac ewentualne klasy do programu jesli sa w pliku jakies)
//    			if(czyNauczycielMaJakiesKlasy(listClassIteach))
//    			{
//    				
//    			}
    			createGui(folderName, firstName, lastName, listOfAllClass, listClassIteach, KlasaWychowankowie);
    			for (String element : listClassIteach)
    			      System.out.printf("%s ", element);
    		}
			 
		/**
    	 * wywoluje g³owne okno aplikacji gdy uzytkownik wprowadzi poprawne dane w oknie logowania
    	 */
//    	public void UruchomKlase(String str)
//    	{   
//    		//String temp = str;
//    		createDirectoryIfNeeded(str, null, null, null, null, null); 
//    	}
    
    	private void wysrodkujOkno(JFrame frame) {
    		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    		 int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
    		 int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
    		 frame.setLocation(x, y);
    	}
    
    	//uruchamia klase 'zarzadzanieKlasami.java'
    	
    } // end: TwojeKlasy.java