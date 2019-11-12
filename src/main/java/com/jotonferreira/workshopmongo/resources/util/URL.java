package com.jotonferreira.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {
	
	// decodifica a string do URL
	public static String decodeParam(String text) {
		
		try {
			return URLDecoder.decode(text, "UTF-8"); // retorna a string decodificada
		} catch (UnsupportedEncodingException e) {
			
			return ""; // caso tenha exceção retorna vazia
		}
	}
	
	// converte data
	public static Date convertDate(String textDate, Date defaultValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return sdf.parse(textDate);
		} catch (ParseException e) {
			return defaultValue;
		}
	}

}
