package com.mycompany.serviciotecnico;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
/**
 * 
 * @author sebas
 */

public class servicioTecnico {
    public static void main(String[] args) throws FileNotFoundException {
        Calendar fechaActual = Calendar.getInstance();
        int sec = fechaActual.get(Calendar.SECOND);
        int min = fechaActual.get(Calendar.MINUTE);
        int hou = fechaActual.get(Calendar.HOUR);
        int day = fechaActual.get(Calendar.DAY_OF_MONTH);
        int mon = fechaActual.get(Calendar.MONTH)+1;
        int yea = fechaActual.get(Calendar.YEAR);
        String fechaActualStr = String.format("%d-%d-%d(%d_%d_%d)", yea,mon,day,hou,min,sec);
        String nombreArchivoReporte = String.format("src/main/resources/Reportes%s.txt", fechaActualStr); // Obtencion de fechas para reportes
        InventarioProductos inventario = new InventarioProductos(); // Inventario almacena TODOS los productos
        ConjuntoServicios listaServicios = new ConjuntoServicios(); // ListaServicios almacena TODOS los servicios
        ArrayList<Orden> ordenesCompradas = new ArrayList<>();      
        System.out.println("|||||| Bienvenido al Servicio Tecnico ||||||");
        System.out.println("Seleccione alguna opción porfavor : ");     
        GestorDatos.leerProductos("src/main/resources/Productos.txt",inventario);     // Carga de productos
        GestorDatos.leerServicios("src/main/resources/Servicios.txt",listaServicios); // Carga de servicios
        Menu m = new Menu ();  
        m.metodMenu(inventario,listaServicios,ordenesCompradas);                      // AQUI se da INICIO al menú del programa
        GestorDatos.guardarOrdenes(nombreArchivoReporte ,ordenesCompradas);           // Si hubo compras, se añaden al reporte de ordenes
        GestorDatos.guardarServicios("src/main/resources/Servicios.txt", listaServicios); // Actualiza el registro de servicios
        GestorDatos.guardarProductos("src/main/resources/Productos.txt", inventario);     // Actualiza el registro de productos
        System.out.println("[He compilado sin problemas, finalización del programa normal. ]");
    }
}
