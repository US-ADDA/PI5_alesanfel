package test.java.ejercicios;

import java.util.function.Function;

import main.java.ejercicios.data.DatosEjercicio1;
import main.java.ejercicios.gen.GenEjercicio1;
import main.java.ejercicios.solution.SolucionEjercicio1;
import test.java.tools.TestGEN;
import test.java.tools.TestPLE;
import us.lsi.ag.ChromosomeData;
import us.lsi.ag.ValuesInRangeData;

public class TestEjercicio1 {
	
	private static String data_path = "data/PI5Ej1DatosEntrada",out_path_ple = "out/ple/ejercicio1.txt",
			lsi_path = "models/lsi/ejercicio1.lsi", gurobi_path = "models/gurobi/ejercicio1.lp",
			out_path_gen = "out/gen/ejercicio1.txt";
	private static Integer population_size = 1000, num_generations = 1000;
	
	public static void main(String[] args) {
		String[] data = {data_path+2+".txt"/*,data_path+2+".txt",data_path+3+".txt"*/};

		
		TestPLE.of(out_path_ple,lsi_path,gurobi_path,
				DatosEjercicio1::initDatos,SolucionEjercicio1::print,
				DatosEjercicio1.class).testFile(data);
		
		TestGEN.of(out_path_gen, population_size, num_generations, GenEjercicio1::create).testFile(data);
	}
}
