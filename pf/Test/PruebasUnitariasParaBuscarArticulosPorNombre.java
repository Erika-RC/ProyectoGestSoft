
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import domain.Articulo;
import domain.Tienda;


public class PruebasUnitariasParaBuscarArticulosPorNombre {
    private Tienda tienda;
 
           
    @Before
    public void setUp(){
        tienda = new Tienda();
        tienda.getInventario().add(new Articulo("004","Camisa","Prendas Superiores",12.5));  
    
    }
    
     @Test
     public void buscarListaArticulosPorNombreExistente(){
        ArrayList<Articulo> resultado = tienda.nombre("Camisa");
    
                    
     
     assertEquals(1,resultado.size());        
    }       
      
    
    @Test 
    public void buscarPorNombreInexistente(){
      ArrayList <Articulo> resultado = tienda.nombre("Blusa");
      
     assertEquals(0,resultado.size());
    
    }
    
            
    @Test 
    public void buscarArticuloConMayusculasYMinusculas(){
        ArrayList resultado = tienda.nombre("camisa");
        
        
        assertEquals(1,resultado.size()); 
    }    

}