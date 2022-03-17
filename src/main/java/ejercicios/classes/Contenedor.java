package main.java.ejercicios.classes;

public record Contenedor(String id, Integer capacidad, String tipo) {
	public static Contenedor of(String id, Integer capacidad, String tipo) {
		return new Contenedor(id, capacidad, tipo);
	}
	
	public static Contenedor parse(String linea) {
		var info_c = linea.split(":")[1].split(";");
		return of(linea.split(":")[0].trim(), Integer.parseInt(info_c[0].split("=")[1]),info_c[1].split("=")[1]);
	}

	@Override
	public String toString() {
		return "Contenedor [ID = " + id + " Capacidad = " + capacidad +" Tipo = " + tipo + "]";
	}
	
	
}
