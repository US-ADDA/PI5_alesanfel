package main.java.ejercicios.solution;

import java.util.List;
import org.jgrapht.graph.SimpleWeightedGraph;

import main.java.ejercicios.data.DatosEjercicio5;
import us.lsi.common.List2;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;

public class SolucionEjercicio5 {

	private SimpleWeightedGraph<Ciudad, Carretera> grafo;
	private List<Ciudad> ciudades;
	private Double distancia;
	
	public static SolucionEjercicio5 create(List<Integer> ls) {
		return new SolucionEjercicio5(ls);
	}
	public SolucionEjercicio5(List<Integer> ls) {
		grafo = DatosEjercicio5.getGrafo();
		distancia = 0.;
		ciudades = List2.of(DatosEjercicio5.getOrigen());
		for (var i = 0; i < ls.size()-1; i++) {
			Ciudad source = DatosEjercicio5.getCiudad(ls.get(i)), target = DatosEjercicio5.getCiudad(ls.get(i+1));
			if (grafo.containsEdge(source, target)) {
				var carretera = grafo.getEdge(source, target);
				distancia += carretera.km();
				ciudades.add(target);
			}
		}
	}
	
	@Override
	public String toString() {
		return String.format("Predicado: Ciudad con más de %s hab. y Carretera con más de %s kms:\n%s; Kms: %.1f", 
				DatosEjercicio5.getHabitantes(), DatosEjercicio5.getDistancia(), ciudades, distancia);
	}
}