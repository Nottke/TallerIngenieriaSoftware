package com.mycompany.serviciotecnico;

public class ProductoVenta extends Producto {
    private String precio;

    public ProductoVenta(String id,String nombre,String precio, String stock){
        super(id,nombre,stock);
        this.precio = precio;
    }
    
    public ProductoVenta(String nombre, String precio, String stock){
        super(nombre, stock);
        this.precio = precio;
    }
    
    @Override
    public void funcion(){
    }
    
    @Override
    public void mostrarProducto(){
        System.out.println("[ Id("+getId()+")  "+getStock()+" disponibles  $ "+getPrecio()+"  Producto: "+getNombre()+" ]");
    }
    
    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
    
    @Override
    public String getCSVline(){ // Se obtienen los datos y se agrupan en una linea {getCSVline es método de ToCSVable interface)
        String line = String.format("%s,%s,%s,%s", getId(),getNombre(),getPrecio(),getStock() );
        return line;
    }
}
