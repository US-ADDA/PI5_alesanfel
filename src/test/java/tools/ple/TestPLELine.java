package test.java.tools.ple;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.function.Consumer;

import us.lsi.common.Files2;
import us.lsi.common.String2;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class TestPLELine {
	private String data_path,lsi_path,gurobi_path;
	private Consumer<GurobiSolution> cs;
	private Consumer<String> init;
	private Class<?> classIdentifier;
	
	private PrintStream consola, ps_res;
	
	
	public TestPLELine(String data_path, String out_path, String lsi_path, String gurobi_path, Consumer<String> init,Consumer<GurobiSolution> cs, Class<?> classIdentifier) {
		this.lsi_path = lsi_path;
		this.gurobi_path = gurobi_path;
		this.cs = cs;
		this.init = init;
		this.classIdentifier = classIdentifier;
		
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
			var ls = Files2.linesFromFile(data_path[i]);
			for (var data: ls) {
				init.accept(data);
				var new_gurobi_path = gurobi_path.replace(".lp", "-" + (i+1)+".lp");
				System.setOut(consola);
				try {
					AuxGrammar.generate(classIdentifier, lsi_path, new_gurobi_path);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.setOut(ps_res);
				GurobiSolution gs = GurobiLp.gurobi(new_gurobi_path);
				String2.toConsole("\nSolución PLE:%s", gs.toString((s,d) -> d>0).substring(2));
				cs.accept(gs);
				System.setOut(consola);
			}
		}
		
	}
}
