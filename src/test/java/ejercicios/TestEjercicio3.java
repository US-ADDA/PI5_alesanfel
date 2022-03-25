package test.java.ejercicios;

import main.java.ejercicios.data.DatosEjercicio3;
import main.java.ejercicios.gen.GenEjercicio3;
import main.java.ejercicios.solution.SolucionEjercicio3;
import test.java.tools.TestGEN;
import test.java.tools.TestPLE;

public class TestEjercicio3 {

	private static String data_path = "data/PI5Ej3DatosEntrada",out_path_ple = "out/ple/ejercicio3.txt",
			lsi_path = "models/lsi/ejercicio3.lsi", gurobi_path = "models/gurobi/ejercicio3.lp",
			out_path_gen = "out/gen/ejercicio3.txt";
	
	private static Integer population_size = 1000, num_generations = 1000;
	
	public static void main(String[] args) {
		String[] data = {data_path+1+".txt", data_path+2+".txt", data_path+3+".txt"};
		
		TestPLE.of(out_path_ple,lsi_path,gurobi_path,
				DatosEjercicio3::initDatos,SolucionEjercicio3::print,
				DatosEjercicio3.class).testFile(data);
		
		TestGEN.of(out_path_gen, population_size, num_generations, GenEjercicio3::create).testFile(data);
	}
}
