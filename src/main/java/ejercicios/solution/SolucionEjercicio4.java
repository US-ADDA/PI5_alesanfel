package main.java.ejercicios.solution;

import main.java.ejercicios.classes.Contenedor;
import main.java.ejercicios.classes.Elemento;
import main.java.ejercicios.data.DatosEjercicio4;
import us.lsi.common.List2;
import us.lsi.common.Map2;
import us.lsi.common.String2;
import us.lsi.gurobi.GurobiSolution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que permite mostrar correctamente las soluciones del ejercicio 4, tanto para programación lineal entera,
 * como por genéticos.
 */
public class SolucionEjercicio4 {

    private final Map<Contenedor, List<Elemento>> elementosPorContenedor;

    private SolucionEjercicio4(Map<String, Double> vbles) {
        elementosPorContenedor = Map2.empty();
        for (var data : vbles.entrySet()) {
            if (data.getValue() > 0 && data.getKey().startsWith("x")) {
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

    private SolucionEjercicio4(List<Integer> ls) {
        elementosPorContenedor = new HashMap<>();
        for (var i = 0; i < ls.size(); i++) {
            if (ls.get(i) < DatosEjercicio4.getNumContenedores()) {
                var value = DatosEjercicio4.getElemento(i);
                var key = DatosEjercicio4.getContenedor(ls.get(i));
                if (elementosPorContenedor.containsKey(key))
                    elementosPorContenedor.get(key).add(value);
                else
                    elementosPorContenedor.put(key, List2.of(value));
            }
        }
    }

    /**
     * Crea una solución del ejercicio 4 para programación lineal entera.
     *
     * @param gs la solución que ha dado gurobi.
     * @return la solución del ejercicio 4 por programación lineal entera.
     */
    public static SolucionEjercicio4 create(GurobiSolution gs) {
        return new SolucionEjercicio4(gs.values);
    }

    /**
     * Crea una solución del ejercicio 4 para genéticos.
     *
     * @param ls la solución que se ha obtenido aplicando algoritmos genéticos.
     * @return la solución del ejercicio 4 por algoritmos genéticos.
     */
    public static SolucionEjercicio4 create(List<Integer> ls) {
        return new SolucionEjercicio4(ls);
    }

    /**
     * Muestra por pantalla la solución del ejercicio 4 por programación lineal entera.
     *
     * @param gs la solución que ha dado gurobi.
     */
    public static void print(GurobiSolution gs) {
        String2.toConsole("%s\n%s%s\n", String2.linea(), create(gs), String2.linea());
    }

    @Override
    public String toString() {
        var cadenaContenedores = elementosPorContenedor.entrySet().stream()
                .map(entry -> entry.getKey().id() + ": " + entry.getValue().stream().map(Elemento::id).toList())
                .reduce("", (ac, nx) -> String.format("%s%s\n", ac, nx));
        return String.format("Reparto obtenido:\n%s", cadenaContenedores);
    }


}
