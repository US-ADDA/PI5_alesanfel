package main.java.ejercicios.data;

import java.util.List;
import java.util.function.BiPredicate;

import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class DatosEjercicio5 {
	
	private static SimpleWeightedGraph<Ciudad, Carretera> GRAFO;
	private static BiPredicate<Carretera, Integer> PREDICADO_CARRETERA;
	private static BiPredicate<Ciudad, Integer> PREDICADO_CIUDAD;
	private static Integer DISTANCIA;
	private static Integer HABITANTES;
	private static Ciudad ORIGEN, DESTINO;
	private static String T_ORIGEN, T_DESTINO;
	
	public static void initDatos(String path) {
		GRAFO = GraphsReader.newGraph(path,
				ciudad -> {
					String nombre = ciudad[0];
					Integer habitantes = Integer.parseInt(ciudad[1].substring(0, ciudad[1].length()-2));
					return new Ciudad(nombre,habitantes);
				}, 
				Carretera::ofFormat, 
				Graphs2::simpleWeightedGraph);
		ORIGEN = GRAFO.vertexSet().stream().filter(ciudad -> ciudad.nombre().equals(T_ORIGEN)).findFirst().orElse(null);
		DESTINO = GRAFO.vertexSet().stream().filter(ciudad -> ciudad.nombre().equals(T_DESTINO)).findFirst().orElse(null);
	}
	
	// Métodos para ciudad.
	public static List<Ciudad> getCiudades() {
		return GRAFO.vertexSet().stream().toList();
	}
	
	public static void setHabitantes(Integer habitantes) {
		HABITANTES = habitantes;
	}
	
	public static Integer getHabitantes() {
		return HABITANTES;
	}
	
	public static void setPredicadoCiudad(BiPredicate<Ciudad, Integer> predicadoCiudad) {
		PREDICADO_CIUDAD = predicadoCiudad;
	}
	
	public static Boolean predicadoCiudad(Ciudad ciudad) {
		return PREDICADO_CIUDAD.test(ciudad, HABITANTES);
	}
	
	public static Ciudad getCiudad(Integer i) {
		return getCiudades().get(i);
	}
	
	public static void setOrigenYDestino(String origen, String destino) {
		T_ORIGEN = origen;
		T_DESTINO = destino;
	}
	
	public static Ciudad getOrigen() {
		return ORIGEN;
	}
	
	public static Ciudad getDestino( ) {
		return DESTINO;
	}
	
	public static Integer getNumCiudades() {
		return GRAFO.vertexSet().size();
	}
	// Métodos para carreteras.
	public static List<Carretera> getCarreteras() {
		return GRAFO.edgeSet().stream().toList();
	}
	
	public static void setDistancia(Integer distancia) {
		DISTANCIA = distancia;
	}
	
	public static Integer getDistancia() {
		return DISTANCIA;
	}
	
	public static void setPredicadoCarretera(BiPredicate<Carretera, Integer> predicadoCarretera) {
		PREDICADO_CARRETERA = predicadoCarretera;
	}
	
	public static Boolean predicadoCarretera(Carretera carretera) {
		return PREDICADO_CARRETERA.test(carretera, DISTANCIA);
	}
	
	public static Ciudad getCiudadInicio(Carretera carretera) {
		return GRAFO.getEdgeSource(carretera);
	}
	
	public static Ciudad getProximaCiudad(Carretera carretera) {
		return GRAFO.getEdgeTarget(carretera);
	}
	
	public static Carretera getCarretera(Integer j) {
		return getCarreteras().get(j);
	}
	
	public static Integer getNumCarretera() {
		return GRAFO.edgeSet().size();
	}
	
	
	// Otros métodos.
	public static SimpleWeightedGraph<Ciudad, Carretera> getGrafo() {
		return GRAFO;
	}
	
	public static void main(String[] args) {
		initDatos("data/PI5Ej5DatosEntrada1.txt");
		BiPredicate<Carretera, Integer> predicadoCarretera = (carretera, distancia) -> carretera.km() > distancia;
		BiPredicate<Ciudad, Integer> predicadoCiudad = (ciudad, habitantes) -> ciudad.habitantes() > habitantes;
		DatosEjercicio5.setPredicadoCarretera(predicadoCarretera);
		DatosEjercicio5.setPredicadoCiudad(predicadoCiudad);
		DatosEjercicio5.setHabitantes(100000);
		DatosEjercicio5.setDistancia(100);
		setOrigenYDestino("Cadiz", "Granada");
		System.out.println("Ciudades del grafo -> " + getCiudades());
		System.out.println("Mínimo número de habitantes -> " + getHabitantes());
		System.out.println("¿El predicado se cumple para la primera ciudad? -> " + DatosEjercicio5.predicadoCiudad(DatosEjercicio5.getCiudad(0)));
		System.out.println("Ciudad origen -> " + getOrigen());
		System.out.println("Ciudad destino -> " + getDestino());
		System.out.println("Número de ciudades del grafo -> " + getNumCiudades());
		System.out.println("Carreteras del grafo -> " + getCarreteras());
		System.out.println("Longitud mínima de la carretera -> " + getDistancia());
		System.out.println("¿El predicado se cumple para la primera carretera? -> " + DatosEjercicio5.predicadoCarretera(DatosEjercicio5.getCarretera(0)));
		System.out.println("Ciudad de inicio de la primera carretera -> " + getCiudadInicio(getCarretera(0)));
		System.out.println("Próxima ciudad en la primera carretera -> " + getProximaCiudad(getCarretera(0)));
		System.out.println("Número de carreteras -> " + getNumCarretera());
	}
}
