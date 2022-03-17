package main.java.ejercicios.data;

import java.util.List;

import main.java.ejercicios.classes.Fichero;
import main.java.ejercicios.classes.Memoria;
import us.lsi.common.Files2;
import us.lsi.common.List2;

public class DatosEjercicio1 {

	private static List<Memoria> MEMORIAS;
	private static List<Fichero> FICHEROS;
	
	public static void initDatos(String path) {
		MEMORIAS = List2.empty();
		FICHEROS = List2.empty();
		for (var linea: Files2.linesFromFile(path)) {
			
			if (linea.contains("MEM") && !linea.contains("//")) {
				MEMORIAS.add(Memoria.parse(linea));
			} else if (linea.contains("F") && !linea.contains("//"))
				FICHEROS.add(Fichero.parse(linea));
		}
	}
	
	// Métodos para memoria.
	public static Integer getCapacidadMemoria(Integer j) {
		System.out.println("hola");
		return MEMORIAS.get(j).capacidad();
	}
	
	public static Integer getMaxTamanoMemoria(Integer j) {
		return MEMORIAS.get(j).tamanoMaximo();
	}
	
	public static Memoria getMemoria(Integer j) {
		return MEMORIAS.get(j);
	}
	
	public static Integer getNumMemoria() {
		return MEMORIAS.size();
	}
	
	// Métodos para ficheros.
	public static Integer getCapacidadFichero(Integer i) {
		return FICHEROS.get(i).capacidad();
	}
	
	public static Fichero getFichero(Integer i) {
		return FICHEROS.get(i);
	}
	
	public static Integer getNumFichero() {
		return FICHEROS.size();
	}
	
	public static void main(String[] args) {
		initDatos("data/PI5Ej1DatosEntrada1.txt");
		System.out.println("Datos memorias -> " + MEMORIAS);
		System.out.println("Capacidad primera memoria -> " + getCapacidadMemoria(0));
		System.out.println("Número de memorias -> " + getNumMemoria());
		System.out.println("Datos ficheros -> " + FICHEROS);
		System.out.println("Capacidad primer fichero -> " + getCapacidadFichero(0));
		System.out.println("Máximo tamaño de fichero en la primera memoria -> " + getMaxTamanoMemoria(0));
		System.out.println("Número de ficheros -> " + getNumFichero());
	}
}
