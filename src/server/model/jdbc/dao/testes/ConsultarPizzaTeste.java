package server.model.jdbc.dao.testes;

import java.util.Calendar;

import org.json.JSONObject;

import server.model.jdbc.dao.CasosPorBiomasDao;

public class ConsultarPizzaTeste {
	public static void main(String[] args) {
		CasosPorBiomasDao dao = new CasosPorBiomasDao();
	
		Calendar date = Calendar.getInstance();
		date.set(2021, 1, 1);
		
		JSONObject json = dao.getCasos(date);
		
		System.out.println(json);
		
	}
	
}
