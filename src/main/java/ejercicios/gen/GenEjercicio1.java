package main.java.ejercicios.gen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.ejercicios.data.DatosEjercicio1;
import main.java.ejercicios.solution.SolucionEjercicio1;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.List2;

public class GenEjercicio1 implements ValuesInRangeData<Integer, SolucionEjercicio1> {

	public static GenEjercicio1 create(String path) {
		return new GenEjercicio1(path);
	}
	
	private GenEjercicio1(String path) {
		DatosEjercicio1.initDatos(path);
	}
	
	@Override
	public Integer size() {
		return DatosEjercicio1.getNumFichero();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> ls) {
		double goal = 0, error = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (var i = 0; i<DatosEjercicio1.getNumFichero(); i++) {
			if (ls.get(i) < DatosEjercicio1.getNumMemoria()) {
				// Maximizar la capacidad de los ficheros.
				goal++;
				// La capacidad del fichero no puede superar al tamaño máximo de la memoria.
				error += DatosEjercicio1.getCapacidadFichero(i) > DatosEjercicio1.getMaxTamanoMemoria(ls.get(i)) ? 1: 0;
				var key = ls.get(i);
				if (map.containsKey(key)) 
					map.put(key, map.get(key)-DatosEjercicio1.getCapacidadFichero(i));
				else 
					map.put(key, DatosEjercicio1.getCapacidadMemoria(key) - DatosEjercicio1.getCapacidadFichero(i));
				
			}
		}
		// La capacidad de los ficheros de una memoria no pueden superar a la de la memoria ni ser menor.
		System.out.println(map);
		error += map.entrySet().stream().mapToInt(entry -> Math.abs(entry.getValue())).sum();
		if (error == 0) {
			System.out.println(ls);
		}
		return error<1? goal: -1000*error;
	}

	@Override
	public SolucionEjercicio1 solucion(List<Integer> ls) {
		return SolucionEjercicio1.create(ls);

	}

	@Override
	public Integer max(Integer i) {
		return DatosEjercicio1.getNumMemoria()+1;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}
}