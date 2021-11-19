package server.controller.logic;

import org.json.JSONArray;
import org.json.JSONObject;

import server.model.jdbc.dao.OcorrenciaDao;
import server.session.Session;
import server.thread.DBQueimadasUploader;

public class UploadDBQueimadasLogic implements Logic {

	@Override
	public void exec(JSONObject packet, Session session) {
		String autToken = packet.getString("token");
		Session.autToken(autToken);


		OcorrenciaDao dao = new OcorrenciaDao();

		JSONObject csv = packet.getJSONObject("csv");
		session.response(new JSONObject("{'code':true}"));

		JSONArray array = csv.getJSONArray("data");

		// lançar a insersão de dados em outra thread
		// para não blocar a comunicação
		new Thread(new DBQueimadasUploader(dao, array)).start();


	}

}
