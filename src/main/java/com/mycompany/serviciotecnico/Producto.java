package com.mycompany.serviciotecnico;

public abstract class Producto implements ToCSVable {
    private String id;
    private String nombre;
    private String stock;
   
    public Producto(String id,String nombre, String stock) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
    }  
    
    public Producto(String nombre, String stock){ // Lo necesita el constructor de ProductoVenta
        this.nombre = nombre;
        this.stock = stock;
    }
    
    public Producto(){
        nombre = new String();
        stock = new String();
    }
    
    public abstract void funcion();
    
    public void mostrarProducto(){ // Método a sobreescribir en el hijo ProductoVenta
        System.out.println("El producto ID:"+getId()+" corresponde a "+getNombre());
    }
    
     public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
    @Override
    public String toString(){ // Aquí definimos la forma en que queremos mostrar el producto
        return "ID: "+getId()+" Nombre: "+getNombre()+" Stock: "+getStock();
    }

}
