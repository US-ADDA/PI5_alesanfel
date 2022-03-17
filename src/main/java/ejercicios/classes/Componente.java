package main.java.ejercicios.classes;

public record Componente(String id, Integer tiempoProduccion, Integer tiempoElaboracion) {
	
	public static Componente of(String id, Integer tiempoProduccion, Integer tiempoElaboracion) {
		return new Componente(id, tiempoProduccion, tiempoElaboracion);
	}
	
	public static Componente parse(String linea) {
		var info_c = linea.split(":")[1].split(";");
		var id = linea.split(":")[0];
		var t_prod = Integer.parseInt(info_c[0].split("=")[1].trim());
		var t_elab = Integer.parseInt(info_c[1].split("=")[1].trim());
		return of(id, t_prod, t_elab);
	}

	@Override
	public String toString() {
		return "Componente [ID = " + id + "Tiempo producción = " + tiempoProduccion + " Tiempo elaboración = " + tiempoElaboracion + "]";
	}
	
	

}
