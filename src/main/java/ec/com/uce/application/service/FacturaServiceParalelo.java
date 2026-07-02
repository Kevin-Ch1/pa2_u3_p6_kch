package ec.com.uce.application.service;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ec.com.uce.application.service.interceptor.MedirTiempo;
import ec.com.uce.domain.model.Factura;
import ec.com.uce.domain.model.Mail;
import ec.com.uce.domain.model.Reporte;
import ec.com.uce.infrastructure.repository.FacturaRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class FacturaServiceParalelo {

    @Inject
    private FacturaRepositoryImpl facturaRepositoryImpl;

    @Inject
    private ReporteService reporteService;

    @Inject
    private MailService mailService;

   // @MedirTiempo
    public void guardar(Factura factura) {
        String nombreHilo = Thread.currentThread().getName();
        System.out.println("Nombre de hilo FacturaService " + nombreHilo);
        System.out.println("ID " + Thread.currentThread().threadId());

        this.facturaRepositoryImpl.persist(factura);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Reporte repo = new Reporte();
        repo.setCodigoReferencia("123456");
        repo.setTitulo("Reporte de la factura");
        repo.setFechaCreacion(LocalDateTime.now());
        repo.setDescripcion("Factura - 001");
        ReporteServiceTarea reporteTarea = new ReporteServiceTarea(repo);
        executorService.submit(reporteTarea);

        Mail mail = new Mail();
        mail.setAsunto("Factura");
        mail.setDestinatario("kchicaiza253@gmail.com");
        mail.setFechaEnvio(LocalDateTime.now());
        MailServiceTarea mailTarea = new MailServiceTarea(mail);
        executorService.submit(mailTarea);

        // Cerrar el proceso de ejecución indicando que no se envian más tareas
        executorService.shutdown();

    }

}
