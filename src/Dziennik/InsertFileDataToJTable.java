package Dziennik;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel; 
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class InsertFileDataToJTable extends AbstractTableModel
{
	Vector data;
	Vector columns;

	public InsertFileDataToJTable(String fullname)
	{
		
		String line;
		data = new Vector();
		columns = new Vector();
		File file = new File("c:\\dziennik\\users\\" + fullname + "\\studenci.txt"); 
		try {
				FileInputStream fis = new FileInputStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
				while(st1.hasMoreTokens())
					columns.addElement(st1.nextToken());
				 	
				while((line=br.readLine())!=null)
				{
					StringTokenizer st2 = new StringTokenizer(line, " ");
					while(st2.hasMoreTokens())
						data.addElement(st2.nextToken());
				}
			
				br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getRowCount()
	{
		return data.size() / getColumnCount();
	}
	
	public int getColumnCount()
	{ 
		return columns.size();
	}
	
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		return (String)
				data.elementAt((rowIndex * getColumnCount()) + columnIndex);
	}
	
	public static void main(String s[])
	{
		InsertFileDataToJTable model = new InsertFileDataToJTable("Michal Klich"); 
		JTable table = new JTable();
		table.setModel(model);
		JScrollPane scrollpane = new JScrollPane(table   );
		JPanel panel = new JPanel();
		panel.add(scrollpane);
		JFrame frame = new JFrame();
		frame.add(panel, "Center");
		frame.pack();
		frame.setVisible(true);
		
		//zmien nazwy column na 'imie' 'nazwisko'
		String[] tableHeader = {"Imie", "Nazwisko"};
		JTableHeader th = table.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		System.out.println(model.getColumnCount());
		for (int i = 0; i<=model.getColumnCount(); i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue( tableHeader[i].toString() );
			th.repaint(); 
		}
	}
} 