package com.mycompany.serviciotecnico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class InventarioProductos { // Administra todos los productos
    private  Map<String,ProductoVenta> mapaProductos = new HashMap<>();
    
    public InventarioProductos(){
    }
    
    public void agregarProducto(ProductoVenta producto){ // Si no existe en el mapa es agregado
        if(!this.mapaProductos.containsKey(producto.getId())){
            this.mapaProductos.put(producto.getId(), producto);
        }
    }
    
    public void eliminarProducto(String id){ // Elimina un producto segun ID
        if(this.mapaProductos.containsKey(id)){
            this.mapaProductos.remove(id);
        }
    }
    
    public ProductoVenta buscarProducto(String id){ // Busqueda de producto por ID
        if(this.mapaProductos.containsKey(id)){
            return this.mapaProductos.get(id);
        }
        return null;
    }
    
    public void mostrarInventario(){ // Se muestran todos los productos almacenados
        Iterator<String> ids = this.mapaProductos.keySet().iterator();
        while(ids.hasNext()){
            ProductoVenta productoActual = this.mapaProductos.get(ids.next());
            productoActual.mostrarProducto();
        }
    }   
    
    public void getCantidadProductos(){ // Obtencion de cantidad de productos
        System.out.println(this.mapaProductos.size());
    }
    
    public int getNuevoId(){ // Cuando se agregan nuevos productos esta función le asigna su ID
        return this.mapaProductos.size()+1;
    }
    
    public boolean isMapaProductosEmpty(){ 
        return this.mapaProductos.isEmpty();
    }
    
    public String getNombreProducto(String id){ // Para obtener solamente el NOMBRE usando ID del producto
        if(this.mapaProductos.containsKey(id)){
            return this.mapaProductos.get(id).getNombre();
        }
        return null;
    }
    
    public void modificarProducto(String id){ // Funcion que modifica el producto seleccionado por ID
        Scanner nuevo = new Scanner(System.in);
        if(this.mapaProductos.containsKey(id)){
            System.out.println("Ingresar nuevo ID del producto(mismo id para mantenerlo): ");
            String iD = nuevo.nextLine();
            mapaProductos.remove(id);
            System.out.println("Ingresar nuevo nombre del producto: ");
            String nombre = nuevo.nextLine();
            System.out.println("Ingresar nuevo precio del producto: ");
            String precio = nuevo.nextLine();
            System.out.println("Ingresa nuevo stock del producto: ");
            String stock = nuevo.nextLine();
            mapaProductos.put(iD, new ProductoVenta(iD,nombre,precio,stock));
        }else{
            System.out.println("No hay productos con el id "+id+" ingresado");
        }
    }
    
    public void filtrarProductoPrecio(int min, int max){ // Filtrado de precio por minimo y maximo
        Iterator<String> ids = this.mapaProductos.keySet().iterator();
        while(ids.hasNext()){
            ProductoVenta productoActual = this.mapaProductos.get(ids.next());
            int precioProducto = Integer.parseInt(productoActual.getPrecio());
            if(precioProducto >= min && precioProducto <= max){
                productoActual.mostrarProducto();
            }
        }
    }
    
    public void filtrarProductoStock(int min, int max){  // Filtrado de stock por minimo y maximo
        Iterator<String> ids = this.mapaProductos.keySet().iterator();
        while(ids.hasNext()){
            ProductoVenta productoActual = this.mapaProductos.get(ids.next());
            int stockProducto = Integer.parseInt(productoActual.getStock());
            if(stockProducto >= min && stockProducto <= max){
                productoActual.mostrarProducto();
            }
        }
    }
    
    public ArrayList<String> getCSVlist(){               // Se genera una linea POR producto en el mapa, asi actualizamos los productos despues
        ArrayList<String> newList = new ArrayList<>();
        Iterator<String> ids = this.mapaProductos.keySet().iterator();
        while(ids.hasNext()){
            String CSVline = this.mapaProductos.get(ids.next()).getCSVline();
            newList.add(CSVline);
        }
        return newList;
    }
}
