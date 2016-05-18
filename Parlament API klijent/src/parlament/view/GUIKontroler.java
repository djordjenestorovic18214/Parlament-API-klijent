package parlament.view;

import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import parlament.util.ParlamentAPIKomunikacija;

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
	public static void getMembers() {
		JsonArray poslanici;
		try {
			poslanici = ParlamentAPIKomunikacija.serijalizujPoslanike(ParlamentAPIKomunikacija.vratiPoslanike());
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/serviceMembers.json")));
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String poslaniciString = gson.toJson(poslanici);
		
			out.println(poslaniciString);
			out.close();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public static void ugasiAplikaciju() {
		int opcija = JOptionPane.showConfirmDialog(glavniProzor.getContentPane(), "Da li zaista zelite da ugasite aplikaciju?",
				"Zatvaranje aplikacije...", JOptionPane.YES_NO_OPTION);
		if(opcija == JOptionPane.YES_OPTION)
			System.exit(0);
	}	
}
