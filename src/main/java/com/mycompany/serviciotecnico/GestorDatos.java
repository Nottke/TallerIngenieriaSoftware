package com.mycompany.serviciotecnico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GestorDatos {
    // Funcion que lee los servicios guardados en el TXT
    public static void leerServicios(String nombreArchivo, ConjuntoServicios listaServicios) throws FileNotFoundException {
        File archivo = new File(nombreArchivo);
        try {
            try (BufferedReader entrada = new BufferedReader(new FileReader(archivo))) {
                String st;
                while ((st = entrada.readLine()) != null) {
                    String[] newString = st.split(",");
                    String id = newString[0];
                    String nombre = newString[1];
                    String precio = newString[2];
                    Servicio servicioNuevo = new Servicio(Integer.parseInt(id), nombre, precio);
                    listaServicios.agregarServicio(servicioNuevo);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    // Funcion que lee los productos guardados en el TXT
    public static void leerProductos(String nombreArchivo, InventarioProductos inventario) throws FileNotFoundException {
        File archivo = new File(nombreArchivo);
        try {
            try (BufferedReader entrada = new BufferedReader(new FileReader(archivo))) {
                String st;
                while ((st = entrada.readLine()) != null) {
                    String[] newString = st.split(",");
                    String id = newString[0];
                    String nombre = newString[1];
                    String precio = newString[2];
                    String stock = newString[3];
                    ProductoVenta productoNuevo = new ProductoVenta(id, nombre, precio, stock);
                    inventario.agregarProducto(productoNuevo);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    // Funcion que guarda las ordenes en EJECUCION del programa, es decir, si hay compras, se generan
    public static void guardarOrdenes(String nombreArchivo, ArrayList<Orden> listaOrdenes) { // Para guardar órdenes hay que realizar compras
        FileWriter fichero = null;
        PrintWriter pw = null;
        if(listaOrdenes.isEmpty()){ // Si no se realiza ninguna orden entonces no hay reporte de boletas en esa ejecucion respectiva
            return;
        }
        try {
            fichero = new FileWriter(nombreArchivo);
            pw = new PrintWriter(fichero);
            int i = 1;
            for (var ordenActual : listaOrdenes) {
                String linea = String.format("Subtotal %d %s Rut: %s Precio: %d", i, ordenActual.getFecha().toString(), ordenActual.getRut(), ordenActual.getPrecio());
                pw.println(linea);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace(System.out);
            }
        }
    }
    // Funcion que actualiza el inventario de productos AL FINALIZAR EL PROGRAMA
    public static void guardarProductos(String nombreArchivo, InventarioProductos inventario) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(nombreArchivo);
            pw = new PrintWriter(fichero);
            ArrayList<String> CSVlines = inventario.getCSVlist();
            for (var linea : CSVlines) {
                pw.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace(System.out);
            }
        }
    }
    // Funcion que actualiza el listado de servicios AL FINALIZAR EL PROGRAMA
    public static void guardarServicios(String nombreArchivo, ConjuntoServicios listaServicios) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(nombreArchivo);
            pw = new PrintWriter(fichero);
            ArrayList<String> CSVlines = listaServicios.getCSVlist();
            for (var linea : CSVlines) {
                pw.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace(System.out);
            }
        }
    }
}
