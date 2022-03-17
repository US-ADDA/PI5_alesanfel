package main.java.ejercicios.solution;

import java.util.List;
import java.util.Map;

import main.java.ejercicios.classes.Producto;
import main.java.ejercicios.data.DatosEjercicio3;
import us.lsi.common.List2;
import us.lsi.common.Pair;
import us.lsi.common.String2;
import us.lsi.gurobi.GurobiSolution;

public class SolucionEjercicio3 {
	
	private List<Pair<Producto, Double>> productos;
	private Double beneficio;
	
	public static SolucionEjercicio3 create(GurobiSolution gs) {
		return new SolucionEjercicio3(gs.objVal, gs.values);
	}	
	
	public static SolucionEjercicio3 create(List<Integer> value) {
		return new SolucionEjercicio3(value);
	}
	
	private SolucionEjercicio3(Double vo, Map<String, Double> vbles) {
		productos = List2.empty();
		beneficio = 0.;
		for (var data: vbles.entrySet()) {
			var value = data.getValue();
			if (value>0 && data.getKey().startsWith("x")) {
				var info_x = Integer.parseInt(data.getKey().split("_")[1]);
				var producto = DatosEjercicio3.getProducto(info_x);
				productos.add(Pair.of(producto, value));
				beneficio += producto.precio() * value;
			} 
		}
	}

	public SolucionEjercicio3(List<Integer> ls) {
		productos = List2.empty();
		beneficio = 0.;
		for (var i = 0; i < ls.size(); i++) {
			var value = ls.get(i);
			if (value>0) {
				var producto = DatosEjercicio3.getProducto(i);
				productos.add(Pair.of(producto, value*1.0));
				beneficio += producto.precio() * value;
			} 
		}
	}

	@Override
	public String toString() {
		String cadenaProductos = productos.stream()
				.map(pair -> pair.first().id() + ": " + Math.round(pair.second()) + " unidades")
				.reduce("", (ac, nx) -> String.format("%s%s\n", ac, nx));
		return String.format("Productos selecionados:\n%sBeneficio: %s",cadenaProductos, beneficio);
	}
	
	public static void print(GurobiSolution gs) {
		String2.toConsole("%s\n%s\n%s", String2.linea(), create(gs), String2.linea());
	}

	
}
