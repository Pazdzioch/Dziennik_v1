package Dziennik;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class Data {
	public static final void main(String[] args) throws InterruptedException 
	{

		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
		public void run()
			{
			 LoadData loadData = new LoadData();
			 loadData.takeData(null, null, null, args, args, args);
			 setLogedUser("jakis user");
			}
		
	 

		public void setLogedUser(String FullName) 
		{
			String LogedUser = FullName;
			  
			File activeUserFolder = new File("c:\\dziennik\\data\\activeUser");
			//jesli folder 'zalogowanyUser' nie istnieje stworz go
			  if (!activeUserFolder.exists()) 
    		  {
				  activeUserFolder.mkdir();
    		  } 
			  
			  File fActiveUserTxt = new File("C:/dziennik/data/activeUser/activeUser.txt.txt");
				try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fActiveUserTxt, false))))
				{
				    out.println(LogedUser);  
				    System.out.println("Udalo sie dodac klase(nowa linijke do pliku txt");
					JOptionPane.showMessageDialog(null, "Dodano nazwe klasy pomyœlnie.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		} 
		
		public void getUserData(String fullName)
		{
			
		}
		
		});
	}
}
