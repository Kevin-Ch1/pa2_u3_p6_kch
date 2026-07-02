package ec.com.uce;

import java.time.LocalDate;
import java.time.LocalDateTime;

import ec.com.uce.application.service.FacturaService;
import ec.com.uce.application.service.FacturaServiceParalelo;
import ec.com.uce.application.service.MailService;
import ec.com.uce.application.service.ReporteService;
import ec.com.uce.application.service.interceptor.MedirTiempo;
import ec.com.uce.domain.model.Factura;
import ec.com.uce.domain.model.Mail;
import ec.com.uce.domain.model.Reporte;
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
        private FacturaServiceParalelo facturaServiceP;

        @Override
        public int run(String... args) throws Exception {
            
            String nombreHilo = Thread.currentThread().getName();
            System.out.println("Nombre de hilo Main " + nombreHilo);
            System.out.println("ID " + Thread.currentThread().threadId());

            Factura fac = new Factura();
            fac.setFecha(LocalDate.of(2026, 10, 1));
            fac.setNumero("123566");
            fac.setRuc("123456789789");
            this.facturaServiceP.guardar(fac);

            return 0;
        }
    } 

}
