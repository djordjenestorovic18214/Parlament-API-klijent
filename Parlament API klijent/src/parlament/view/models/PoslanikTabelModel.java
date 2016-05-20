package parlament.view.models;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import java.util.Date;
import parlament.domain.Poslanik;

public class PoslanikTabelModel extends AbstractTableModel {
	
	private static String[] kolone = new String[]{"ID", "Name", "Last name", "Birth date"};
	private LinkedList<Poslanik> poslanici;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
	
	public PoslanikTabelModel(LinkedList<Poslanik> poslanici) {
		if (poslanici == null) {
			this.poslanici = new LinkedList<Poslanik>();
		} else {
			this.poslanici = poslanici;
		}
	}

	public int getColumnCount() {
		return kolone.length;
	}
	
	public String getColumnName(int column) {
		return kolone[column];
	}

	public int getRowCount() {
			return poslanici.size();
	}
	
	public Poslanik getRowValue(int rowIndex) {
/*		Bezuspesan pokusaj resavanja problema formata datuma
 * 
 * Poslanik p = new Poslanik();
		int id = (Integer) getValueAt(rowIndex, 0);
		String ime = (String) getValueAt(rowIndex, 1);
		String prezime = (String) getValueAt(rowIndex, 2);
		Date dRodjenja;
		try {
			if(getValueAt(rowIndex, 3) != null)
				dRodjenja = sdf.parse((String) getValueAt(rowIndex, 3));
			else dRodjenja = null;
		p.setId(id);
		p.setIme(ime);
		p.setPrezime(prezime);
		p.setDatumRodjenja(dRodjenja);
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		*/
		return poslanici.get(rowIndex);
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		Poslanik p = poslanici.get(rowIndex);
				
			switch (columnIndex) {
			case 0:		return p.getId();
			case 1:		return p.getIme();
			case 2:		return p.getPrezime();		
			case 3:	if(p.getDatumRodjenja() != null)
						return sdf.format(p.getDatumRodjenja());
					else return null;
			default:	return "N/A";
					}
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		String value = (String) aValue;
			if(columnIndex == 1) {
				try {
					poslanici.get(rowIndex).setIme(value);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Morate uneti ime.", "Greska pri unosu imena", JOptionPane.ERROR_MESSAGE);
				}
			} else if(columnIndex == 2) {	
				try {
					poslanici.get(rowIndex).setPrezime(value);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Morate uneti prezime.", "Greska pri unosu prezimena", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(columnIndex == 3) {
				try {
				if(value != null)
					poslanici.get(rowIndex).setDatumRodjenja(sdf.parse(value));
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, "Datum mora biti u formatu 'dd.mm.yyyy.'", "Greska pri unosu datuma", JOptionPane.ERROR_MESSAGE);
				}
			}
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(getColumnName(columnIndex) == "ID")
			return false;
		else return true;
	}
	
	public void ucitajTabelu(LinkedList<Poslanik> poslanici) {
		this.poslanici = poslanici;
		fireTableDataChanged();
	}
}