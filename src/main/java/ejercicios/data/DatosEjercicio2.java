package main.java.ejercicios.data;

import java.util.List;

import main.java.ejercicios.classes.Candidato;
import us.lsi.common.Files2;
import us.lsi.common.List2;

public class DatosEjercicio2 {
	
	private static List<Candidato> CANDIDATOS;
	private static List<String> CUALIDADES;
	private static Integer PRESUPUESTO_MAXIMO;
	
	public static void initDatos(String path) {
		CANDIDATOS = List2.empty();
		CUALIDADES = List2.empty();
		for (var linea: Files2.linesFromFile(path)) {
			if (linea.contains("Cualidades Deseadas: "))
				CUALIDADES = List2.parse(linea.split(":")[1], ",", String::trim);
			else if (linea.contains("Presupuesto Máximo: "))
				PRESUPUESTO_MAXIMO = Integer.parseInt(linea.split(":")[1].trim());
			else 
				CANDIDATOS.add(Candidato.parse(linea));
		}
	}
	
	// Métodos para candidatos
	public static Integer getValoracion(Integer i) {
		return CANDIDATOS.get(i).valoracion();
	}
	
	public static Double getSueldo(Integer i) {
		return CANDIDATOS.get(i).sueldo();
	}
	
	public static Boolean esCompatible(Integer i, Integer k) {
		return CANDIDATOS.get(i).incompatibilidadesPorCandidato().contains(CANDIDATOS.get(k).id());
	}
	
	public static Integer tieneCualidad(Integer i, Integer k) {
		return CANDIDATOS.get(i).cualidadesPorCandidato().contains(CUALIDADES.get(k)) ? 1: 0;
	}
	
	public static Candidato getCandidato(Integer i) {
		return CANDIDATOS.get(i);
	}
	
	public static Integer getNumCandidatos() {
		return CANDIDATOS.size();
	}
	
	// Método para cualidades.
	public static Integer getNumCualidades() {
		return CUALIDADES.size();
	}
	
	// Otro método.
	public static Integer getPresupuesto() {
		return PRESUPUESTO_MAXIMO;
	}
	
	
	
	public static void main(String[] args) {
		initDatos("data/PI5Ej2DatosEntrada1.txt");
		System.out.println("Datos candidatos -> " + CANDIDATOS);
		System.out.println("Valoración del primer candidato -> " + getValoracion(0));
		System.out.println("¿Primer candidato es compatible con segundo candidato? -> " + (esCompatible(0, 1) ? "Si": "No")) ;
		System.out.println("Sueldo del primer candidato -> " + getSueldo(0));
		System.out.println("Primer candidato -> " + getCandidato(0));
		System.out.println("Número de candidatos -> " + getNumCandidatos());
		System.out.println("Posibles cualidades -> " + CUALIDADES);
		System.out.println("Número de cualidades -> " + getNumCualidades());
		System.out.println("Presupuesto máximo -> " + getPresupuesto());
	}
}
