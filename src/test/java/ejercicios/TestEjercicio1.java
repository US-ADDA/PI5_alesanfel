package test.java.ejercicios;

import main.java.ejercicios.data.DatosEjercicio1;
import main.java.ejercicios.gen.GenEjercicio1;
import main.java.ejercicios.solution.SolucionEjercicio1;
import test.java.tools.TestGEN;
import test.java.tools.TestPLE;

public class TestEjercicio1 {

    // Ambos
    private static final String dataPath = "data/PI5Ej1DatosEntrada";

    // PLE
    private static final String outPathPle = "out/ple/ejercicio1.txt";
    private static final String lsiPath = "models/lsi/ejercicio1.lsi";
    private static final String gurobiPath = "models/gurobi/ejercicio1.lp";

    // Genéticos
    private static final String outPathGen = "out/gen/ejercicio1.txt";
    private static final Integer populationSize = 1000;
    private static final Integer numGenerations = 2000;

    public static void main(String[] args) {
        String[] data = {dataPath + 1 + ".txt", dataPath + 2 + ".txt", dataPath + 3 + ".txt"};

        TestPLE.of(outPathPle, lsiPath, gurobiPath,
                DatosEjercicio1::initDatos, SolucionEjercicio1::print,
                DatosEjercicio1.class).testFile(data);

        TestGEN.of(outPathGen, populationSize, numGenerations, GenEjercicio1::create).testFile(data);
    }
}
