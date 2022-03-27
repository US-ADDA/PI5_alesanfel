package main.java.ejercicios.solution;

import main.java.ejercicios.data.DatosEjercicio5;
import org.jgrapht.graph.SimpleWeightedGraph;
import us.lsi.common.List2;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;

import java.util.List;

/**
 * Clase que permite mostrar correctamente las soluciones del ejercicio 5 por genéticos.
 */
public class SolucionEjercicio5 {

    private final List<Ciudad> ciudades;
    private Double distancia;

    private SolucionEjercicio5(List<Integer> ls) {
        SimpleWeightedGraph<Ciudad, Carretera> grafo = DatosEjercicio5.getGrafo();
        distancia = 0.;
        ciudades = List2.of(DatosEjercicio5.getOrigen());
        for (int i = 0; i < ls.size() - 1; i++) {
            Ciudad source = DatosEjercicio5.getCiudad(ls.get(i)),
                    target = DatosEjercicio5.getCiudad(ls.get(i + 1));
            if (grafo.containsEdge(source, target)) {
                var carretera = grafo.getEdge(source, target);
                distancia += carretera.km();
                ciudades.add(target);
            }
        }
    }

    /**
     * Crea una solución del ejercicio 5 para genéticos.
     *
     * @param ls la solución que se ha obtenido aplicando algoritmos genéticos.
     * @return la solución del ejercicio 1 por algoritmos genéticos.
     */
    public static SolucionEjercicio5 create(List<Integer> ls) {
        return new SolucionEjercicio5(ls);
    }

    @Override
    public String toString() {
        return String.format("Predicado:  %s hab. y %s kms:\n%s; Kms: %.1f",
                DatosEjercicio5.getHabitantes(), DatosEjercicio5.getDistancia(), ciudades, distancia);
    }
}

