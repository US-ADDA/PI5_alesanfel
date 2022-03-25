package test.java.ejercicios;

import java.util.List;
import java.util.function.BiPredicate;

import main.java.ejercicios.data.DatosEjercicio5;
import main.java.ejercicios.gen.GenEjercicio5;
import main.java.ejercicios.solution.SolucionEjercicio5;
import test.java.tools.TestGEN;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;

public class TestEjercicio5 {
	
	private static String data_path = "data/PI5Ej5DatosEntrada",out_path = "out/gen/ejercicio5.txt";
	
	private static Integer population_size = 500, num_generations = 2000;
	
	public static void test(BiPredicate<Carretera, Integer> predicadoCarretera, BiPredicate<Ciudad, Integer> predicadoCiudad, String origen, String destino, Integer habitantes, Integer distancia, 
			String data_path, TestGEN<GenEjercicio5, List<Integer>, SolucionEjercicio5> test) {
		DatosEjercicio5.setPredicadoCarretera(predicadoCarretera);
		DatosEjercicio5.setPredicadoCiudad(predicadoCiudad);
		DatosEjercicio5.setOrigenYDestino(origen,destino);
		DatosEjercicio5.setHabitantes(habitantes);
		DatosEjercicio5.setDistancia(distancia);
		test.testFile(data_path);
	}
	
	public static void main(String[] args) {
		
		TestGEN<GenEjercicio5, List<Integer>, SolucionEjercicio5> test = TestGEN.of(out_path, population_size, num_generations, GenEjercicio5::create);

		BiPredicate<Carretera, Integer> predicadoCarretera1 = (carretera, distancia) -> carretera.km() > distancia;
		BiPredicate<Ciudad, Integer> predicadoCiudad1 = (ciudad, habitantes) -> ciudad.habitantes() > habitantes;
		test(predicadoCarretera1, predicadoCiudad1, "Cadiz", "Granada", 100000, 100, data_path + 1 + ".txt", test);
		
		BiPredicate<Carretera, Integer> predicadoCarretera2 = (carretera, distancia) -> carretera.km() > distancia;
		BiPredicate<Ciudad, Integer> predicadoCiudad2 = (ciudad, habitantes) -> ciudad.habitantes() < habitantes;
		test(predicadoCarretera2, predicadoCiudad2, "Toledo", "Guadalajara", 200000, 120, data_path + 2 + ".txt", test);
		
		BiPredicate<Carretera, Integer> predicadoCarretera3 = (carretera, distancia) -> carretera.km() < distancia;
		BiPredicate<Ciudad, Integer> predicadoCiudad3 = (ciudad, habitantes) -> ciudad.habitantes() > habitantes;
		test(predicadoCarretera3, predicadoCiudad3, "C01", "C25", 25000, 200, data_path + 3 + ".txt", test);
	}
}
