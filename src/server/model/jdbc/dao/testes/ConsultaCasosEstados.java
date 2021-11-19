package server.model.jdbc.dao.testes;

import org.json.JSONObject;

import server.model.jdbc.dao.CasosPorEstadosDao;

public class ConsultaCasosEstados {

	public static void main(String[] args) {
		CasosPorEstadosDao dao = new CasosPorEstadosDao();
		
		JSONObject json = dao.getCasos();

		System.out.println(json);
		
	}
	
}