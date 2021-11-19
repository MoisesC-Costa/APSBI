package server.controller.logic;

import org.json.JSONObject;

import server.model.jdbc.dao.CasosPorEstadosDao;
import server.session.Session;

// Pegar os dados do grafico de pizza
public class GetBarraData implements Logic {

	@Override
	public void exec(JSONObject packet, Session session) {
		String autToken = packet.getString("token");
		Session.autToken(autToken);

		CasosPorEstadosDao dao = new CasosPorEstadosDao();

		JSONObject response = new JSONObject();
		
		try {
			JSONObject data = dao.getCasos();
			
			response.put("code", true);
			response.put("data", data);
			
		} catch (Exception e) {
			response.put("code", false);
			response.put("description", "Ocorreu um problema na solicitação dos dados");
		}
		
		session.response(response);
		
	}
}
