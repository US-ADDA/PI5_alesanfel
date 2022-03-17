package main.java.ejercicios.data;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class DatosEjercicio5 {
	
	private static SimpleWeightedGraph<Ciudad, Carretera> GRAFO;
	private static Predicate<Carretera> PREDICADO_CARRETERA;
	private static Predicate<Ciudad> PREDICADO_CIUDAD;
	
	public static void initDatos(String path) {
		GRAFO = GraphsReader.newGraph(path,
				Ciudad::ofFormat, 
				Carretera::ofFormat, 
				Graphs2::simpleWeightedGraph);
	}
	
	public static SimpleWeightedGraph<Ciudad, Carretera> getGrafo() {
		return GRAFO;
	}
	
	public static List<Carretera> getCarreteras() {
		return GRAFO.edgeSet().stream().toList();
	}
	
	public static Set<Ciudad> getCiudades() {
		return GRAFO.vertexSet();
	}
	
	public static boolean esValido(Ciudad ciudad, Carretera carretera) {
		return PREDICADO_CARRETERA.test(carretera) && PREDICADO_CIUDAD.test(ciudad);
	}
	
	public static Ciudad getProximaCiudad(Carretera carretera) {
		return GRAFO.getEdgeTarget(carretera);
	}
	
	public static void setPredicates(Predicate<Carretera> predicadoCarretera, Predicate<Ciudad> predicadoCiudad) {
		PREDICADO_CARRETERA = predicadoCarretera;
		PREDICADO_CIUDAD = predicadoCiudad;
	}
}
