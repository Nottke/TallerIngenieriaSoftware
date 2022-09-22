package com.mycompany.serviciotecnico;

import java.util.ArrayList;
import java.util.Date;

public class Orden { // Clase encargada de la emision de "facturas" o boletas, por adquisicion de servicios (y/o productos)
    private String idOrden;
    private String rut;    
    private Date fecha;
    private ArrayList<Servicio> listaServicios;
    

    public Orden(String rut) {
        this.rut = rut;
        this.fecha = new Date();
        this.idOrden = Long.toString(this.fecha.getTime());
        this.listaServicios = new ArrayList<>();      
    }
    
    public void mostrarOrden(){ // Muestra los servicios adquiridos
        System.out.println("La orden :"+this.idOrden+" del cliente :"+this.rut+" a la fecha : "+this.fecha+" con los siguientes servivios :");
        for (var servicioActual:this.listaServicios){
            servicioActual.mostrarServicio();
        }
    }
    
    public void agregarServicio(Servicio s){
        this.listaServicios.add(s);
    }
    
    public void mostrarServicios(){
        for(int i = 0; i<this.listaServicios.size(); i++){
            this.listaServicios.get(i).mostrarServicio();
        }
    }
    
    public int obtenerCantidadServicios(){
        return this.listaServicios.size();
    }

    public Servicio buscarServicio(int numServicio){    
        for(var servicioActual: this.listaServicios){
           if(servicioActual.getId() == numServicio){
               return servicioActual;
           }
        }
        return null;
    }
    
    public String getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getPrecio(){ // Obtiene el precio total de todos los servicios adquiridos en una misma ORDEN
        int total = 0;
        for(Servicio servicioActual: this.listaServicios){
            total += Integer.parseInt(servicioActual.getPrecio());
        }
        return total;
    }
}