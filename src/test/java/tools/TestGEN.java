package test.java.tools;

import us.lsi.ag.ChromosomeData;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.common.String2;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.function.Function;

/**
 * Clase que permite testear los problemas de algoritmos genéticos.
 *
 * @param <R> clase del cromosoma usado.
 * @param <E> clase del elemento que contiene dentro de la lista.
 * @param <S> clase para la solución del ejercicio.
 */
public class TestGEN<R extends ChromosomeData<E, S>, E, S> {
    private final int populationSize, numGenerations;
    private final PrintStream consola;
    private final Function<String, R> create;
    private PrintStream result;

    private TestGEN(String outPath, Integer populationSize, Integer numGenerations,
                    Function<String, R> create) {
        this.populationSize = populationSize;
        this.numGenerations = numGenerations;
        this.create = create;

        consola = System.out;
        try {
            result = new PrintStream(outPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        result.append(String.format("%s\n%s\n", "Resultados de los test de los test ejemplo:", String2.linea()));
    }

    /**
     * Método de factoría de la clase {@link TestGEN}.
     *
     * @param <R>            clase del cromosoma usado.
     * @param <E>            clase del elemento que contiene dentro de la lista.
     * @param <S>            clase para la solución del ejercicio.
     * @param outPath        ruta donde se almacenará la solución del ejercicio.
     * @param populationSize el tamaño de la población.
     * @param numGenerations el número de generaciones.
     * @param create         función qu, a partir del cromosoma, obtiene la información que se tiene que mostrar.
     * @return un objeto que permite testear un determinado ejercicio.
     */
    public static <R extends ChromosomeData<E, S>, E, S> TestGEN<R, E, S> of(String outPath, Integer populationSize, Integer numGenerations,
                                                                             Function<String, R> create) {
        return new TestGEN<>(outPath, populationSize, numGenerations, create);
    }

    /**
     * Da los datos del problema con el fichero entero.
     *
     * @param dataPath la ruta donde se encuentra los datos.
     */
    public void testFile(String... dataPath) {
        for (String line : dataPath) {
            AlgoritmoAG.POPULATION_SIZE = populationSize;
            StoppingConditionFactory.NUM_GENERATIONS = numGenerations;
            AlgoritmoAG<E, S> alg = AlgoritmoAG.of(create.apply(line));
            alg.ejecuta();
            System.setOut(result);
            String2.toConsole("%s\n%s\n%s", String2.linea(), alg.bestSolution(), String2.linea());
            System.setOut(consola);
        }
    }
}
