package ec.com.uce;

import java.time.LocalDate;

import ec.com.uce.application.service.EstudianteService;
import ec.com.uce.application.service.FacturaServiceCompletableFuture;
import ec.com.uce.domain.model.Estudiante;
import ec.com.uce.domain.model.Factura;
import ec.com.uce.infrastructure.repository.EstudianteRepositoryImpl;
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
        private EstudianteService estudianteService;

        @Override
        public int run(String... args) throws Exception {
            
            // String nombreHilo = Thread.currentThread().getName();
            // System.out.println("Nombre de hilo Main " + nombreHilo);
            // System.out.println("ID " + Thread.currentThread().threadId());

            // Factura fac = new Factura();
            // fac.setFecha(LocalDate.of(2026, 10, 1));
            // fac.setNumero("123566");
            // fac.setRuc("123456789789");
            // this.facturaServiceFuture.guardar(fac);

            Estudiante e1 = new Estudiante();
            e1.setApellido("Chicaiza");
            e1.setNombre("Kevin");

            this.estudianteService.guardarEstu(e1);

            Estudiante e2 = new Estudiante();
            e2.setApellido("Rocha");
            e2.setNombre("Israel");
            this.estudianteService.actualizarEstu(1, e2);
            
            this.estudianteService.eliminarEstu(6);

            //Cada vez que se guarde un estudiante se va a disparar archivoInterceptor

            return 0;
        }
    } 

}
