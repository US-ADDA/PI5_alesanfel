package main.java.ejercicios.gen;

import main.java.ejercicios.data.DatosEjercicio4;
import main.java.ejercicios.solution.SolucionEjercicio4;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.Map2;

import java.util.List;
import java.util.Map;

/**
 * Resolución por genéticos del ejercicio 4, para ello se ha utilizado un cromosoma de tipo rango, siendo el índice
 * de la lista un elemento y el valor en esa posición en la lista es el contenedor en el que va a ser almacenado el
 * elemento (si es mayor que el tamaño de la lista de contenedores significa que no va a ser almacenada en
 * ningún contenedor).
 */
public class GenEjercicio4 implements ValuesInRangeData<Integer, SolucionEjercicio4> {

    private GenEjercicio4(String path) {
        DatosEjercicio4.initDatos(path);
    }

    /**
     * Crea una instancia del tipo {@link GenEjercicio4}.
     *
     * @param path la ruta del fichero.
     * @return una instancia del tipo {@link GenEjercicio4}.
     */
    public static GenEjercicio4 create(String path) {
        return new GenEjercicio4(path);
    }

    @Override
    public Integer size() {
        return DatosEjercicio4.getNumElementos();
    }

    @Override
    public ChromosomeType type() {
        return ChromosomeType.Range;
    }

    @Override
    public Double fitnessFunction(List<Integer> value) {
        double goal = 0, error = 0;
        Integer numContenedores = DatosEjercicio4.getNumContenedores();
        Map<Integer, Integer> consumo = Map2.empty();
        for (int i = 0; i < value.size(); i++) {
            if ((value.get(i) < numContenedores)) {
                // Para cada elemento y para cada contenedor, solo se puede ubicar en caso de que esté permitido acorde a sus tipos.
                if (DatosEjercicio4.esCompatible(i, value.get(i))) {
                    Integer key = value.get(i);
                    // Establecer una relación entre las variables x e y.
                    if (consumo.containsKey(key))
                        consumo.put(key, consumo.get(key) - DatosEjercicio4.getTamanoElemento(i));
                    else
                        consumo.put(key, DatosEjercicio4.getCapacidadContenedor(value.get(i)) - DatosEjercicio4.getTamanoElemento(i));
                } else
                    error += 1;
            }

        }
        error += consumo.entrySet().stream().filter(entry -> entry.getValue() < 0).count();
        goal += consumo.entrySet().stream().filter(entry -> entry.getValue() == 0).count();

        return error < 1 ? goal : -1000 * error;
    }

    @Override
    public SolucionEjercicio4 solucion(List<Integer> value) {
        return SolucionEjercicio4.create(value);
    }

    @Override
    public Integer max(Integer i) {
        return DatosEjercicio4.getNumContenedores() + 1;
    }

    @Override
    public Integer min(Integer i) {
        // TODO Auto-generated method stub
        return 0;
    }


}
