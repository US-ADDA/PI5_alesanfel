package main.java.ejercicios.data;

import main.java.ejercicios.classes.Componente;
import main.java.ejercicios.classes.Producto;
import us.lsi.common.Files2;

import java.util.ArrayList;
import java.util.List;

/**
 * Los datos necesarios para resolver el ejercicio 3.
 */
public class DatosEjercicio3 {

    private static List<Componente> componentes;
    private static List<Producto> productos;
    private static Integer totalProduccion;
    private static Integer totalManual;

    /**
     * Cargar los datos de un fichero.
     *
     * @param path la ruta del fichero.
     */
    public static void initDatos(String path) {
        componentes = new ArrayList<>();
        productos = new ArrayList<>();
        for (var linea : Files2.linesFromFile(path)) {
            if (linea.contains("T_prod =") && !linea.contains("//"))
                totalProduccion = Integer.parseInt(linea.split("=")[1].trim());
            else if (linea.contains("T_manual =") && !linea.contains("//"))
                totalManual = Integer.parseInt(linea.split("=")[1].trim());
            else if (linea.contains("C") && !linea.contains("->") && !linea.contains("//"))
                componentes.add(Componente.parse(linea));
            else if (linea.contains("P") && !linea.contains("//"))
                productos.add(Producto.parse(linea));
        }
    }

    // <- MÉTODOS PARA PRODUCTOS -> //

    /**
     * Obtiene los ingresos para un producto.
     *
     * @param i el índice correspondiente al producto en la lista {@code productos}.
     * @return los ingresos que produce el producto.
     */
    public static Integer getIngresos(Integer i) {
        return productos.get(i).precio();
    }

    /**
     * Obtiene el número máximo de unidades que se puede producir de un producto.
     *
     * @param i el índice correspondiente al producto en la lista {@code productos}.
     * @return el número máximo de unidades.
     */
    public static Integer getMaxUnidades(Integer i) {
        return productos.get(i).maxUnidades();
    }

    /**
     * Obtiene una instancia del tipo {@link Producto}.
     *
     * @param i el índice correspondiente al producto en la lista {@code productos}.
     * @return una instancia del tipo {@link Producto}.
     */
    public static Producto getProducto(Integer i) {
        return productos.get(i);
    }

    /**
     * Obtiene el número de productos que disponemos.
     *
     * @return el número de productos que disponemos.
     */
    public static Integer getNumProductos() {
        return productos.size();
    }


    // <- MÉTODOS PARA COMPONENTES -> //

    /**
     * Obtiene el tiempo que necesita el componente para la fase de producción.
     *
     * @param j el índice correspondiente al componente en la lista {@code componentes}.
     * @return tiempo necesario para el componente en la fase de producción.
     */
    private static Integer getTiempoComponenteEnProduccion(Integer j) {
        return componentes.get(j).tiempoProduccion();
    }

    /**
     * Obtiene el tiempo que necesita el componente para la fase manual.
     *
     * @param j el índice correspondiente al componente en la lista {@code componentes}.
     * @return tiempo necesario para el componente en la fase manual.
     */
    private static Integer getTiempoComponenteEnManual(Integer j) {
        return componentes.get(j).tiempoManual();
    }

    /**
     * Obtiene el número de componentes que disponemos.
     *
     * @return el número de componentes que disponemos,
     */
    public static Integer getNumComponentes() {
        return componentes.size();
    }

    // Métodos para ambos.

    /**
     * Obtiene el número necesario para un determinado componente y producto.
     *
     * @param i el índice correspondiente al producto en la lista {@code productos}.
     * @param j el índice correspondiente al componente en la lista {@code componentes}.
     * @return el número que necesitamos para un determinado componente y producto.
     */
    private static Integer getNumComponentesDelProducto(Integer i, Integer j) {
        var res = productos.get(i).componentes().get(j);
        return res != null ? res : 0;
    }

    /**
     * Obtiene el tiempo necesario para producir un determinado componente de un producto en la fase de producción.
     *
     * @param i el índice correspondiente al producto en la lista {@code productos}.
     * @param j el índice correspondiente al componente en la lista {@code componentes}.
     * @return el tiempo necesario para producir un determinado componente de un producto en la fase de producción.
     */
    public static Integer getTiempoComponenteDelProductoEnProduccion(Integer i, Integer j) {
        return getNumComponentesDelProducto(i, j) * getTiempoComponenteEnProduccion(j);
    }

    /**
     * Obtiene el tiempo necesario para producir un determinado componente de un producto en la fase manual.
     *
     * @param i el índice correspondiente al producto en la lista {@code productos}.
     * @param j el índice correspondiente al componente en la lista {@code componentes}.
     * @return el tiempo necesario para producir un determinado componente de un producto en la fase manual.
     */
    public static Integer getTiempoComponenteDelProductoEnManual(Integer i, Integer j) {
        return getNumComponentesDelProducto(i, j) * getTiempoComponenteEnManual(j);
    }

    // <- OTROS MÉTODOS -> //

    /**
     * Obtiene el tiempo máximo en la fase de producción.
     *
     * @return el tiempo máximo en la fase de producción.
     */
    public static Integer getMaxTiempoEnProduccion() {
        return totalProduccion;
    }

    /**
     * Obtiene el tiempo máximo en la fase manual.
     *
     * @return el tiempo máximo en la fase manual.
     */
    public static Integer getMaxTiempoEnManual() {
        return totalManual;
    }
}
