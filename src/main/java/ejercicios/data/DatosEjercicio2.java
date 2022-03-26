package main.java.ejercicios.data;

import main.java.ejercicios.classes.Candidato;
import us.lsi.common.Files2;
import us.lsi.common.List2;

import java.util.List;

/**
 * Los datos necesarios para resolver el ejercicio 2.
 */
public class DatosEjercicio2 {

    private static List<Candidato> candidatos;
    private static List<String> cualidades;
    private static Integer presupuestoMaximo;

    /**
     * Carga los datos de un fichero.
     *
     * @param path la ruta del fichero.
     */
    public static void initDatos(String path) {
        candidatos = List2.empty();
        cualidades = List2.empty();
        for (String linea : Files2.linesFromFile(path)) {
            if (linea.contains("Cualidades Deseadas: "))
                cualidades = List2.parse(linea.split(":")[1], ",", String::trim);
            else if (linea.contains("Presupuesto Máximo: "))
                presupuestoMaximo = Integer.parseInt(linea.split(":")[1].trim());
            else
                candidatos.add(Candidato.parse(linea));
        }
    }

    // <- MÉTODOS PARA CANDIDATOS -> //

    /**
     * Obtiene la valoración para un candidato.
     *
     * @param i el índice correspondiente al candidato en la lista {@code candidatos}.
     * @return la valoración de un candidato.
     */
    public static Integer getValoracion(Integer i) {
        return candidatos.get(i).valoracion();
    }

    /**
     * Obtiene el sueldo mínimo de un candidato.
     *
     * @param i el índice correspondiente al candidato en la lista {@code candidatos}.
     * @return el sueldo mínimo del candidato.
     */
    public static Double getSueldo(Integer i) {
        return candidatos.get(i).sueldo();
    }

    /**
     * Devuelve {@code true} si el primer candidato es incompatible con el segundo candidato, en caso contrario, devuelve {@code false}.
     *
     * @param i el índice correspondiente al primer candidato en la lista {@code candidatos}.
     * @param k el índice correspondiente al segundo candidato en la lista {@code candidatos}.
     * @return un {@link Boolean} indicando si son o no compatibles.
     */
    public static Boolean esIncompatible(Integer i, Integer k) {
        return candidatos.get(i).incompatibilidadesPorCandidato().contains(candidatos.get(k).id());
    }

    /**
     * Devuelve {@code 1} si el candidato tiene la cualidad indicada, en caso contrario, devuelve {@code 0}.
     *
     * @param i el índice correspondiente al candidato en la lista {@code candidatos}.
     * @param k el índice correspondiente a la cualidad en la lista {@code cualidades}.
     * @return {@code 1} si el candidato contiene la cualidad y sino {@code 0}.
     */
    public static Integer tieneCualidad(Integer i, Integer k) {
        return candidatos.get(i).cualidadesPorCandidato().contains(cualidades.get(k)) ? 1 : 0;
    }

    /**
     * Obtiene una instancia del tipo {@link Candidato}.
     *
     * @param i el índice correspondiente al candidato en la lista {@code candidatos}.
     * @return una instancia del tipo {@link Candidato}.
     */
    public static Candidato getCandidato(Integer i) {
        return candidatos.get(i);
    }

    /**
     * Obtiene el número de candidatos que disponemos.
     *
     * @return el número de memorias que disponemos.
     */
    public static Integer getNumCandidatos() {
        return candidatos.size();
    }


    // <- MÉTODOS PARA CUALIDADES -> //

    /**
     * Obtiene la lista de cualidades.
     *
     * @return la lista de cualidades.
     */
    public static List<String> getCualidades() {
        return cualidades;
    }

    /**
     * Obtiene el número de cualidades que desea la empresa que dispongan los candidatos seleccionados.
     *
     * @return el número de cualidades.
     */
    public static Integer getNumCualidades() {
        return cualidades.size();
    }

    // <- OTRO MÉTODO -> //

    /**
     * Obtiene el presupuesto máximo de la empresa.
     *
     * @return el presupuesto máximo.
     */
    public static Integer getPresupuesto() {
        return presupuestoMaximo;
    }
}
