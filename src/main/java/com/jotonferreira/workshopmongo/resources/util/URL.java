package com.jotonferreira.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {
	
	public static String decodeParam(String text) {
		
		try {
			return URLDecoder.decode(text, "UTF-8"); // retorna a string decodificada
		} catch (UnsupportedEncodingException e) {
			
			return ""; // caso tenha exceção retorna vazia
		}
	}

}
