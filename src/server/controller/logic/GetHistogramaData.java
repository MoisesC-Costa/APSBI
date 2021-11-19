package server.controller.logic;

import org.json.JSONArray;
import org.json.JSONObject;

import server.model.jdbc.dao.EvolucaoDosCasosDao;
import server.session.Session;

public class GetHistogramaData implements Logic{

	@Override
	public void exec(JSONObject packet, Session session) {
		String autToken = packet.getString("token");
		Session.autToken(autToken);

		EvolucaoDosCasosDao dao = new EvolucaoDosCasosDao();

		JSONObject response = new JSONObject();
		
		try {
			JSONArray data = dao.getCasos(2021);
			
			response.put("code", true);
			response.put("data", data);
			
		} catch (Exception e) {
			response.put("code", false);
			response.put("description", "Ocorreu um problema na solicitação dos dados");
		}
		
		session.response(response);
		
	}

}
