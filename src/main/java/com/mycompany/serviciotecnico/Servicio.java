package com.mycompany.serviciotecnico;

import java.util.ArrayList;

public class Servicio implements ToCSVable {
    private int id;
    private String nombre;                              
    private String precio;
    private ArrayList<ProductoVenta> listaProductos; // Cada servicio tiene asociado su propia lista de productos
    
    public Servicio(int id,String nombre, String precio) {    
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.listaProductos = new ArrayList();
    }
    
    public Servicio(int id,String nombreServicio){  // Necesario para instanciar un servicio específico 
        this.id = id;
        this.nombre = nombreServicio;
        this.precio = "-1";
        this.listaProductos = new ArrayList();
    }
    
    public Servicio (int id){
        this.id = id;
        this.listaProductos = new ArrayList();
    }
    
    public Servicio(){
    }
    
    void agregarProducto(ProductoVenta producto) {
        this.listaProductos.add(producto);
    }
    
    void agregarProducto(String id,String nombre,String precio,String stock){
        this.listaProductos.add(new ProductoVenta(id,nombre,precio,stock));
                
    }   

    public void mostrarProductos(){ //Método llamado por el menú para mostrar los productos guardados en una lista
        for(int i = 0; i<this.listaProductos.size();i++){
            this.listaProductos.get(i).mostrarProducto();
        }
    }
    
    public void mostrarServicio(){
        System.out.println("El servicio : "+this.id+" tiene un precio de $"+this.getPrecio());
    }
    public ProductoVenta buscarProducto(String nombre){ // Busqueda de producto por NOMBRE en la lista de productos
        for (int i = 0; i < this.listaProductos.size();i++){
            ProductoVenta productoActual = this.listaProductos.get(i);
            if (productoActual.getNombre().equals(nombre)){
                return productoActual;
            }
        }
        return null;
    }
    
    public int getCantidadProductos(){
        return this.listaProductos.size();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override // aca modificamos la forma en que queremos mostrar los servicios por pantalla
    public String toString(){
        return "["+getId()+"]["+getNombre()+"]->$"+getPrecio();
    }

    @Override // obtenemos una linea con los datos de id nombre y precio
    public String getCSVline() {
        String line = String.format("%s,%s,%s", getId(),getNombre(),getPrecio() );
        return line;
    }
}

