package server.threath;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONArray;

import server.model.bean.Ocorrencia;
import server.model.jdbc.dao.OcorrenciaDao;

public class DBQueimadasUploader implements Runnable {
	private OcorrenciaDao dao;
	private JSONArray array;
	
	public DBQueimadasUploader(OcorrenciaDao dao, JSONArray array) {
		this.dao = dao;
		this.array = array;

	}
	
	public void run() {

		try  {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/ddHH:mm:ss");


			// Criando as ocorrencias e enviando elas para o banco de dados
			for (int i = 0 ; i < array.length() ; i++) {
				JSONArray row = array.getJSONArray(i);

				Ocorrencia ocorrencia = new Ocorrencia();

				Calendar date = Calendar.getInstance();
				date.setTime(sdf.parse(row.getString(0).strip()));
				
				ocorrencia.setDataOcorrencia(date);
				ocorrencia.setEstado(row.getString(3));
				ocorrencia.setMunicipio(row.getString(4));
				ocorrencia.setBioma(row.getString(5));
				ocorrencia.setLatitude(row.getDouble(9));
				ocorrencia.setLongitude(row.getDouble(10));
				
				dao.createOcorrencia(ocorrencia);					
				
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	
}
