package main.java.ejercicios.gen;

import java.util.List;
import main.java.ejercicios.data.DatosEjercicio3;
import main.java.ejercicios.solution.SolucionEjercicio3;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class GenEjercicio3 implements ValuesInRangeData<Integer, SolucionEjercicio3> {

	public static GenEjercicio3 create(String path) {
		return new GenEjercicio3(path);	
	}
	
	public GenEjercicio3(String path) {
		DatosEjercicio3.initDatos(path);
	}
	
	@Override
	public Integer size() {
		return DatosEjercicio3.getNumProductos();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		double goal = 0, error = 0;
		Integer tiempoProduccion = 0, tiempoElaboracion = 0, 
				maxTiempoProduccion = DatosEjercicio3.getMaxTiempoEnProduccion(),
				maxTiempoElaboracion = DatosEjercicio3.getMaxTiempoEnElaboracion(),
				numProductos = DatosEjercicio3.getNumProductos(),
				numComponentes = DatosEjercicio3.getNumComponentes();
		for (var i = 0; i < numProductos; i++) {
			if (value.get(i) > 0 ) {
				// Maximizar ingresos totales.
				goal += DatosEjercicio3.getIngresos(i) * value.get(i);
				for (var j = 0; j< numComponentes; j++) {
					tiempoElaboracion += DatosEjercicio3.getTiempoComponenteDelProductoEnElaboracion(i, j) * value.get(i);
					tiempoProduccion +=  DatosEjercicio3.getTiempoComponenteDelProductoEnProduccion(i, j) * value.get(i);
				}
				// No se puede superar el tiempo total de producción.
				error += maxTiempoProduccion < tiempoProduccion ? 1: 0;
				// No se puede superar el tiempo taltal de elaboración.
				error += maxTiempoElaboracion < tiempoProduccion ? 1 : 0;
			}
			
		}
		System.out.println("goal: " + goal + "error: " +error);
		System.out.println(value);
		return error < 1 ? goal: -1000*error;
	}

	@Override
	public SolucionEjercicio3 solucion(List<Integer> value) {
		return SolucionEjercicio3.create(value);
	}

	@Override
	public Integer max(Integer i) {
		return DatosEjercicio3.getMaxUnidades(i);
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}
	
	

}
