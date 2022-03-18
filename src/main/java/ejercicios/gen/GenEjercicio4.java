package main.java.ejercicios.gen;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import main.java.ejercicios.data.DatosEjercicio4;
import main.java.ejercicios.solution.SolucionEjercicio4;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.Map2;

public class GenEjercicio4 implements ValuesInRangeData<Integer, SolucionEjercicio4>{

	public static GenEjercicio4 create(String path) {
		return new GenEjercicio4(path);
	}
	
	private GenEjercicio4(String path) {
		DatosEjercicio4.initDatos(path);
	}
	
	@Override
	public Integer size() {
		return DatosEjercicio4.getNumElementos();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		double goal = 0, error = 0;
		Integer numContenedores = DatosEjercicio4.getNumContenedores();
		// Para cada elemento y para cada contenedor, sólo se puede ubicar en case de que esté permitido acorde a sus tipos.
		// Establecer una relación entre las variables x e y.
		Map<Integer, Integer> consumo = Map2.empty();
		for (var i = 0; i < value.size(); i++) {
			if ((value.get(i) < numContenedores) && DatosEjercicio4.esCompatible(i, value.get(i))) {
				var key = value.get(i);
				if (consumo.containsKey(key))
					consumo.put(key, consumo.get(key) - DatosEjercicio4.getTamanoElemento(i));
				else
					consumo.put(key, DatosEjercicio4.getCapacidadContenedor(value.get(i)) - DatosEjercicio4.getTamanoElemento(i));
			}
		}
		error += consumo.entrySet().stream().filter(entry -> entry.getValue() > 0).count()*2;
		error += consumo.entrySet().stream().filter(entry -> entry.getValue() < 0).count();
		goal += consumo.entrySet().stream().filter(entry -> entry.getValue() == 0).count();
		return error > 1 ? goal: -1000*error;
	}

	@Override
	public SolucionEjercicio4 solucion(List<Integer> value) {
		return SolucionEjercicio4.create(value);
	}

	@Override
	public Integer max(Integer i) {
		return DatosEjercicio4.getNumContenedores()+1;
	}

	@Override
	public Integer min(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
