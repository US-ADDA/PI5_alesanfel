package main.java.ejercicios.classes;

import java.util.HashMap;
import java.util.Map;

public record Producto(String id, Integer precio, Map<Integer, Integer> componentes, Integer maxUnidades) {
	
	public static Producto of(String id,Integer precio, Map<Integer, Integer> componentes, Integer maxUnidades) {
		return new Producto(id, precio, componentes, maxUnidades);
	}
	
	public static Producto parse(String linea) {
		var info_p = linea.split(">")[1].split(";");
		var map = new HashMap<Integer, Integer>();
		var num_c = info_p[1].split("=")[1].split(",");
		for (var i = 0; i < num_c.length; i++) {
			var data = num_c[i].split(":");
			String aux_key = data[0].replaceAll("\\(C", "");
			while(aux_key.charAt(0)=='0') 
				aux_key = aux_key.substring(1, aux_key.length());
			var key = Integer.parseInt(aux_key);
			var value = Integer.parseInt(data[1].replaceAll("\\)", ""));
			map.put(key-1, value);
		}
		return Producto.of(linea.split(">")[0].replaceAll(" -", ""), Integer.parseInt(info_p[0].split("=")[1].trim()),
				map, Integer.parseInt(info_p[2].split("=")[1].trim()));
	}
}
