package test.java.tools.ag;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.function.Function;

import us.lsi.ag.ChromosomeData;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.common.Files2;
import us.lsi.common.String2;

public class TestGENLine <E, S> {
	
	
	private int population_size, num_generations;
	private PrintStream consola;
	private PrintStream ps_res;
	private Function<String, ChromosomeData<E, S>> create;
	
	
	public TestGENLine(String out_path, Integer population_size, Integer num_generations, 
			Function<String, ChromosomeData<E, S>> create) {
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
	
	public void test(String ... data_path) {
		for (var i = 0; i < data_path.length; i++) {
			System.setOut(consola);
			AlgoritmoAG.POPULATION_SIZE = population_size;	
			StoppingConditionFactory.NUM_GENERATIONS = num_generations;
			var ls = Files2.linesFromFile(data_path[i]);
			for (var data: ls) {
				var alg = AlgoritmoAG.of(create.apply(data));
				alg.ejecuta();
				String2.toConsole("%s\n%s\n%s", String2.linea(), alg.bestSolution(), String2.linea());
			}
		}
	}
}
