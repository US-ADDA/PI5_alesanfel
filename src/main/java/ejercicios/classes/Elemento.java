package main.java.ejercicios.classes;

import java.util.List;

import us.lsi.common.List2;

public record Elemento(String id, Integer tamano, List<String> posiblesContenedores) {
	public static Elemento of(String id, Integer tamano, List<String> posiblesContenedores) {
		return new Elemento(id, tamano, posiblesContenedores);
	}
	
	public static Elemento parse(String linea) {
		var info_e = linea.split(":")[1].split(";");
		var id = linea.split(": ")[0].trim();
		var tamano = Integer.parseInt(info_e[0].trim());
		var posiblesContenedores = List2.parse(info_e[1],",", String::trim);
		return of(id, tamano, posiblesContenedores);
	}

	@Override
	public String toString() {
		return "Elemento [ID = " + id + "Tamaño = " + tamano + "Posbles contenedores = " + posiblesContenedores + "]";
	}
	
	
}
