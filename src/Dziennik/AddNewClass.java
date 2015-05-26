package Dziennik;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class AddNewClass {

	private JFrame frame;
	private final JPanel panel = new JPanel();
	private JTextField nazwaKlasy;
	private JTextField rocznikOd;
	private JTextField rocznikDo;
	private JTextField iloscUczniow;
	public String[] args = {};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewClass window = new AddNewClass();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddNewClass() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("info"));
		frame.setBounds(100, 100, 454, 376);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 11, 438, 38);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNowaKlasa = new JLabel("Nowa Klasa");
		lblNowaKlasa.setForeground(SystemColor.inactiveCaptionText);
		lblNowaKlasa.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNowaKlasa.setBounds(10, 11, 102, 14);
		panel.add(lblNowaKlasa);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 49, 438, 300);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(410, 236, -399, -6);
		panel_1.add(separator);
		
		JLabel lblNazwaKlasy = new JLabel("Nazwa Klasy");
		lblNazwaKlasy.setFont(new Font("Verdana", Font.ITALIC, 11));
		lblNazwaKlasy.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNazwaKlasy.setForeground(SystemColor.inactiveCaptionText);
		lblNazwaKlasy.setBounds(51, 11, 103, 32);
		panel_1.add(lblNazwaKlasy);
		
		nazwaKlasy = new JTextField();
		nazwaKlasy.setBounds(170, 11, 160, 32);
		panel_1.add(nazwaKlasy);
		nazwaKlasy.setColumns(10);
		
		rocznikOd = new JTextField();
		rocznikOd.setBounds(170, 54, 75, 23);
		panel_1.add(rocznikOd);
		rocznikOd.setColumns(10);
		
		rocznikDo = new JTextField();
		rocznikDo.setColumns(10);
		rocznikDo.setBounds(255, 54, 75, 23);
		panel_1.add(rocznikDo);
		
		JLabel lblRocznik = new JLabel("Rocznik");
		lblRocznik.setFont(new Font("Verdana", Font.ITALIC, 11));
		lblRocznik.setForeground(SystemColor.inactiveCaptionText);
		lblRocznik.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRocznik.setBounds(77, 54, 75, 23);
		panel_1.add(lblRocznik);
		
		JLabel lblIloscUczniw = new JLabel("Ilosc uczni\u00F3w");
		lblIloscUczniw.setFont(new Font("Verdana", Font.ITALIC, 11));
		lblIloscUczniw.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIloscUczniw.setForeground(SystemColor.inactiveCaptionText);
		lblIloscUczniw.setBounds(51, 95, 101, 15);
		panel_1.add(lblIloscUczniw);
		
		iloscUczniow = new JTextField();
		iloscUczniow.setBounds(170, 92, 86, 20);
		panel_1.add(iloscUczniow);
		iloscUczniow.setColumns(10);
		
		JRadioButton radiobtnWychowawca = new JRadioButton("Jestem wychowawc\u0105");
		radiobtnWychowawca.setFont(new Font("Verdana", Font.ITALIC, 11));
		radiobtnWychowawca.setForeground(SystemColor.inactiveCaptionText);
		radiobtnWychowawca.setBounds(170, 119, 164, 23);
		panel_1.add(radiobtnWychowawca);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(1, 256, 437, 32);
		panel_1.add(panel_2);
		
		JButton btnZapisz = new JButton("Zapisz");
		btnZapisz.setBackground(new Color(0, 204, 102));
		btnZapisz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameOfClass = nazwaKlasy.getText();
		 
				File fDodajTuNowaKlase = new File("C:/dziennik/users/Michal Klich/uczyKlasy.txt.txt"); 
				if(fDodajTuNowaKlase.exists() && !fDodajTuNowaKlase.isDirectory())
				{
					System.out.println(" ok folder istnieje");
					//dodaj tekst z txtField "nazwa klasy" jako now¹ klase(now¹ linijkê) do pliku
					try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fDodajTuNowaKlase, true))))
					{
					    out.println(nameOfClass);  
					    System.out.println("Udalo sie dodac klase(nowa linijke do pliku txt");
						JOptionPane.showMessageDialog(null, "Dodano nazwe klasy pomyœlnie.");
						
						
						
					}
					
					catch (IOException e1) 
					{
						 System.out.println("Nie udalo sie dodac nowej linijki do pliku");
					}
					
					frame.dispose();
				}
				else System.out.println("Nie udalo sie zpaisac nowej klasy poniewaz folder uzytkownika z plikiem klas nie istnieje");
			}
		});
		btnZapisz.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnZapisz.setForeground(SystemColor.textHighlightText);
		panel_2.add(btnZapisz);
		
		JButton btnAnuluj = new JButton("Anuluj");
		btnAnuluj.setBackground(Color.white);
		btnAnuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_2.add(btnAnuluj);
		
		JButton btnNewButton = new JButton(" ");
		btnNewButton.setBackground(SystemColor.activeCaptionBorder);
		btnNewButton.setBounds(1, 253, 437, 2);
		panel_1.add(btnNewButton);
	}
}
