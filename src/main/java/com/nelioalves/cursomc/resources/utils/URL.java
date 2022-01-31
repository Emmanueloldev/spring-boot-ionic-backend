package com.nelioalves.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Integer> decodeIntList(String s){
		String[] vet = s.split(",");  // vetor de strings "vet" recebendo s.split baseado na virgula(",")
		List<Integer> list = new ArrayList<>();
		for (int i=0; i<vet.length; i++) { // percorre o vetor
			list.add(Integer.parseInt(vet[i])); // pra cada posição do vetor p  converter pra inteiro... // pra cada elemento do vetor vet list.add 
		}
		return list;
		//Ta no GITHUB//ao invés de fazer o de cima faz esse >>> return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}

}
