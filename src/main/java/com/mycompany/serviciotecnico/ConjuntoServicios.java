package com.mycompany.serviciotecnico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class ConjuntoServicios { // Administra todos los servicios 
    private Map<Integer,Servicio> mapaServicios = new HashMap<>();
       
    public void ConjuntoServicios(){  
    }
    
    public void agregarServicio (Servicio servicio){  // Agregamos nuevo servicio al mapa
        if(!this.mapaServicios.containsKey(servicio.getId())){
            this.mapaServicios.put(servicio.getId(), servicio);
        }   
    }
    
    public void eliminarServicio(int numServicio){    // Eliminacion de servicio del mapa segun ID (numServicio)
        if(this.mapaServicios.containsKey(numServicio)){
            this.mapaServicios.remove(numServicio);
        }
    }
    
    public Servicio buscarServicio(int numServicio){  // Busqueda de servicio en el mapa segun ID
        if(this.mapaServicios.containsKey(numServicio)){
            return this.mapaServicios.get(numServicio);
        }
        return null;
    }
    
    public void mostrarServicios (){
        Iterator<Integer> ids = this.mapaServicios.keySet().iterator();
        while(ids.hasNext()){
            Servicio servicioActual = this.mapaServicios.get(ids.next());
            System.out.println(servicioActual);
        }    
    }
    
    public void getCantidadServicios(){  // Obtencion cantidad servicios
        System.out.println(this.mapaServicios.size());
    }
    
    public int getNuevoId(){ // Asigna ID para nuevo SERVICIO
        return this.mapaServicios.size();
    }      
    
    public boolean isMapaServiciosEmpty(){
        return this.mapaServicios.isEmpty();
    }
    
    public String getNombreServicio(int numServicio){
        if(this.mapaServicios.containsKey(numServicio)){
            return this.mapaServicios.get(numServicio).getNombre();
        }
        return null;
    }
    
    public void modificarServicio(int numServicio){ // Funcion que modifica el servicio seleccionado por ID
        Scanner nuevo = new Scanner(System.in);
        if(this.mapaServicios.containsKey(numServicio)){
            System.out.println("Ingresar nuevo ID del servicio(mismo id para mantenerlo): ");
            int iD = Integer.parseInt(nuevo.nextLine());
            mapaServicios.remove(numServicio);
            System.out.println("Ingresar nuevo nombre del servicio: ");
            String nombre = nuevo.nextLine();
            System.out.println("Ingresar nuevo precio del servicio: ");
            String precio = nuevo.nextLine();
            mapaServicios.put(iD, new Servicio(iD,nombre,precio));
        }else{
            System.out.println("No hay servicios con el ID "+numServicio+" ingresado");
        }
    }
    
    public void filtrarServicioPrecio(int min, int max){ // Filtrado de precio por min y max
        Iterator<Integer> ids = this.mapaServicios.keySet().iterator(); 
        while(ids.hasNext()){
            Servicio servicioActual = this.mapaServicios.get(ids.next());
            int precioServicio = Integer.parseInt(servicioActual.getPrecio());
            if(precioServicio >= min && precioServicio <= max){
                servicioActual.mostrarServicio();
            }
        }
    }
    
    public void agregarProductoEnServicio (int numServicio, ProductoVenta producto){ // Añade un producto a un servicio especifico
        if (this.mapaServicios.containsKey(numServicio)){
            this.mapaServicios.get(numServicio).agregarProducto(producto);
            System.out.println("Producto-> "+producto+"\nAgregado al servicio ID -> "+numServicio);
        }else{
            System.out.println("Numero de servicio inválido .");
        }
    }
    
    public ArrayList<String> getCSVlist(){  // Se genera una linea POR servicio en el mapa, asi actualizamos los servicios despues
        ArrayList<String> newList = new ArrayList<>();
        Iterator<Integer> ids = this.mapaServicios.keySet().iterator();
        while(ids.hasNext()){
            Servicio servicioActual = this.mapaServicios.get(ids.next());
            if(0 != servicioActual.getId()){
                String CSVline = servicioActual.getCSVline();
                newList.add(CSVline);
            }  
        }
        return newList;
    }
}
