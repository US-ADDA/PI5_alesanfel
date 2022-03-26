package main.java.ejercicios.gen;

import main.java.ejercicios.data.DatosEjercicio2;
import main.java.ejercicios.solution.SolucionEjercicio2;
import us.lsi.ag.BinaryData;
import us.lsi.common.Set2;

import java.util.List;
import java.util.Set;

/**
 * Resolución por genéticos del ejercicio 2, para ello se ha utilizado un cromosoma binario, siendo el índice de la
 * lista un candidato y el valor en esa posición en la lista es si el candidato será contratado o no.
 */
public class GenEjercicio2 implements BinaryData<SolucionEjercicio2> {

    private GenEjercicio2(String path) {
        DatosEjercicio2.initDatos(path);
    }

    /**
     * Crea una instancia del tipo {@link GenEjercicio2}.
     *
     * @param path la ruta del fichero.
     * @return una instancia del tipo {@link GenEjercicio2}.
     */
    public static GenEjercicio2 create(String path) {
        return new GenEjercicio2(path);
    }

    @Override
    public Integer size() {
        return DatosEjercicio2.getNumCandidatos();
    }

    @Override
    public Double fitnessFunction(List<Integer> value) {
        double error = 0, goal = 0, gasto = 0;
        Set<String> cualidades = Set2.empty();
        for (int i = 0; i < DatosEjercicio2.getNumCandidatos(); i++) {
            if (value.get(i) == 1) {
                // Maximizar la valoración de los candidatos.
                goal += DatosEjercicio2.getValoracion(i);
                // Cualidades que utiliza cada candidato.
                cualidades.addAll(DatosEjercicio2.getCandidato(i).cualidadesPorCandidato());
                // No se pueden contratar "candidatos incompatibles.
                for (int k = i + 1; k < DatosEjercicio2.getNumCandidatos(); k++)
                    error += DatosEjercicio2.esIncompatible(i, k) && value.get(k) == 1 ? 1 : 0;
                // Calculamos el gasto.
                gasto += DatosEjercicio2.getSueldo(i);
            }
        }
        // Por cada cualidad, debe haber al menos un candidato seleccionado que presente dicha cualidad.
        error += cualidades.containsAll(DatosEjercicio2.getCualidades()) ? 0 : 1;
        error += gasto <= DatosEjercicio2.getPresupuesto() ? 0 : 1;
        return error < 1 ? goal : -1000 * error;
    }

    @Override
    public SolucionEjercicio2 solucion(List<Integer> value) {
        return SolucionEjercicio2.create(value);
    }

}
