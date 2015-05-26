package Dziennik; 
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddStudent {
	
	private static JButton  bAdd, bSaveAndExit, bExitWithoutSave;
	 
	public void createGui(String fullname)
	{
		
		JFrame f = new JFrame("Dodawanie uczniów");
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setSize(740, 130);
		f.setVisible(true);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - f.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - f.getHeight()) / 2);
	    f.setLocation(x, y);
	   
	    JPanel p = new JPanel();
		p.setLayout(null);
		
		JTextField txtImie = new JTextField("imie");
		txtImie.setBounds(10, 5, 150, 20);
		txtImie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtImie.setText("");
			}
		});
		p.add(txtImie);
		
		JTextField txtNazwisko = new JTextField("nazwisko");
		txtNazwisko.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtNazwisko.setText("");
			}
		});
		txtNazwisko.setBounds(160, 5, 260, 20);
		p.add(txtNazwisko);
		
		bAdd = new JButton("Dodaj"); 
		bAdd.setBounds(450, 5, 260, 20);
		bAdd.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				 System.out.println("Button work");
				 File file = new File("c:\\dziennik\\users\\" + fullname + "\\studenci.txt");
				 FileWriter fileWriter;
				 if(!file.exists() && !file.isDirectory())
				 {
					 try 
					 {
						 	//tworzy plik txt
						 	fileWriter = new FileWriter(file);
 							PrintWriter printWriter = new PrintWriter(fileWriter);
 							
 							//Dodaj tekst do pliku nie usuwaj¹c zawartoœci obecnej w pliku
 							try(PrintWriter output = new PrintWriter(new FileWriter(file,false))) 
							{ 
							    output.printf("%s\r\n", txtImie.getText() + " " + txtNazwisko.getText());
							    System.out.println("Dodano studenta: " +  txtImie.getText() + " " +   txtNazwisko.getText())  ; 
							    txtImie.setText("");  txtNazwisko.setText("");
							}  
 					}
					 catch (IOException e2) 
					 {
 							e2.printStackTrace();
 					}
				 }
				 
				 if(file.exists() && !file.isDirectory())
				 {
					 try 
					 {
							//Dodaj tekst do pliku nie usuwaj¹c zawartoœci obecnej w pliku
							try(PrintWriter output = new PrintWriter(new FileWriter(file,true))) 
							{
							    output.printf("%s\r\n", txtImie.getText() + " " + txtNazwisko.getText());
							    System.out.println("Dodano studenta: " +  txtImie.getText() + " " +   txtNazwisko.getText())  ; 
							    txtImie.setText("");  txtNazwisko.setText("");
							}  
					}
					 
					 catch (IOException e2)
					 		{
								e2.printStackTrace();						
							 }
				 	}
			}
		});
		
		p.add(bAdd);
		f.add(p);
		
	}
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
            public void run() {
            	 AddStudent as = new AddStudent();
            	 as.createGui("Michal Klich");
 				 
            	 //Komenda wyzej zakomentowana bo po wcisnieciu przycisku "dodaj klase" okno uruchamia sie dwa razy  
            }
        });
	}
}