package parlament.view;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import parlament.domain.Poslanik;
import parlament.util.ParlamentAPIKomunikacija;

public class GUIKontroler {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
	private static LinkedList<Poslanik> poslanici;
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
		try {
			JsonArray poslanici = ParlamentAPIKomunikacija.serijalizujPoslanike(ParlamentAPIKomunikacija.vratiPoslanike());
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/serviceMembers.json")));
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String poslaniciString = gson.toJson(poslanici);
		
			out.write(poslaniciString);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public static void ubaciPoslanikeIzFajla() {
		poslanici = new LinkedList<Poslanik>();
		try {
		BufferedReader in = new BufferedReader(new FileReader("data/serviceMembers.json"));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonArray poslaniciJson = gson.fromJson(in, JsonArray.class);

		for (int i = 0; i < poslaniciJson.size(); i++) {
			JsonObject poslanikJson = (JsonObject) poslaniciJson.get(i);
			
			Poslanik p = new Poslanik();
			p.setId(poslanikJson.get("id").getAsInt());
			p.setIme(poslanikJson.get("ime").getAsString());
			p.setPrezime(poslanikJson.get("prezime").getAsString());
			if(poslanikJson.get("datumRodjenja") != null)
				p.setDatumRodjenja(sdf.parse(poslanikJson.get("datumRodjenja").getAsString()));
			poslanici.add(p);
			in.close();
		}
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(glavniProzor.getContentPane(), "Fajl nije pronadjen.", "Greska", JOptionPane.ERROR_MESSAGE);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(glavniProzor.getContentPane(), "Greska kod unosa datuma", "Greska", JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(glavniProzor.getContentPane(), "Fajl je prazan.", "Greska", JOptionPane.ERROR_MESSAGE);
			}	
		}
	
	public static LinkedList<Poslanik> vratiListuPoslanika() {
		return poslanici;		
	}
	
	public static void fillTable() {
		ubaciPoslanikeIzFajla();
		glavniProzor.osveziTabelu();
	}
	
	public static void ugasiAplikaciju() {
		int opcija = JOptionPane.showConfirmDialog(glavniProzor.getContentPane(), "Da li zaista zelite da ugasite aplikaciju?",
				"Zatvaranje aplikacije...", JOptionPane.YES_NO_OPTION);
		if(opcija == JOptionPane.YES_OPTION)
			System.exit(0);
	}
}
