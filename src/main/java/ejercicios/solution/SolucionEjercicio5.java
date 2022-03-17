package main.java.ejercicios.solution;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;

import main.java.ejercicios.data.DatosEjercicio5;
import us.lsi.common.List2;
import us.lsi.common.Set2;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.views.SubGraphView;

public class SolucionEjercicio5 {
	
	private SubGraphView<Ciudad, Carretera, Graph<Ciudad,Carretera>> subgrafo;
	
	public static SolucionEjercicio5 create(List<Integer> value, List<Carretera> carreteras) {
		return new SolucionEjercicio5(value, carreteras);
	}
	
	public SolucionEjercicio5(List<Integer> value, List<Carretera> carreteras) {
		Set<Carretera> edges = Set2.empty();
		for (var i = 0; i < value.size(); i++) {
			if (value.get(i) == 1) {
				edges.add(carreteras.get(i));
			}
		}
		var grafo = DatosEjercicio5.getGrafo();
		subgrafo = SubGraphView.of(grafo, v -> grafo.vertexSet().contains(v), e -> carreteras.contains(e));
	}
	
	private Double getKms() {
		return subgrafo.edgeSet().stream().map(x->x.km()).reduce(0., (ac, x) -> ac +x);
	}
	
	private List<Carretera> getCarreteras() {
		return subgrafo.edgeSet().stream().toList();
	}
	
	private Set<Ciudad> getCiudades() {
		return subgrafo.vertexSet();
	}
	
	private List<Ciudad> imprimeCamino() {
		List<Ciudad> ciudadesCamino = List2.empty();
		Set<Ciudad> ciudades = getCiudades();
		List<Carretera> carreteras = getCarreteras();
		Set<Ciudad> visitadas = new HashSet<Ciudad>(ciudadesCamino);
		Integer i = 0;
		while(ciudadesCamino.size()<ciudades.size()+1 && !visitadas.containsAll(ciudades)) {
			var salida = subgrafo.getEdgeSource(carreteras.get(i));
			var destino = subgrafo.getEdgeTarget(carreteras.get(i));
			if (ciudadesCamino.isEmpty()) {
				ciudadesCamino.add(salida);
				ciudadesCamino.add(destino);
			}
			else if (salida.equals(ciudadesCamino.get(ciudadesCamino.size()-1)))
				ciudadesCamino.add(destino);
			else if (salida.equals(ciudadesCamino.get(ciudadesCamino.size()-1)))
				ciudadesCamino.add(salida);
			i = (i+1)%carreteras.size();
			visitadas.addAll(ciudadesCamino);
		}
		return ciudadesCamino;	
	}
	
	public String formatoEntendible() {
		var formatoEntendible = "Camino propuesto: " + imprimeCamino() + "\n";
		formatoEntendible += "Coste total: " + getKms() + "kms \n";
		return formatoEntendible;
	}

}
