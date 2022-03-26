package main.java.ejercicios.data;

import org.jgrapht.graph.SimpleWeightedGraph;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

import java.util.List;
import java.util.function.BiPredicate;

/**
 * Los datos necesarios para resolver el ejercicio 5.
 */
public class DatosEjercicio5 {

    private static SimpleWeightedGraph<Ciudad, Carretera> grafo;
    private static BiPredicate<Carretera, Integer> predicadoCarretera;
    private static BiPredicate<Ciudad, Integer> predicadoCiudad;
    private static Integer distancia, habitantes;
    private static Ciudad origen, destino;
    private static String auxOrigen, auxDestino;
    private static List<Ciudad> ciudades;

    /**
     * Carga los datos de un fichero.
     *
     * @param path la ruta del fichero.
     */
    public static void initDatos(String path) {
        grafo = GraphsReader.newGraph(path,
                ciudad -> {
                    String nombre = ciudad[0];
                    Integer habitantes = Integer.parseInt(ciudad[1].substring(0, ciudad[1].length() - 2));
                    return new Ciudad(nombre, habitantes);
                },
                Carretera::ofFormat,
                Graphs2::simpleWeightedGraph);
        origen = grafo.vertexSet().stream().filter(ciudad -> ciudad.nombre().equals(auxOrigen)).findFirst().orElse(null);
        destino = grafo.vertexSet().stream().filter(ciudad -> ciudad.nombre().equals(auxDestino)).findFirst().orElse(null);
        ciudades = grafo.vertexSet().stream().toList();
    }

    // <- MÉTODOS PARA CIUDADES -> //

    /**
     * obtiene una instancia del tipo {@link Ciudad}.
     *
     * @param i el índice correspondiente al candidato en la lista {@code candidatos}.
     * @return the ciudad
     */
    public static Ciudad getCiudad(Integer i) {
        return ciudades.get(i);
    }

    /**
     * Permite indicar cuál va a ser el origen y el destino del camino.
     *
     * @param origen  la ciudad origen del trayecto.
     * @param destino la ciudad destino del trayecto.
     */
    public static void setOrigenYDestino(String origen, String destino) {
        auxOrigen = origen;
        auxDestino = destino;
    }

    /**
     * Obtiene la ciudad origen del trayecto.
     *
     * @return el origen.
     */
    public static Ciudad getOrigen() {
        return origen;
    }

    /**
     * Obtiene la ciudad destino del trayecto.
     *
     * @return el destino.
     */
    public static Ciudad getDestino() {
        return destino;
    }

    /**
     * Obtiene el número de ciudades que disponemos.
     *
     * @return el número de ciudades que disponemos.
     */
    public static Integer getNumCiudades() {
        return ciudades.size();
    }

    // <- MÉTODOS PARA EL PREDICADO DE LAS CIUDADES -> //

    /**
     * Obtiene el número de habitantes que se usará en los predicados de ciudades.
     *
     * @return el número de habitantes.
     */
    public static Integer getHabitantes() {
        return habitantes;
    }

    /**
     * Da un valor al número de habitantes que se usará en los predicados de ciudad.
     *
     * @param habitantes el número de habitantes
     */
    public static void setHabitantes(Integer habitantes) {
        DatosEjercicio5.habitantes = habitantes;
    }

    /**
     * Define el predicado que se utilizará para las ciudades.
     *
     * @param predicadoCiudad el predicado que se utilizará para las ciudades.
     */
    public static void setPredicadoCiudad(BiPredicate<Ciudad, Integer> predicadoCiudad) {
        DatosEjercicio5.predicadoCiudad = predicadoCiudad;
    }

    /**
     * Devuelve {@code true} si el predicado para una ciudad y unos habitantes se cumple, en caso contrario, devuelve {@code false}.
     *
     * @param ciudad la ciudad que queremos comprobar el predicado.
     * @return un {@link Boolean} indicando si se cumple el predicado o no.
     */
    public static Boolean predicadoCiudad(Ciudad ciudad) {
        return predicadoCiudad.test(ciudad, habitantes);
    }

    // <- MÉTODOS PARA EL PREDICADO DE LAS CARRETERAS -> //

    /**
     * Obtiene la distancia que se utilizará en el predicado de carretera.
     *
     * @return la distancia.
     */
    public static Integer getDistancia() {
        return distancia;
    }

    /**
     * Da un valor a la distancia que se usará en los predicados de ciudad.
     *
     * @param distancia la distancia.
     */
    public static void setDistancia(Integer distancia) {
        DatosEjercicio5.distancia = distancia;
    }

    /**
     * Define el predicado que se utilizará para las carreteras.
     *
     * @param predicadoCarretera el predicado de las carreteras.
     */
    public static void setPredicadoCarretera(BiPredicate<Carretera, Integer> predicadoCarretera) {
        DatosEjercicio5.predicadoCarretera = predicadoCarretera;
    }

    /**
     * Devuelve {@code true} si el predicado para una carretera y una distancia se cumple, en caso contrario, devuelve {@code false}..
     *
     * @param carretera la carretera que queremos comprobar el predicado.
     * @return un {@link Boolean} indicando si se cumple o no el predicado.
     */
    public static Boolean predicadoCarretera(Carretera carretera) {
        return predicadoCarretera.test(carretera, distancia);
    }

    // <- OTRO MÉTODO -> //

    /**
     * Obtiene el grafo con sus correspondientes vértices (ciudades) y aristas (carreteras).
     *
     * @return el grafo.
     */
    public static SimpleWeightedGraph<Ciudad, Carretera> getGrafo() {
        return grafo;
    }
}
