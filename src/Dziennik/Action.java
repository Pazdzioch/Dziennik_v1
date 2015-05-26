package Dziennik;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Action implements ActionListener
{
	 String pobranaNazwaKlasy = "";
	   
    @Override
    public void actionPerformed(ActionEvent e)
    {
    	Object z = e.getSource();
    	
    	// Akcja do wyswietlenia nazwy wpisanej klasy:
        if(z == AddClass.btnDodaj_Klase)
        { 
        	try
        	{
        		pobranaNazwaKlasy = AddClass.txt_nazwa_Klasy.getText();
        		System.out.println("Nazwa klasy: " + pobranaNazwaKlasy);
        	}
        	catch(Exception exc)
        	{
        		System.out.println("Nie udalo siê pobraæ nazwy");
        	}
        }
    }
    
}