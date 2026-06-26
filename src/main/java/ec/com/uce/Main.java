package ec.com.uce;

import java.time.LocalDate;
import java.time.LocalDateTime;

import ec.com.uce.application.service.FacturaService;
import ec.com.uce.application.service.MailService;
import ec.com.uce.application.service.ReporteService;
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
        private ReporteService reporteService;

        @Inject
        private MailService mailService;

        @Override
        public int run(String... args) throws Exception {
            
            Reporte rep = new Reporte();
            rep.setCodigoReferencia("R001");
            rep.setTipoReporte("Factura");
            rep.setTitulo("Problemas factura");
            rep.setDescripcion("No registra un campo");
            rep.setFechaCreacion(LocalDateTime.now());

            reporteService.guardar(rep);
            System.out.println(reporteService.buscarPorId(1).getTitulo());

            Mail mail = new Mail();
            mail.setRemitente("Kevin");
            mail.setDestinatario("Julia");
            mail.setAsunto("Fotos dataset");
            mail.setCuerpo("Envio fotos de dataset");
            mail.setFechaEnvio(LocalDateTime.now());

            mailService.guardar(mail);
            System.out.println(mailService.buscarPorId(1).getAsunto());


            return 0;
        }
    } 

}
