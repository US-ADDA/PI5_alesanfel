package main.java.ejercicios.classes;

import java.util.List;

import us.lsi.common.List2;

public record Candidato(String id, List<String> cualidadesPorCandidato, 
		                List<String> incompatibilidadesPorCandidato, Double sueldo, Integer valoracion) {
	public static Candidato of(String id, List<String> cualidadesPorCandidato, 
            List<String> incompatibilidadesPorCandidato,Double sueldo,Integer valoracion) {
		return new Candidato(id, cualidadesPorCandidato, incompatibilidadesPorCandidato, sueldo, valoracion);
	}
	
	public static Candidato parse(String linea) {
		var data = linea.split(":")[1].split(";");
		var cualidadesPorCandidato = List2.parse(data[0], ",", String::trim);
		List<String> incompatibilidadesPorCandidato = List2.parse(data[3], ",", String::trim);
		return Candidato.of(linea.split(":")[0], cualidadesPorCandidato, incompatibilidadesPorCandidato, Double.parseDouble(data[1].trim()), Integer.parseInt(data[2].trim()));
	}

	@Override
	public String toString() {
		return id + ": " + cualidadesPorCandidato + "; " + sueldo + "; " + valoracion + "; " + incompatibilidadesPorCandidato;
	}
}
