package test.java.ejercicios;

import main.java.ejercicios.data.DatosEjercicio4;
import main.java.ejercicios.solution.SolucionEjercicio4;
import test.java.tools.ple.TestPLEFile;

public class TestEjercicio4 {

	private static String data_path = "data/PI5Ej4DatosEntrada",out_path_ple = "out/ple/ejercicio4.txt",
			lsi_path = "models/lsi/ejercicio4.lsi", gurobi_path = "models/gurobi/ejercicio4.lp",
			out_path_gen = "out/gen/ejercicio4.txt";
	private static Integer population_size = 5000, num_generations = 5000;
	
	public static void main(String[] args) {
		String[] data = {data_path+1+".txt", data_path+2+".txt", data_path+3+".txt"};
		
		TestPLEFile.of(out_path_ple,lsi_path,gurobi_path,
				DatosEjercicio4::initDatos,SolucionEjercicio4::print,
				DatosEjercicio4.class).test(data);
		// TestGENFile.of(out_path_gen, population_size, num_generations, GenEjercicio4::create).test();
		
	}
}