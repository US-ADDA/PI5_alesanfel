package test.java.ejercicios;

import main.java.ejercicios.data.DatosEjercicio2;
import main.java.ejercicios.gen.GenEjercicio2;
import main.java.ejercicios.solution.SolucionEjercicio2;
import test.java.tools.TestGEN;
import test.java.tools.TestPLE;

public class TestEjercicio2 {
	
	private static String data_path = "data/PI5Ej2DatosEntrada",out_path_ple = "out/ple/ejercicio2.txt",
			lsi_path = "models/lsi/ejercicio2.lsi", gurobi_path = "models/gurobi/ejercicio2.lp",
			out_path_gen = "out/gen/ejercicio2.txt";
	
	private static Integer population_size = 500, num_generations = 500;
	public static void main(String[] args) {
		String[] data = {data_path +1+".txt",data_path+2+".txt",data_path+3+".txt"}; 
		
		TestPLE.of(out_path_ple,lsi_path,gurobi_path,
				DatosEjercicio2::initDatos,SolucionEjercicio2::print,
				DatosEjercicio2.class).testFile(data);
		//TestGEN.of(out_path_gen, population_size, num_generations, GenEjercicio2::create).testFile(data);
	}
}


