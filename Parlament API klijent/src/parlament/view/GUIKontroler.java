package parlament.view;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

public class GUIKontroler {
	private static ParlamentGUI glavniProzor;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					glavniProzor = new ParlamentGUI();
					glavniProzor.setVisible(true);
					glavniProzor.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void ugasiAplikaciju() {
		int opcija = JOptionPane.showConfirmDialog(glavniProzor.getContentPane(), "Da li zaista zelite da ugasite aplikaciju?",
				"Zatvaranje aplikacije...", JOptionPane.YES_NO_OPTION);
		if(opcija == JOptionPane.YES_OPTION)
			System.exit(0);
	}

}
