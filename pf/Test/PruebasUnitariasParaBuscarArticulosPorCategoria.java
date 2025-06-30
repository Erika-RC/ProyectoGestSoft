
import java.util.ArrayList;
import domain.Articulo;
import domain.Tienda;
import org.junit.*;
import static org.junit.Assert.*;


public class PruebasUnitariasParaBuscarArticulosPorCategoria {
    private Tienda tienda;
    
    @Before
    public void setUp(){
        tienda = new Tienda();
        tienda.getInventario().add(new Articulo("004","Blusa","Prendas Superiores",12.5)) ;
    
    }

        
    
     @Test
     public void buscarListaArticulosPorCategoriaExistente(){
      ArrayList resultado = new ArrayList<>();
            resultado.add(tienda.categoria("Prendas Superiores"));
      assertEquals(1,resultado.size());
             
    }       
      
    
    @Test 
    public void buscarPorCategoriaInexistente(){
      ArrayList resultado = tienda.categoria("Prendas Extra");
      
      assertEquals(0,resultado.size());
    
    } 
      
}
            
            


