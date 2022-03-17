package main.java.ejercicios.solution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.ejercicios.classes.Contenedor;
import main.java.ejercicios.classes.Elemento;
import main.java.ejercicios.data.DatosEjercicio4;
import us.lsi.common.List2;
import us.lsi.common.String2;
import us.lsi.gurobi.GurobiSolution;

public class SolucionEjercicio4 {
	
	private static Map<Contenedor, List<Elemento>> elementosPorContenedor;
	
	public static SolucionEjercicio4 create(GurobiSolution gs) {
		return new SolucionEjercicio4(gs.objVal, gs.values);
	}	
	


	private SolucionEjercicio4(Double vo, Map<String, Double> vbles) {
		elementosPorContenedor = new HashMap<>();
		for (var data: vbles.entrySet()) {
			if (data.getValue()>0 && data.getKey().startsWith("x")) {
				var info_x = data.getKey().split("_");
				var value = DatosEjercicio4.getElemento(Integer.parseInt(info_x[1]));
				var key = DatosEjercicio4.getContenedor(Integer.parseInt(info_x[2]));
				if (elementosPorContenedor.containsKey(key))
					elementosPorContenedor.get(key).add(value);
				else 
					elementosPorContenedor.put(key, List2.of(value));
			}
		}
	}

	@Override
	public String toString() {
		var cadenaContenedores = elementosPorContenedor.entrySet().stream()
				.map(entry -> entry.getKey().id() + ": " + entry.getValue().stream().map(Elemento::id).toList())
				.reduce("", (ac, nx) -> String.format("%s%s\n", ac, nx));
		return String.format("Reparto obtenido:\n%s", cadenaContenedores);
	}
	
	public static void print(GurobiSolution gs) {
		String2.toConsole("%s\n%s\n%s", String2.linea(), create(gs), String2.linea());
	}
}
