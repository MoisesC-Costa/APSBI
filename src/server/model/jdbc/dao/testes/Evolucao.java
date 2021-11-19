package server.model.jdbc.dao.testes;

import org.json.JSONArray;

import server.model.jdbc.dao.EvolucaoDosCasosDao;

public class Evolucao {

	public static void main(String[] args) {
		EvolucaoDosCasosDao dao = new EvolucaoDosCasosDao();
		
		JSONArray data = dao.getCasos(2021);
		
		System.out.print(data);

	}

}
