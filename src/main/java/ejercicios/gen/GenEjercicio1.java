package main.java.ejercicios.gen;

import main.java.ejercicios.data.DatosEjercicio1;
import main.java.ejercicios.solution.SolucionEjercicio1;
import main.java.ejercicios.solution.SolucionEjercicio5;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.List2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Resolución por genéticos del ejercicio 1, para ello se ha utilizado un cromosoma de rango, siendo el índice
 * de la lista un fichero y el valor en esa posición en la lista es la memoria que ocupará el fichero (si es mayor que
 * el tamaño de la lista de memorias significa que no va a ser almacenada en ninguna memoria).
 */
public class GenEjercicio1 implements ValuesInRangeData<Integer, SolucionEjercicio1> {

    private GenEjercicio1(String path) {
        DatosEjercicio1.initDatos(path);
    }

    /**
     * Crea una instancia del tipo {@link GenEjercicio1}.
     *
     * @param path la ruta del fichero.
     * @return una instancia del tipo {@link GenEjercicio1}.
     */
    public static GenEjercicio1 create(String path) {
        return new GenEjercicio1(path);
    }

    @Override
    public Integer size() {
        return DatosEjercicio1.getNumFichero();
    }

    @Override
    public ChromosomeType type() {
        return ChromosomeType.Range;
    }

    @Override
    public Double fitnessFunction(List<Integer> value) {

        double goal = 0, error = 0;
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < DatosEjercicio1.getNumFichero(); i++) {
            if (value.get(i) < DatosEjercicio1.getNumMemoria()) {
                // Maximizar la capacidad de los ficheros.
                goal++;
                // La capacidad del fichero no puede superar al tamaño máximo de la memoria.
                error += DatosEjercicio1.getCapacidadFichero(i) > DatosEjercicio1.getMaxTamanoMemoria(value.get(i)) ? 1 : 0;
                Integer key = value.get(i);
                if (map.containsKey(key))
                    map.put(key, map.get(key) - DatosEjercicio1.getCapacidadFichero(i));
                else
                    map.put(key, DatosEjercicio1.getCapacidadMemoria(key) - DatosEjercicio1.getCapacidadFichero(i));


            }
        }
        // Para cada memoria, no se puede exceder su capacidad.
        error += map.entrySet().stream().filter(entry -> entry.getValue() < 0).count();
        return error < 1 ? goal : -1000 * error;
    }

    @Override
    public SolucionEjercicio1 solucion(List<Integer> ls) {
        return SolucionEjercicio1.create(ls);

    }

    @Override
    public Integer max(Integer i) {
        return DatosEjercicio1.getNumMemoria() + 1;
    }

    @Override
    public Integer min(Integer i) {
        return 0;
    }
}