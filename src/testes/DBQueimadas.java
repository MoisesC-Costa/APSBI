package testes;

import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DBQueimadas {
	public static void main(String[] args) {
		String pattern = "yyyy/MM/dd HH:mm:ss";
		Calendar date = Calendar.getInstance();
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			
			date.setTime(sdf.parse("2021/07/02 02:45:01\n"));
			
			System.out.println(date.toString());
			
		} catch (ParseException e) {
			
		}
		System.out.println(date.toString());

	}
}
