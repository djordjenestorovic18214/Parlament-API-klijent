package parlament.view.models;

import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

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
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		Poslanik p = poslanici.get(rowIndex);
		
		switch (columnIndex) {
		case 0:		return p.getId();
		case 1:		return p.getIme();
		case 2:		return p.getPrezime();		
		case 3:		if(p.getDatumRodjenja() != null)
				return sdf.format(p.getDatumRodjenja());
					else return null;
		default:	return "N/A";
		}
	}
	
//	public void setValueAt(int rowIndex, int columnIndex) {
//		if(isCellEditable(rowIndex, columnIndex)) {
//			Poslanik p = poslanici.get(rowIndex);
//			
//			switch (columnIndex) {
//			case 1:		p.setIme((String) getValueAt(rowIndex, columnIndex));
//			case 2:		p.setPrezime((String) getValueAt(rowIndex, columnIndex));	
//			case 3:		try {
//					p.setDatumRodjenja(sdf.parse((String) getValueAt(rowIndex, columnIndex)));
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			default:	break;
//			}
//		} else throw new RuntimeException("Polje za ID ne sme biti izmenjeno.");
//	}
	
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