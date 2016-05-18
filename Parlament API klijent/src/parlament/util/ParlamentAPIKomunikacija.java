package parlament.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import parlament.domain.Poslanik;

public class ParlamentAPIKomunikacija {
	
	private static final String parlamentURL = "http://147.91.128.71:9090/parlament/api/members";
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		
	public LinkedList<Poslanik> vratiPoslanike() throws ParseException {
		String result;
		try {
			result = sendGet(parlamentURL);
		
		Gson gson = new GsonBuilder().create();
		JsonArray poslaniciJson = gson.fromJson(result, JsonArray.class);
		
		LinkedList<Poslanik> poslanici = new LinkedList<Poslanik>();
		
		for (int i = 0; i < poslaniciJson.size(); i++) {
			JsonObject poslanikJson = (JsonObject) poslaniciJson.get(i);
			
			Poslanik p = new Poslanik();
			p.setId(poslanikJson.get("id").getAsInt());
			p.setIme(poslanikJson.get("name").getAsString());
			p.setPrezime(poslanikJson.get("lastName").getAsString());
			if(poslanikJson.get("birthDate") != null)
			p.setDatumRodjenja(sdf.parse(poslanikJson.get("birthDate").getAsString()));
			
			poslanici.add(p);
		}
		return poslanici;
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new LinkedList<Poslanik>();	
	}

	private String sendGet(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
		
		connection.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		boolean endReading = false;
		String response = "";
		
		while(endReading == false) {
			String s = in.readLine();
			if(s != null)
				response += s;
			else
				endReading = true;
		}
		in.close();
		return response.toString();
	}
}
