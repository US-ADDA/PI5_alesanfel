package test.java.tools;

import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.function.Consumer;

/**
 * Clase que permite testear los problemas programación lineal entera.
 */
public class TestPLE {

    private final String lsiPath, gurobiPath;
    private final Consumer<String> init;
    private final Consumer<GurobiSolution> consumerSolution;
    private final Class<?> classIdentifier;
    private final PrintStream consola;
    private PrintStream result;
    private Integer c;

    private TestPLE(String out_path, String lsi_path, String gurobi_path, Consumer<String> init, Consumer<GurobiSolution> cs, Class<?> classIdentifier) {
        this.lsiPath = lsi_path;
        this.gurobiPath = gurobi_path;
        this.init = init;
        this.classIdentifier = classIdentifier;
        this.consumerSolution = cs;
        this.c = 1;

        consola = System.out;
        try {
            result = new PrintStream(out_path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        result.append(String.format("%s\n\n", "Resultados de los test:"));
    }

    /**
     * Método de factoría de la clase {@link TestPLE}.
     *
     * @param outPath          ruta donde se almacenará la solución del ejercicio.
     * @param lsiPath          ruta donde se encuentra la solución del problema en formato lsi.
     * @param gurobiPath       ruta donde se va a almacenar la solución del problema en formato pli.
     * @param init             para poder cargar los datos.
     * @param consumerSolution función que a partir de una solución de gurobi genera lo que se va a mostrar.
     * @param classIdentifier  la clase donde se encuentran los métodos utilizados en el lsi.
     * @return un objeto que permite testear un determinado ejercicio.
     */
    public static TestPLE of(String outPath, String lsiPath, String gurobiPath, Consumer<String> init, Consumer<GurobiSolution> consumerSolution, Class<?> classIdentifier) {
        return new TestPLE(outPath, lsiPath, gurobiPath, init, consumerSolution, classIdentifier);
    }

    /**
     * Da los datos del problema con el fichero entero.
     *
     * @param data_path la ruta donde se encuentra los datos.
     */
    public void testFile(String... data_path) {
        for (String s : data_path) {
            init.accept(s);
            var new_gurobi_path = gurobiPath.replace(".lp", "-" + c + ".lp");
            c++;

            try {
                AuxGrammar.generate(classIdentifier, lsiPath, new_gurobi_path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            GurobiSolution gs = GurobiLp.gurobi(new_gurobi_path);
            System.setOut(result);
            consumerSolution.accept(gs);
            System.setOut(consola);
        }
    }
}