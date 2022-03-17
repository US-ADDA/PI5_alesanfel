package main.java.ejercicios.classes;

public record Fichero(String id, Integer capacidad) {
	
	public static Fichero of(String id, Integer capacidad) {
		return new Fichero(id, capacidad);
	}
	
	public static Fichero parse(String linea) {
		return of(linea.split(":")[0], Integer.parseInt(linea.split(":")[1].trim()));
	}

	@Override
	public String toString() {
		return "Fichero [ID = " + id + "Capacidad = " + capacidad + "]";
	}
}
