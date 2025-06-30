
import domain.Tienda;
import domain.Articulo;
import domain.Devolucion;
import domain.Venta;
import org.junit.*;
import static org.junit.Assert.*;

        
public class PruebasUnitariasParaDevoluciones {
    private Tienda tienda;
    
    @Before
    public void setUp(){
        tienda = new Tienda();
    }

    
    
 @Test   
   public void regitrarDevolucion(){
    
     Articulo articulo = new Articulo("004","Blusa","Prendas Superiores",12.5);
     Venta venta = new Venta("001",articulo,2,"28/3/25");
     String idDevolucion = "346";
     int cantidadDevuelta = 2 ;
     String fechaDevolucion = "1/4/25";
   
     Devolucion devolucion = new Devolucion(idDevolucion,venta,cantidadDevuelta,fechaDevolucion);
    
     assertEquals(idDevolucion,devolucion.getIdDevolucion()); 
     assertEquals("001", devolucion.getVenta().getIdVenta()); 
     assertEquals("004", devolucion.getVenta().getArticulo().getIdArticulo()); 
     assertEquals(cantidadDevuelta,devolucion.getCantidadDevuelta()); 
     assertEquals(fechaDevolucion, devolucion.getFecha()); 
     assertEquals(true,devolucion.getVenta().getArticulo().getIsDevuelto());
   }
   
   
   
   
    @Test 
    public void añadirDevolucion(){
    
     Articulo articulo = new Articulo("Camisa","Prendas Superiores","008",10.99);
     Venta venta = new Venta("009",articulo,5,"20/2/25");

     String idDevolucion = "308" ;
     int cantidadDevuelta = 1;
     String fechaDevolucion = "27/2/25" ;
       
        
     Devolucion devolucion = new Devolucion(idDevolucion,venta,cantidadDevuelta,fechaDevolucion);  
     tienda.agregarDevolucion(devolucion);
     
      
     assertEquals(1,tienda.getDevoluciones().size()); 
     assertFalse(tienda.getDevoluciones().isEmpty()); 
     assertTrue(tienda.getDevoluciones().contains(devolucion));
     
    }
     
    
    
    @Test
    public void registrarMultiplesDevoluciones(){
     
     Articulo articulo = new Articulo("Gorra","Prendas Extra","007",4.5);
     
     Venta venta = new Venta("078",articulo,3,"8/2/25");
     Venta venta1 = new Venta("098",articulo,9,"6/2/25");
     
     
     tienda.agregarDevolucion(new Devolucion("678",venta,3,"10/2/25"));
     tienda.agregarDevolucion(new Devolucion("378",venta1,2,"10/2/25"));
     
     assertEquals(2,tienda.getDevoluciones().size());
     
    }
    
}
 
