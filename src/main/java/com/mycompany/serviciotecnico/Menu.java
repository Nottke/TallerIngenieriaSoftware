package com.mycompany.serviciotecnico;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
// Al menú le pasamos nuestro INVENTARIO,LISTADO DE SERVICIOS y el arreglo que almacenará las ORDENES
public class Menu {   
    public void metodMenu(InventarioProductos inventario, ConjuntoServicios listaServicios, ArrayList<Orden> ordenesCompradas){
        Scanner input = new Scanner(System.in);
        var titleOut = ("|||||| ServicioTecnico ||||||");
        Servicio servicioProductoVenta = new Servicio(0,"*Nuevo Producto*->sin servicio específico","1"); // En este servicio se almacenan todos los productos cargados
        listaServicios.agregarServicio(servicioProductoVenta); 
        boolean exiting = false;
        while(!exiting){        
            System.out.println("0.  Finalizar programa                    .- ");
            System.out.println("Consultas dedicadas a Productos : ");
            System.out.println("1.  Mostrar productos                     .- ");
            System.out.println("2.  Agregar nuevo producto                .- ");
            System.out.println("3.  Buscar producto                       .- ");
            System.out.println("4.  Modificar producto                    .- ");
            System.out.println("5.  Eliminar producto                     .- ");
            System.out.println("6.  Filtrar productos por rango de precios.- ");
            System.out.println("7.  Filtrar productos por rango de stock  .- ");
            System.out.println("Consultas dedicadas a Servicios : ");
            System.out.println("8.  Mostrar servicios                     .- ");
            System.out.println("9.  Agregar nuevo servicio                .- ");
            System.out.println("10. Buscar servicio                       .- ");
            System.out.println("11. Modificar servicio                    .- ");         
            System.out.println("12. Eliminar servicio                     .- ");
            System.out.println("13. Filtrar servicios por rango de precios.- ");
            System.out.println("Consultas dedicadas a compras");
            System.out.println("14. Emitir orden de servicios             .- ");
            try {
                System.out.println("Ingresar número de la opción");
                int option = input.nextInt();
                String a = input.nextLine();
                switch(option){
                    case 0: //System.out.println("0. Finalizar programa- ");                       
                        System.out.println("Finaliza el programa ");
                        System.out.println(titleOut);
                        return;
                    case 1: //System.out.println("1. Mostrar productos.- ");
                        System.out.println("Aquí se muestran los productos guardados : ");
                        inventario.mostrarInventario();   
                        System.out.println("Total de productos guardados en el inventario: ");
                        inventario.getCantidadProductos();
                        System.out.println(titleOut);
                        break; 
                    case 2: //System.out.println("2. Agregar nuevo producto.- "); Se selecciona un servicio o ninguno específico
                        System.out.println("Ingrese datos del nuevo producto separados por COMA ; 'nombre,precio,stock' ");//cambiar   
                        try{
                            String[] datos = input.nextLine().split(",");
                            try{
                                ProductoVenta producto;
                                producto = new ProductoVenta(Integer.toString(inventario.getNuevoId()),datos[0],datos[1],datos[2]); // vector de cuatro datos; id nombre precio stock               
                                int idServicio;
                                listaServicios.mostrarServicios();
                                System.out.println("Ingresa el numero del servicio donde vas a guardar el producto :");
                                idServicio = input.nextInt();
                                while(listaServicios.buscarServicio(idServicio) == null){
                                    System.out.println("Ingrese el numero de servicio valido: ");
                                    input.nextLine();
                                    idServicio = input.nextInt();
                                }
                                inventario.agregarProducto(producto);
                                listaServicios.agregarProductoEnServicio(idServicio, producto);
                            }catch(ArrayIndexOutOfBoundsException e){
                                throw new badInput();
                            }
                        }catch(badInput e){
                            System.out.println("Formato de ingreso de datos inválido, volviendo a menú...");
                        }                                      
                        System.out.println(titleOut);
                        break;
                    case 3:  //System.out.println("3. Buscar producto.- "); En el inventario
                        System.out.println("Ingresar código del producto a buscar : ");
                        String nombre = input.nextLine();
                        ProductoVenta productoBuscado = inventario.buscarProducto(nombre);
                        if(productoBuscado == null){
                            System.out.println("Producto no encontrado");
                        }else{
                            productoBuscado.mostrarProducto();
                        }
                        System.out.println(titleOut);
                        break;
                    case 4: //System.out.println("4. Modificar producto.- ");
                        System.out.println("Ingresar ID del producto a modificar");
                        String id = input.nextLine();
                        inventario.modificarProducto(id);
                        System.out.println(titleOut);
                        break;
                    case 5: //System.out.println("5.  Eliminar producto.- ");
                        System.out.println("Ingresar ID del producto a eliminar");
                        String idp = input.nextLine();
                        System.out.println("Producto "+inventario.getNombreProducto(idp)+" eliminado.");
                        inventario.eliminarProducto(idp);                        
                        System.out.println(titleOut);
                        break;
                    case 6: //System.out.println("6. Filtrar productos por rango de precio.- ");
                        System.out.println("Ingresar el rango de precio mínimo :");
                        int min = input.nextInt();
                        input.nextLine();
                        System.out.println("Ingresar el rango de precio máximo :");
                        int max = input.nextInt();                     
                        input.nextLine();
                        inventario.filtrarProductoPrecio(min, max);
                        System.out.println(titleOut);
                        break;
                    case 7: //System.out.println("7. Filtrar productos por rango de stock.- ");
                        System.out.println("Ingresar el rango de stock mínimo :");
                        int minStock = input.nextInt();
                        input.nextLine();
                        System.out.println("Ingresar el rango de stock máximo :");
                        int maxStock = input.nextInt();
                        input.nextLine();
                        inventario.filtrarProductoStock(minStock, maxStock);
                        System.out.println(titleOut);
                        break;
                    case 8: //System.out.println("8. Mostrar servicios.- ");
                        System.out.println("Aquí se muestran los servicios guardados : ");
                        listaServicios.mostrarServicios();
                        System.out.println("Total de servicios guardados en el sistema: ");
                        listaServicios.getCantidadServicios();
                        System.out.println(titleOut);
                        break; 
                    case 9: //System.out.println("9. Agregar nuevo servicio.- ");
                        System.out.println("Ingrese datos del nuevo servicio separados por coma; 'nombre,precio' ");                      
                        String[] dato = input.nextLine().split(",");
                        int numServicio = listaServicios.getNuevoId();
                        Servicio servicio = new Servicio(numServicio,dato[0],dato[1]);
                        listaServicios.agregarServicio(servicio);
                        System.out.println(titleOut);
                        break;
                    case 10: //System.out.println("10. Buscar servicio.- ");
                        System.out.println("Ingresar ID del servicio buscar : ");
                        int numero = input.nextInt();
                        input.nextLine();
                        Servicio servicioBuscado = listaServicios.buscarServicio(numero);
                        if(servicioBuscado == null){
                            System.out.println("Servicio no encontrado");
                        }else{
                            System.out.println("El ID->"+numero+" corresponde a -> "+servicioBuscado.getNombre()+" y tiene un valor de -> $ "+servicioBuscado.getPrecio());
                        }
                        System.out.println(titleOut);
                        break;                  
                    case 11: //System.out.println("11. Modificar servicio.- ");
                        System.out.println("Ingresar ID del servicio a modificar");
                        int ide = input.nextInt();
                        listaServicios.modificarServicio(ide);
                        System.out.println(titleOut);
                        break;        
                    case 12: //System.out.println("12. Eliminar servicio.- ");
                        System.out.println("Ingresar ID del servicio a eliminar");
                        int ids = input.nextInt();
                        input.nextLine();
                        System.out.println("Servicio "+listaServicios.getNombreServicio(ids)+" eliminado.");
                        listaServicios.eliminarServicio(ids);                        
                        System.out.println(titleOut);
                        break; 
                    case 13: //System.out.println("13. Filtrar servicios por rango de precios.- ");
                        System.out.println("Ingresar el rango de precio mínimo :");
                        int mins  = input.nextInt();
                        input.nextLine();
                        System.out.println("Ingresar el rango de precio máximo :");
                        int maxs = input.nextInt();   
                        input.nextLine();
                        listaServicios.filtrarServicioPrecio(mins, maxs);
                        System.out.println(titleOut);
                        break; 
                    case 14: //System.out.println("14. Emitir orden (boleta factura) por compra de servicios.- ");
                        System.out.println("Ingresar RUT del cliente ; '123456789' :");
                        String rut = input.nextLine();
                        System.out.println("Aquí se muestran los servicios a seleccionar :");
                        listaServicios.mostrarServicios();
                        Orden nuevaOrden = new Orden(rut);
                        int continuar = 1;
                        while(true){
                            if(continuar == 2){
                                break;
                            }
                            System.out.println("Ingrese el id del servicio a comprar :");
                            int numeroServicio = input.nextInt();
                            input.nextLine();
                            Servicio servicioComprado = listaServicios.buscarServicio(numeroServicio);
                            if(servicioComprado != null){
                               nuevaOrden.agregarServicio(servicioComprado); 
                            }else{
                                System.out.println("ID del servicio no válido");
                                continue;
                            }                        
                            System.out.println("Desea agregar otro servicio a la compra? 1->SI  2->NO");
                            continuar = input.nextInt();
                            input.nextLine();
                        }
                        nuevaOrden.mostrarOrden();                       
                        System.out.println("El total de su orden corresponde al valor de $"+nuevaOrden.getPrecio());                      
                        ordenesCompradas.add(nuevaOrden);
                        System.out.println(titleOut);
                        break;
                    default:
                        System.out.println("Sólo numeros entre 0 y 14 ");
                        System.out.println(titleOut);
                    }
                }
            catch (InputMismatchException e){
                System.out.println("Debes insertar un número ");
                input.next();              
            }
        }
    }
}

