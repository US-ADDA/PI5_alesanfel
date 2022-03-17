package main.java.ejercicios.solution;

import java.util.List;
import java.util.Map;

import main.java.ejercicios.classes.Candidato;
import main.java.ejercicios.data.DatosEjercicio2;
import us.lsi.common.List2;
import us.lsi.common.String2;
import us.lsi.gurobi.GurobiSolution;

public class SolucionEjercicio2 {
	
	private List<Candidato> candidatos;
	private Double valoracionMedia, valoracionTotal, gasto;
	
	public static SolucionEjercicio2 create(GurobiSolution gs) {
		return new SolucionEjercicio2(gs.objVal, gs.values);
	}	
	
	public static SolucionEjercicio2 create(List<Integer> value) {
		return new SolucionEjercicio2(value);
	}
	


	private SolucionEjercicio2(Double vo, Map<String, Double> vbles) {
		candidatos = List2.empty();
		valoracionMedia = 0.;
		valoracionTotal = 0.;
		gasto = 0.;
		for (var data: vbles.entrySet()) {
			if (data.getValue()>0 && data.getKey().startsWith("x")) {
				var info_x = Integer.parseInt(data.getKey().split("_")[1]);
				var candidato = DatosEjercicio2.getCandidato(info_x);
				candidatos.add(candidato);
				valoracionTotal += candidato.valoracion();
				gasto += candidato.sueldo();
			} 
		}
		valoracionMedia = valoracionTotal/candidatos.size();
	}
	
	private SolucionEjercicio2(List<Integer> value) {
		candidatos = List2.empty();
		valoracionMedia = 0.;
		valoracionTotal = 0.;
		gasto = 0.;
		for (var i = 0; i < DatosEjercicio2.getNumCandidatos(); i++) {
			if (value.get(i) == 1) {
				var candidato = DatosEjercicio2.getCandidato(i);
				candidatos.add(candidato);
				valoracionTotal += candidato.valoracion();
				gasto += candidato.sueldo();
			}
		}
		valoracionMedia = valoracionTotal/candidatos.size();
	}

	@Override
	public String toString() {
		String cadenaCandidatos = candidatos.stream().map(Candidato::toString).reduce("", (ac, nx) -> ac+nx+"\n");
		return "Candidatos Seleccionados:\n" + cadenaCandidatos + "Valoracion total: " + valoracionTotal +  " Valoración media: " + valoracionMedia + "; Gasto: " + gasto;
	}
	
	public static void print(GurobiSolution gs) {
		String2.toConsole("%s\n%s\n%s", String2.linea(), create(gs), String2.linea());
	}



	
}
