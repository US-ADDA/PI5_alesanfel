package test.java.ejercicios;

import main.java.ejercicios.data.DatosEjercicio3;
import main.java.ejercicios.gen.GenEjercicio3;
import main.java.ejercicios.solution.SolucionEjercicio3;
import test.java.tools.TestGEN;
import test.java.tools.TestPLE;

public class TestEjercicio3 {

    // Ambos
    private static final String dataPath = "data/PI5Ej3DatosEntrada";

    // PLE
    private static final String outPathPle = "out/ple/ejercicio3.txt";
    private static final String lsiPath = "models/lsi/ejercicio3.lsi";
    private static final String gurobiPath = "models/gurobi/ejercicio3.lp";

    // Genéticos
    private static final String outPathGen = "out/gen/ejercicio3.txt";
    private static final Integer populationSize = 1000;
    private static final Integer numGenerations = 1000;

    public static void main(String[] args) {
        String[] data = {dataPath + 1 + ".txt", dataPath + 2 + ".txt", dataPath + 3 + ".txt"};

        TestPLE.of(outPathPle, lsiPath, gurobiPath,
                DatosEjercicio3::initDatos, SolucionEjercicio3::print,
                DatosEjercicio3.class).testFile(data);

        TestGEN.of(outPathGen, populationSize, numGenerations, GenEjercicio3::create).testFile(data);
    }
}
