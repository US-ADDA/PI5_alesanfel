package test.java.ejercicios;

import java.util.function.Predicate;

import main.java.ejercicios.data.DatosEjercicio5;
import main.java.ejercicios.gen.GenEjercicio5;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;

public class TestEjercicio5 {
	
	public static void Resolucion(Integer i, Predicate<Carretera> predicadoCarretera, Predicate<Ciudad> predicadoCiudad) {
		GenEjercicio5 problema = GenEjercicio5.create("hola");
		AlgoritmoAG.ELITISM_RATE = 0.3;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 50;
		
		StoppingConditionFactory.NUM_GENERATIONS = 10;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN=1;
		
		DatosEjercicio5.setPredicates(predicadoCarretera, predicadoCiudad);
		var algoritmo = AlgoritmoAG.of(problema);
		algoritmo.ejecuta();
		System.out.println(algoritmo.getBestChromosome().decode());
		var solucion = problema.solucion(algoritmo.getBestChromosome().decode());
		System.out.println(solucion.formatoEntendible());
	}
}
