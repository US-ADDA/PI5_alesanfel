package main.java.ejercicios.solution;

import main.java.ejercicios.classes.Candidato;
import main.java.ejercicios.data.DatosEjercicio2;
import us.lsi.common.List2;
import us.lsi.common.String2;
import us.lsi.gurobi.GurobiSolution;

import java.util.List;
import java.util.Map;

/**
 * Clase que permite mostrar correctamente las soluciones del ejercicio 2, tanto para programación lineal entera,
 * como por genéticos.
 */
public class SolucionEjercicio2 {

    private final List<Candidato> candidatos;
    private Double valoracionMedia, valoracionTotal, gasto;

    private SolucionEjercicio2(Map<String, Double> vbles) {
        candidatos = List2.empty();
        valoracionMedia = 0.;
        valoracionTotal = 0.;
        gasto = 0.;
        for (var data : vbles.entrySet()) {
            if (data.getValue() > 0 && data.getKey().startsWith("x")) {
                var info_x = Integer.parseInt(data.getKey().split("_")[1]);
                var candidato = DatosEjercicio2.getCandidato(info_x);
                candidatos.add(candidato);
                valoracionTotal += candidato.valoracion();
                gasto += candidato.sueldo();
            }
        }
        valoracionMedia = valoracionTotal / candidatos.size();
    }

    private SolucionEjercicio2(List<Integer> ls) {
        candidatos = List2.empty();
        valoracionMedia = 0.;
        valoracionTotal = 0.;
        gasto = 0.;
        for (var i = 0; i < ls.size(); i++) {
            if (ls.get(i) == 1) {
                var candidato = DatosEjercicio2.getCandidato(i);
                candidatos.add(candidato);
                valoracionTotal += candidato.valoracion();
                gasto += candidato.sueldo();
            }
        }
        valoracionMedia = valoracionTotal / candidatos.size();
    }

    /**
     * Crea una solución del ejercicio 2 para programación lineal entera.
     *
     * @param gs la solución que ha dado gurobi.
     * @return ls solución del ejercicio 2 por programación lineal entera.
     */
    public static SolucionEjercicio2 create(GurobiSolution gs) {
        return new SolucionEjercicio2(gs.values);
    }

    /**
     * Crea una solución del ejercicio 2 para genéticos.
     *
     * @param ls la solución que se ha obtenido aplicando algoritmos genéticos.
     * @return la solución del ejercicio 2 por algoritmos genéticos.
     */
    public static SolucionEjercicio2 create(List<Integer> ls) {
        return new SolucionEjercicio2(ls);
    }

    /**
     * Muestra por pantalla la solución del ejercicio 2 por programación lineal entera.
     *
     * @param gs la solución que ha dado gurobi.
     */
    public static void print(GurobiSolution gs) {
        String2.toConsole("%s\n%s\n%s\n", String2.linea(), create(gs), String2.linea());
    }

    @Override
    public String toString() {
        String cadenaCandidatos = candidatos.stream().map(Candidato::toString).reduce("", (ac, nx) -> String.format("%s%s\n", ac, nx));
        return String.format("Candidatos Seleccionados:\n%sValoracion total: %.1f; Gasto: %.1f; V. media: %.1f", cadenaCandidatos, valoracionTotal, gasto, valoracionMedia);
    }


}
