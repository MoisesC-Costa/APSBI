package cliente.tools;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class DataConveter {
	
	public static JSONObject converterCSVToJSONObject(String path) 
			throws CsvValidationException, IOException {
		JSONObject json = new JSONObject();

		try {
			CSVReader reader = new CSVReader(new FileReader(path));
			json.put("columns", reader.readNext());
			
			JSONArray array = new JSONArray();
			
			String[] nextLine;
			int c = 0;
			// Limitando a 60000 porque da um delay tremendo para trabalhar
			while ((nextLine = reader.readNext()) != null && c < 60000) {
				array.put(nextLine);
				c++;
			}
			
			json.put("data", array);
			
			return json;
			
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);

		}
		
	}
	
}
