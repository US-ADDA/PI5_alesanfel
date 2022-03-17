package main.java.ejercicios.gen;

import java.util.List;

import main.java.ejercicios.data.DatosEjercicio5;
import main.java.ejercicios.solution.SolucionEjercicio5;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.grafos.datos.Carretera;

public class GenEjercicio5 implements ValuesInRangeData<Integer, SolucionEjercicio5>{
	
	public static GenEjercicio5 create(String linea) {
		return new GenEjercicio5(linea);
	}
	
	private GenEjercicio5(String linea) {
		DatosEjercicio5.initDatos(linea);
	}

	@Override
	public Integer size() {
		return DatosEjercicio5.getCarreteras().size();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Binary;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		Double numeroAristas = 0.,aciertos = 0.;
		List<Carretera> carreteras = DatosEjercicio5.getCarreteras();
		for (var i = 0; i <value.size(); i++) {
			if (i!=value.size()-1) {
				var carretera = carreteras.get(i);
				var proximaCiudad = DatosEjercicio5.getProximaCiudad(carretera);
				numeroAristas ++;
				aciertos += DatosEjercicio5.esValido(proximaCiudad, carretera) ? 1:0;
			}
		}
		return aciertos >= 1 ? -aciertos - numeroAristas: -1000*numeroAristas;
	}

	@Override
	public SolucionEjercicio5 solucion(List<Integer> value) {
		
		return SolucionEjercicio5.create(value, DatosEjercicio5.getCarreteras());
	}

	@Override
	public Integer max(Integer i) {
		return 2;
	}

	@Override
	public Integer min(Integer i) {
		return 2;
	}
	
	
	
	
}
