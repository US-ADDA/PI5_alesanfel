package main.java.ejercicios.solution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.ejercicios.data.DatosEjercicio1;
import us.lsi.common.List2;
import us.lsi.common.String2;
import us.lsi.gurobi.GurobiSolution;

public class SolucionEjercicio1 {
	
	private Map<String, List<String>> memorias;
	private Integer num_ficheros;
	
	public static SolucionEjercicio1 create(GurobiSolution gs) {
		return new SolucionEjercicio1(gs.objVal, gs.values);
	}
	
	public static SolucionEjercicio1 create(List<Integer> ls) {
		return new SolucionEjercicio1(ls);
	}	
	
	private SolucionEjercicio1(Double vo, Map<String, Double> vbles) {
		num_ficheros = vo.intValue();
		memorias = new HashMap<String, List<String>>();
		for (var data: vbles.entrySet()) {
			if (data.getValue()>0 && data.getKey().startsWith("x")) {
				var info_x = data.getKey().split("_");
				var key = DatosEjercicio1.getMemoria(Integer.parseInt(info_x[2])).id();
				var value = DatosEjercicio1.getFichero(Integer.parseInt(info_x[1])).id();
				if (memorias.containsKey(key))
					memorias.get(key).add(value);
				else 
					memorias.put(key, List2.of(value));
			} 
		}
	}
	
	public SolucionEjercicio1(List<Integer> ls) {
		num_ficheros = 0;
		memorias = new HashMap<String, List<String>>();
		for (var i = 0; i < ls.size(); i++) {
			if (ls.get(i) < DatosEjercicio1.getNumMemoria()) {
				num_ficheros++;
				var key = DatosEjercicio1.getMemoria(ls.get(i)).id();;
				var value = DatosEjercicio1.getFichero(i).id();
				if (memorias.containsKey(key))
					memorias.get(key).add(value);
				else {
					memorias.put(key, List2.of(value));
				}
			}
		}
		
	}

	@Override
	public String toString() {
		String cadenaMemorias = memorias.entrySet().stream()
				.map(entry -> entry.getKey() + ": " + entry.getValue() )
				.reduce("", (ac, nx) -> String.format("%s%s\n", ac, nx));
		return String.format("Reparto obtenido:\n%sNúmero de archivos:%s",cadenaMemorias,num_ficheros);
	}
	
	public static void print(GurobiSolution gs) {
		String2.toConsole("%s\n%s\n%s", String2.linea(), create(gs), String2.linea());
	}



	
}
	

