package main.java.ejercicios.data;

import java.util.List;

import main.java.ejercicios.classes.Contenedor;
import main.java.ejercicios.classes.Elemento;
import us.lsi.common.Files2;
import us.lsi.common.List2;

public class DatosEjercicio4 {

	private static List<Elemento> ELEMENTOS;
	private static List<Contenedor> CONTENEDORES;
	
	public static void initDatos(String path) {
		CONTENEDORES = List2.empty();
		ELEMENTOS = List2.empty();
		for (var linea: Files2.linesFromFile(path)) {
			if (linea.contains("CONT") && !linea.contains("//"))
				CONTENEDORES.add(Contenedor.parse(linea));
			else if (linea.contains("E") && !linea.contains("//"))
				ELEMENTOS.add(Elemento.parse(linea));
		}
	}
	
	// Métodos para elementos.
	public static Integer getTamanoElemento(Integer i) {
		return ELEMENTOS.get(i).tamano();
	}
	
	public static Elemento getElemento(Integer i) {
		return ELEMENTOS.get(i);
	}
	
	public static Integer getNumElementos() {
		return ELEMENTOS.size();
	}
	
	// Métodos para contenedores.
	public static Integer getCapacidadContenedor(Integer j) {
		return CONTENEDORES.get(j).capacidad();
	}
	
	public static Contenedor getContenedor(Integer j) {
		return CONTENEDORES.get(j);
	}
	
	public static Integer getNumContenedores() {
		return CONTENEDORES.size();
	}
	
	// Métodos para ambos.
	public static Boolean esCompatible(Integer i, Integer j) {
		return ELEMENTOS.get(i).posiblesContenedores().contains(CONTENEDORES.get(j).tipo());
	}
	
	public static void main(String[] args) {
		initDatos("data/PI5Ej4DatosEntrada1.txt");
		System.out.println("Datos elementos -> " + ELEMENTOS);
		System.out.println("Tamaño del primer elemento -> " + getTamanoElemento(0));
		System.out.println("Número de elementos -> " + getNumElementos());
		System.out.println("Datos contenedores -> " + CONTENEDORES);
		System.out.println("Capacidad del primer contenedor -> " + getCapacidadContenedor(0));
		System.out.println("Número de contenedores -> " + getNumContenedores());
		System.out.println("¿El primer elemento es compatible con el primer contenedor? -> " + (esCompatible(0, 0) ? "Si": "NO"));
		
	}
}
