package com.mycompany.serviciotecnico;

public class ProductoDescripcion extends ProductoVenta {
    private String tipo;
    private String descripcion;  
    
    public ProductoDescripcion(String id,String nombre,String precio, String stock, String tipo, String descripcion){
        super(id,nombre,precio,stock);
        this.tipo = tipo;
        this.descripcion = descripcion;
    }
    // Por tiempo no esta implementado pero la idea era cargar un TXT con los tipos y descripciones
    // especificas de cada producto, para dar una informacion mas detallada de cada producto en venta
    @Override
    public void mostrarProducto(){
        System.out.println("El producto "+getNombre()+" se clasifica como un "+getTipo()+" y se le asocia la siguiente informacion: "+this.getDescripcion());
    }

    @Override
    public String getCSVline(){
        return ("Producto:"+getNombre()+"-Stock:"+getStock()+"-Tipo:"+getTipo()+"-Descripcion:"+this.getDescripcion());
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
