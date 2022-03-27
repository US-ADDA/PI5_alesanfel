package main.java.ejercicios.solution;

import main.java.ejercicios.data.DatosEjercicio1;
import us.lsi.common.List2;
import us.lsi.common.Map2;
import us.lsi.common.String2;
import us.lsi.gurobi.GurobiSolution;

import java.util.List;
import java.util.Map;

/**
 * Clase que permite mostrar correctamente las soluciones del ejercicio 1, tanto para programación lineal entera,
 * como por genéticos.
 */
public class SolucionEjercicio1 {

    private final Map<String, List<String>> memorias;
    private Integer numFicheros;

    private SolucionEjercicio1(Double vo, Map<String, Double> vbles) {
        numFicheros = vo.intValue();
        memorias = Map2.empty();
        for (Map.Entry<String, Double> data : vbles.entrySet()) {
            if (data.getValue() > 0 && data.getKey().startsWith("x")) {
                String[] info_x = data.getKey().split("_");
                String key = DatosEjercicio1.getMemoria(Integer.parseInt(info_x[2])).id();
                String value = DatosEjercicio1.getFichero(Integer.parseInt(info_x[1])).id();
                if (memorias.containsKey(key))
                    memorias.get(key).add(value);
                else
                    memorias.put(key, List2.of(value));
            }
        }
    }

    private SolucionEjercicio1(List<Integer> ls) {
        numFicheros = 0;
        memorias = Map2.empty();
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i) < DatosEjercicio1.getNumMemoria()) {
                numFicheros++;
                String key = DatosEjercicio1.getMemoria(ls.get(i)).id();
                String value = DatosEjercicio1.getFichero(i).id();
                if (memorias.containsKey(key))
                    memorias.get(key).add(value);
                else
                    memorias.put(key, List2.of(value));
            }
        }

    }

    /**
     * Crea una solución del ejercicio 1 para programación lineal entera.
     *
     * @param gs la solución que ha dado gurobi.
     * @return la solución del ejercicio 1 por programación lineal entera.
     */
    public static SolucionEjercicio1 create(GurobiSolution gs) {
        return new SolucionEjercicio1(gs.objVal, gs.values);
    }

    /**
     * Crea una solución del ejercicio 1 para genéticos.
     *
     * @param ls la solución que se ha obtenido aplicando algoritmos genéticos.
     * @return la solución del ejercicio 1 por algoritmos genéticos.
     */
    public static SolucionEjercicio1 create(List<Integer> ls) {
        return new SolucionEjercicio1(ls);
    }

    /**
     * Muestra por pantalla la solución del ejercicio 1 por programación lineal entera.
     *
     * @param gs la solución que ha dado gurobi.
     */
    public static void print(GurobiSolution gs) {
        String2.toConsole("%s\n%s\n%s\n", String2.linea(), create(gs), String2.linea());
    }

    @Override
    public String toString() {
        String cadenaMemorias = memorias.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .reduce("", (ac, nx) -> String.format("%s%s\n", ac, nx));
        return String.format("Reparto obtenido:\n%sNúmero de archivos:%s", cadenaMemorias, numFicheros);
    }
}
	

