package server.controller.logic;

import java.util.Calendar;

import org.json.JSONObject;

import server.model.jdbc.dao.CasosPorBiomasDao;
import server.session.Session;

// Pegar os dados do grafico de pizza
public class GetPizzaData implements Logic {

	@Override
	public void exec(JSONObject packet, Session session) {
		String autToken = packet.getString("token");
		Session.autToken(autToken);

		CasosPorBiomasDao dao = new CasosPorBiomasDao();

		Calendar ano = Calendar.getInstance();
		ano.setTimeInMillis(packet.getLong("date"));

		JSONObject response = new JSONObject();
		
		try {
			JSONObject data = dao.getCasos(ano);
			
			response.put("code", true);
			response.put("data", data);
			
		} catch (Exception e) {
			response.put("code", false);
			response.put("description", "Ocorreu um problema na solicitação dos dados");
		}
		
		session.response(response);
		
	}
}
