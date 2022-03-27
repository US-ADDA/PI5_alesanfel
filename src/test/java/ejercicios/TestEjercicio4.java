package test.java.ejercicios;

import main.java.ejercicios.data.DatosEjercicio4;
import main.java.ejercicios.gen.GenEjercicio4;
import main.java.ejercicios.solution.SolucionEjercicio4;
import test.java.tools.TestGEN;
import test.java.tools.TestPLE;

public class TestEjercicio4 {

    // Ambos
    private static final String dataPath = "data/PI5Ej4DatosEntrada";

    // PLE
    private static final String outPathPle = "out/ple/ejercicio4.txt";
    private static final String lsiPath = "models/lsi/ejercicio4.lsi";
    private static final String gurobiPath = "models/gurobi/ejercicio4.lp";

    // Genéticos
    private static final String outPathGen = "out/gen/ejercicio4.txt";
    private static final Integer population_size = 1000;
    private static final Integer num_generations = 1000;

    public static void main(String[] args) {
        String[] data = {dataPath + 1 + ".txt", dataPath + 2 + ".txt", dataPath + 3 + ".txt"};

        TestPLE.of(outPathPle, lsiPath, gurobiPath,
                DatosEjercicio4::initDatos, SolucionEjercicio4::print,
                DatosEjercicio4.class).testFile(data);

        //TestGEN.of(outPathGen, population_size, num_generations, GenEjercicio4::create).testFile(data);
    }
}