package ec.com.uce;

import java.time.LocalDate;

import ec.com.uce.application.service.FacturaService;
import ec.com.uce.domain.model.Factura;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;

@QuarkusMain
public class Main {

    public static void main(String[] args) {
        Quarkus.run(App.class, args);    
    }

    public static class App implements QuarkusApplication{

        @Inject
        private FacturaService facturaService;

        @Override
        public int run(String... args) throws Exception {
            
            Factura f1 = new Factura();
            f1.setFecha(LocalDate.of(2025, 9, 1));
            f1.setNumero("0002");
            f1.setRuc("1748456789001");

            //this.facturaService.guardar(f1);
            
            Factura fact = this.facturaService.buscarPorId(1);
            System.out.println(fact.getNumero());

            return 0;
        }
    } 

}
