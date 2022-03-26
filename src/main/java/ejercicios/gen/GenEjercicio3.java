package main.java.ejercicios.gen;

import main.java.ejercicios.data.DatosEjercicio3;
import main.java.ejercicios.solution.SolucionEjercicio3;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

import java.util.List;

/**
 * Resolución pro genéticos del ejercicio 3, para ello se ha utilizado un cromosoma de tipo rango, siendo el índice
 * de la lista un producto y el valor en esa posición en la lista es el número de productos que se van a elaborar.
 */
public class GenEjercicio3 implements ValuesInRangeData<Integer, SolucionEjercicio3> {

    private GenEjercicio3(String path) {
        DatosEjercicio3.initDatos(path);
    }

    /**
     * Crea una instancia del tipo {@link GenEjercicio3}.
     *
     * @param path la ruta del fichero.
     * @return una instancia del tipo {@link GenEjercicio3}.
     */
    public static GenEjercicio3 create(String path) {
        return new GenEjercicio3(path);
    }

    @Override
    public Integer size() {
        return DatosEjercicio3.getNumProductos();
    }

    @Override
    public ChromosomeType type() {
        return ChromosomeType.Range;
    }

    @Override
    public Double fitnessFunction(List<Integer> value) {
        double goal = 0., error = 0.;
        int tiempoProduccion = 0, tiempoElaboracion = 0,
                maxTiempoProduccion = DatosEjercicio3.getMaxTiempoEnProduccion(),
                maxTiempoElaboracion = DatosEjercicio3.getMaxTiempoEnManual(),
                numComponentes = DatosEjercicio3.getNumComponentes();
        for (int i = 0; i < value.size(); i++) {
            if (value.get(i) > 0) {
                // Maximizar ingresos totales.
                goal += DatosEjercicio3.getIngresos(i) * value.get(i);
                for (int j = 0; j < numComponentes; j++) {
                    tiempoElaboracion += DatosEjercicio3.getTiempoComponenteDelProductoEnManual(i, j) * value.get(i);
                    tiempoProduccion += DatosEjercicio3.getTiempoComponenteDelProductoEnProduccion(i, j) * value.get(i);
                }
                // No se puede superar el tiempo total de producción.
                error += maxTiempoProduccion < tiempoProduccion ? 1 : 0;
                // No se puede superar el tiempo total de elaboración.
                error += maxTiempoElaboracion < tiempoElaboracion ? 1 : 0;
            }
        }
        return error < 1 ? goal : -1000 * error;
    }

    @Override
    public SolucionEjercicio3 solucion(List<Integer> value) {
        return SolucionEjercicio3.create(value);
    }

    @Override
    public Integer max(Integer i) {
        return DatosEjercicio3.getMaxUnidades(i);
    }

    @Override
    public Integer min(Integer i) {
        return 0;
    }


}
