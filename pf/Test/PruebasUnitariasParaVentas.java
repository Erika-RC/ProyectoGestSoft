
import org.junit.*;
import static org.junit.Assert.*;
import domain.Articulo;
import domain.Tienda;
import domain.Venta;


public class PruebasUnitariasParaVentas {
    private Tienda tienda;
    
    
     @Before
  public void setUp(){
      tienda = new Tienda();
  }
    
    @Test
    public void regitrarVenta(){
     Articulo articulo = new Articulo("004","blusa","Prendas Superiores",12.5);
     
     String idVenta = "001";
     int cantidadVenta = 2;
     String fechaVenta = "28/3/25";   
     
     Venta venta = new Venta(idVenta,articulo,cantidadVenta,fechaVenta);
     
     
     assertEquals("004",venta.getArticulo().getIdArticulo()); 
     assertEquals(idVenta,venta.getIdVenta()); 
     assertEquals(cantidadVenta,venta.getCantidad()); 
     assertEquals(fechaVenta, venta.getFecha()); 
     assertEquals(12.5 *2, venta.getTotal(),0.0);
    
    }
    
  @Test 
    public void añadirVenta(){ 
     Articulo articulo = new Articulo("008","Camisa","Prendas Superiores",10.99);
     
     String idVenta = "009";
     int cantidadVenta = 5;
     String fechaVenta = "20/2/25";  
        
    
     Venta venta = new Venta(idVenta,articulo,cantidadVenta,fechaVenta);
     
     tienda.agregarVenta(venta);
        
     assertEquals(1,tienda.getVentas().size());
     assertEquals("008",tienda.getVentas().get(0).getArticulo().getIdArticulo());
     assertFalse(tienda.getVentas().isEmpty());
     assertTrue(tienda.getVentas().contains(venta));
    } 
    
    
    
    @Test
    public void calcularTotalVenta(){ 
    
     Articulo articulo = new Articulo("007","Pantalon","Prendas Inferiores",35.69);
    
     String idVenta = "003";
     int cantidadVenta = 4;
     String fechaVenta = "15/1/25"; 
     
     Venta venta = new Venta (idVenta,articulo,cantidadVenta,fechaVenta);
     double totalEsperado = 142.76;
     
     assertEquals(totalEsperado,venta.getTotal(),0.00);
    }
    
    
    
   @Test
    public void registrarMultiplesVentas(){
    
     Articulo articulo = new Articulo("008","Gorra","Prendas Extra",4.5);
     
    
     tienda.agregarVenta(new Venta("078",articulo,3,"6/8/24"));
     tienda.agregarVenta(new Venta("078",articulo,9,"6/2/25"));
     
     assertEquals(2,tienda.getVentas().size());
     
    }
    
    
    
    
   
    
    
}


    
    



    


