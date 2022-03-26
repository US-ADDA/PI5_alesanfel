package test.java.ejercicios;

import main.java.ejercicios.data.DatosEjercicio2;
import main.java.ejercicios.gen.GenEjercicio2;
import main.java.ejercicios.solution.SolucionEjercicio2;
import test.java.tools.TestGEN;
import test.java.tools.TestPLE;

public class TestEjercicio2 {

    // Ambos
    private static final String dataPath = "data/PI5Ej2DatosEntrada";

    // PLE
    private static final String outPathPle = "out/ple/ejercicio2.txt";
    private static final String lsiPath = "models/lsi/ejercicio2.lsi";
    private static final String gurobiPath = "models/gurobi/ejercicio2.lp";

    // Genéticos
    private static final String outPathGen = "out/gen/ejercicio2.txt";
    private static final Integer populationSize = 1000;
    private static final Integer numGenerations = 1000;

    public static void main(String[] args) {
        String[] data = {dataPath + 1 + ".txt", dataPath + 2 + ".txt", dataPath + 3 + ".txt"};

        TestPLE.of(outPathPle, lsiPath, gurobiPath,
                DatosEjercicio2::initDatos, SolucionEjercicio2::print,
                DatosEjercicio2.class).testFile(data);

        TestGEN.of(outPathGen, populationSize, numGenerations, GenEjercicio2::create).testFile(data);
    }
}


