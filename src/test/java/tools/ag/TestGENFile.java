package test.java.tools.ag;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.function.Function;

import us.lsi.ag.ChromosomeData;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.common.String2;

public class TestGENFile <R extends ChromosomeData<E, S>, E, S> {
	private int population_size, num_generations;
	private PrintStream consola;
	private PrintStream ps_res;
	private Function<String, R> create;
	
	
	public TestGENFile(String out_path, Integer population_size, Integer num_generations, 
			Function<String, R> create) {
		this.population_size = population_size;
		this.num_generations = num_generations;
		this.create = create;
	
		consola = System.out;
		try {
			ps_res = new PrintStream(new File(out_path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ps_res.append(String.format("%s\n%s\n", "Resultados de los test de los test ejemplo:", String2.linea()));
	}
	
	public static <R extends ChromosomeData<E, S>, E, S> TestGENFile of(String out_path, Integer population_size, Integer num_generations, 
			Function<String, R> create) {
		return new TestGENFile(out_path, population_size, num_generations, create);
	}
	
	public void test(String ... data_path) {
		for (var i = 0; i < data_path.length; i++) {
			System.setOut(consola);
			AlgoritmoAG.POPULATION_SIZE = population_size;	
			StoppingConditionFactory.NUM_GENERATIONS = num_generations;
			var alg = AlgoritmoAG.of(create.apply(data_path[i]));
			alg.ejecuta();
			System.setOut(ps_res);
			String2.toConsole("%s\n%s\n%s", String2.linea(), alg.bestSolution(), String2.linea());
			System.setOut(consola);
		}
	}
}
