package main.java.ejercicios.solution;

import main.java.ejercicios.classes.Producto;
import main.java.ejercicios.data.DatosEjercicio3;
import us.lsi.common.List2;
import us.lsi.common.Pair;
import us.lsi.common.String2;
import us.lsi.gurobi.GurobiSolution;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Clase que permite mostrar correctamente las soluciones del ejercicio 3, tanto para programación lineal entera,
 * como por genéticos.
 */
public class SolucionEjercicio3 {

    private final List<Pair<Producto, Double>> productos;
    private Double beneficio;

    private SolucionEjercicio3(Map<String, Double> vbles) {
        productos = List2.empty();
        beneficio = 0.;
        for (Entry<String, Double> data : vbles.entrySet()) {
            var value = data.getValue();
            if (value > 0 && data.getKey().startsWith("x")) {
                var info_x = Integer.parseInt(data.getKey().split("_")[1]);
                var producto = DatosEjercicio3.getProducto(info_x);
                productos.add(Pair.of(producto, value));
                beneficio += producto.precio() * value;
            }
        }
    }

    private SolucionEjercicio3(List<Integer> ls) {
        productos = List2.empty();
        beneficio = 0.;
        for (int i = 0; i < ls.size(); i++) {
            int value = ls.get(i);
            if (value > 0) {
                Producto producto = DatosEjercicio3.getProducto(i);
                productos.add(Pair.of(producto, value * 1.0));
                beneficio += producto.precio() * value;
            }
        }
    }

    /**
     * Crea una solución del ejercicio 3 para programación lineal entera.
     *
     * @param gs la solución que ha dado gurobi.
     * @return la solución del ejercicio 3 por programación lineal entera.
     */
    public static SolucionEjercicio3 create(GurobiSolution gs) {
        return new SolucionEjercicio3(gs.values);
    }

    /**
     * Crea una solución del ejercicio 3 para genéticos.
     *
     * @param ls la solución que se ha obtenido aplicando algoritmos genéticos.
     * @return la solución del ejercicio 3 por algoritmos genéticos.
     */
    public static SolucionEjercicio3 create(List<Integer> ls) {
        return new SolucionEjercicio3(ls);
    }

    /**
     * Muestra por pantalla la solución del ejercicio 3 por programación lineal entera.
     *
     * @param gs la solución que ha dado gurobi.
     */
    public static void print(GurobiSolution gs) {
        String2.toConsole("%s\n%s\n%s\n", String2.linea(), create(gs), String2.linea());
    }

    @Override
    public String toString() {
        String cadenaProductos = productos.stream()
                .map(pair -> pair.first().id() + ": " + Math.round(pair.second()) + " unidades")
                .reduce("", (ac, nx) -> String.format("%s%s\n", ac, nx));
        return String.format("Productos seleccionados:\n%sBeneficio: %s", cadenaProductos, beneficio);
    }
}
