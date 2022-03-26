package main.java.ejercicios.gen;

import main.java.ejercicios.data.DatosEjercicio5;
import main.java.ejercicios.solution.SolucionEjercicio5;
import org.jgrapht.graph.SimpleWeightedGraph;
import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;

import java.util.List;

/**
 * Resolución pro genéticos del ejercicio 5, para ello se ha utilizado un cromosoma de permutación,
 * siendo el índice el orden en el cual se van a ir pasando las ciudades y el valor en esa posición en la lista es
 * la ciudad que va a ser atravesada. Además, el tamaño de la lista, de un cromosoma a otro, puede variar
 * al no tener por qé contener todas las ciudades que disponemos, cosa que no sucede en los ejercicios anteriores.
 */
public class GenEjercicio5 implements SeqNormalData<SolucionEjercicio5> {

    private final SimpleWeightedGraph<Ciudad, Carretera> grafo;

    private GenEjercicio5(String path) {
        DatosEjercicio5.initDatos(path);
        grafo = DatosEjercicio5.getGrafo();
    }

    /**
     * Crea una instancia del tipo {@link GenEjercicio5}.
     *
     * @param path la ruta del fichero.
     * @return una instancia del tipo {@link GenEjercicio5}.
     */
    public static GenEjercicio5 create(String path) {
        return new GenEjercicio5(path);
    }

    @Override
    public ChromosomeType type() {
        return ChromosomeType.PermutationSubList;
    }

    @Override
    public Double fitnessFunction(List<Integer> value) {
        double goal = 0., error = 0.;
        int predicadoCarreteraCumplido = 0, predicadoCiudadCumplido = 0;
        Ciudad origen = DatosEjercicio5.getOrigen(), destino = DatosEjercicio5.getDestino();
        if (value.size() >= 3) {
            for (var i = 0; i < value.size() - 1; i++) {
                Ciudad source = DatosEjercicio5.getCiudad(value.get(i)), target = DatosEjercicio5.getCiudad(value.get(i + 1));
                if (grafo.containsEdge(source, target)) {
                    Carretera carretera = grafo.getEdge(source, target);
                    predicadoCarreteraCumplido += DatosEjercicio5.predicadoCarretera(carretera) ? 1 : 0;
                    goal += carretera.km();
                } else
                    error += 1;
                if (!source.equals(origen))
                    predicadoCiudadCumplido += DatosEjercicio5.predicadoCiudad(source) ? 1 : 0;
            }
            error += origen != DatosEjercicio5.getCiudad(value.get(0)) ? 1 : 0;
            error += destino != DatosEjercicio5.getCiudad(value.get(value.size() - 1)) ? 1 : 0;
        } else
            error += 1;
        error += predicadoCarreteraCumplido > 0 && predicadoCiudadCumplido > 0 ? 0 : 1;
        return error < 1 ? -goal : -1000 * error;
    }

    @Override
    public SolucionEjercicio5 solucion(List<Integer> value) {
        return SolucionEjercicio5.create(value);
    }

    @Override
    public Integer itemsNumber() {
        return DatosEjercicio5.getNumCiudades();
    }
}