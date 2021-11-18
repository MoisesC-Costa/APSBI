package server.model.jdbc.testes;

import java.util.Calendar;

import server.model.bean.Ocorrencia;
import server.model.jdbc.dao.OcorrenciaDao;

public class CreateOcorrencia {
	public static void main(String[] args) {
		Ocorrencia ocorrencia = new Ocorrencia();
		OcorrenciaDao dao = new OcorrenciaDao();
		
		ocorrencia.setDataOcorrencia(Calendar.getInstance());
		ocorrencia.setLongitude(0);
		ocorrencia.setLatitude(0);
		ocorrencia.setBioma("Cogumelos");
		ocorrencia.setEstado("Acre");
		ocorrencia.setMunicipio("Não existe Bixo");
		
		dao.createOcorrencia(ocorrencia);
		
	}
	
}
