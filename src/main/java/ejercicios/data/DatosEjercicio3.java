package main.java.ejercicios.data;

import java.util.ArrayList;
import java.util.List;

import main.java.ejercicios.classes.Componente;
import main.java.ejercicios.classes.Producto;
import us.lsi.common.Files2;

public class DatosEjercicio3 {

	private static List<Componente> COMPONENTES;
	private static List<Producto> PRODUCTOS;
	private static Integer TOTAL_PRODUCCION;
	private static Integer TOTAL_MANUAL;
	
	public static void initDatos(String path) {
		COMPONENTES = new ArrayList<>();;
		PRODUCTOS = new ArrayList<>();
		for (var linea: Files2.linesFromFile(path)) { 
			if (linea.contains("T_prod =") && !linea.contains("//")) 
				TOTAL_PRODUCCION = Integer.parseInt(linea.split("=")[1].trim());
			else if (linea.contains("T_manual =") && !linea.contains("//")) 
				TOTAL_MANUAL = Integer.parseInt(linea.split("=")[1].trim());
			else if (linea.contains("C") && !linea.contains("->") && !linea.contains("//")) 
				COMPONENTES.add(Componente.parse(linea));
		    else if (linea.contains("P") && !linea.contains("//")) 
				PRODUCTOS.add(Producto.parse(linea));
		}
	}
	
	// Métodos para productos.
	public static Integer getIngresos(Integer i) {
		return PRODUCTOS.get(i).precio();
	}
	
	public static Integer getMaxUnidades(Integer i) {
		return PRODUCTOS.get(i).maxUnidades();
	}
	
	public static Producto getProducto(Integer i) {
		return PRODUCTOS.get(i);
	}
	
	public static Integer getNumProductos() {
		return PRODUCTOS.size();
	}
	

	// Métodos para componentes.
	private static Integer getTiempoComponenteEnProduccion(Integer j) {
		return COMPONENTES.get(j).tiempoProduccion();
	}
	
	private static Integer getTiempoComponenteEnElaboracion(Integer j) {
		return COMPONENTES.get(j).tiempoElaboracion();
	}
		
	public static Integer getNumComponentes() {
		return COMPONENTES.size();
	}
	
	// Métodos para ambos.
	private static Integer getNumComponentesDelProducto(Integer i, Integer j) {
		var res = PRODUCTOS.get(i).componentes().get(j);
		return res != null ? res: 0;
	}
	
	public static Integer getTiempoComponenteDelProductoEnProduccion(Integer i, Integer j) {
		return getNumComponentesDelProducto(i, j) * getTiempoComponenteEnProduccion(j);
	}
	
	public static Integer getTiempoComponenteDelProductoEnElaboracion(Integer i, Integer j) {
		return getNumComponentesDelProducto(i, j) * getTiempoComponenteEnElaboracion(j);
	}
	
	// Otros métodos
	public static Integer getMaxTiempoEnProduccion() {
		return TOTAL_PRODUCCION;
	}
		
	public static Integer getMaxTiempoEnElaboracion() {
		return TOTAL_MANUAL;
	}
	
	public static void main(String[] args) {
		initDatos("data/PI5Ej3DatosEntrada1.txt");
		System.out.println("Datos productos -> " + PRODUCTOS);
		System.out.println("Ingresos del primer componente -> " + getIngresos(0));
		System.out.println("Número máximo de unidades del primer producto -> " + getMaxUnidades(0));
		System.out.println("Primer producto -> " + getProducto(0));
		System.out.println("Datos componentes -> " + COMPONENTES);
		System.out.println("Tiempo de producción del primer componente -> " + getTiempoComponenteEnProduccion(0));
		System.out.println("Tiempo acabado del segundo componente -> " + getTiempoComponenteEnElaboracion(0));
		System.out.println("Número de componentes -> " + getNumComponentes());
		System.out.println("Número de componetes del primer componente para el primer producto -> " + getNumComponentesDelProducto(0, 0));
		System.out.println("Tiempo de producción del primer componente para el primer producto -> " + getTiempoComponenteDelProductoEnProduccion(0, 0));
		System.out.println("Tiempo de acabado manual del primer componente para el primer producto -> " + getTiempoComponenteDelProductoEnElaboracion(0, 0));
		System.out.println("Tiempo máximo de producción -> " + getMaxTiempoEnProduccion());
		System.out.println("Tiempo máximo de acabado manual -> " + getMaxTiempoEnElaboracion());
	}
}
