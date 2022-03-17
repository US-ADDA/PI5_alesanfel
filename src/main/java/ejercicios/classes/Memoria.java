package main.java.ejercicios.classes;

public record Memoria(String id, Integer capacidad, Integer tamanoMaximo) {
	
	public static Memoria of(String id, Integer capacidad, Integer tamanoMaximo) {
		return new Memoria(id, capacidad, tamanoMaximo);
	}
	
	public static Memoria parse(String linea) {
		var info_mem = linea.split(":")[1].split(";");
		var id = linea.split(":")[0];
		var capacidad = Integer.parseInt(info_mem[0].split("=")[1].trim());
		var tam_max = Integer.parseInt(info_mem[1].split("=")[1].trim());
		return of(id, capacidad, tam_max);
	}

	@Override
	public String toString() {
		return "Memoria [ID = " + id  + ", Capacidad = " + capacidad + ", Tamaño máximo = " + tamanoMaximo + "]";
	}
	
	
}
