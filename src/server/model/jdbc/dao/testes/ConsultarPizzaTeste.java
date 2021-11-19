package server.model.jdbc.dao.testes;

import org.json.JSONObject;

import server.model.jdbc.dao.CasosPorBiomasDao;

public class ConsultarPizzaTeste {
	public static void main(String[] args) {
		CasosPorBiomasDao dao = new CasosPorBiomasDao();
	
		JSONObject json = dao.getCasos();
		
		System.out.println(json);
		
	}
	
}
