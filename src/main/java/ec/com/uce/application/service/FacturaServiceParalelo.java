package ec.com.uce.application.service;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ec.com.uce.application.service.interceptor.Auditar;
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
    private ReporteServiceTarea reporteServiceTarea;

    @Inject
    private MailServiceTarea mailServiceTarea;


    @Auditar
    public void guardar(Factura factura) throws InterruptedException, ExecutionException {
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
        
        this.reporteServiceTarea.setReporte(repo);
        Future<?> repoFuture = executorService.submit(reporteServiceTarea);

        Mail mail = new Mail();
        mail.setAsunto("Factura");
        mail.setDestinatario("kchicaiza253@gmail.com");
        mail.setFechaEnvio(LocalDateTime.now());
        
        this.mailServiceTarea.setMail(mail);
        Future<?> mailFuture = executorService.submit(mailServiceTarea);

        repoFuture.get();

        mailFuture.get();

        executorService.shutdown();
        
        
    }

}
