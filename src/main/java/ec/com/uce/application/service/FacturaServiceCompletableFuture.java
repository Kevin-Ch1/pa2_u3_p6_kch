package ec.com.uce.application.service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

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
public class FacturaServiceCompletableFuture {

    @Inject
    private FacturaRepositoryImpl facturaRepositoryImpl;

    @Inject
    private ReporteService reporteService;

    @Inject
    private MailService mailService;

    @MedirTiempo
    public void guardar(Factura factura) {
        String nombreHilo = Thread.currentThread().getName();
        System.out.println("Nombre de hilo FacturaService " + nombreHilo);
        System.out.println("ID " + Thread.currentThread().threadId());

        this.facturaRepositoryImpl.persist(factura);

        Reporte repo = new Reporte();
        repo.setCodigoReferencia("123456");
        repo.setTitulo("Reporte de la factura");
        repo.setFechaCreacion(LocalDateTime.now());
        repo.setDescripcion("Factura - 001");

        // Ejecuta la tarea de forma asincrona
        CompletableFuture<Void> completableReporte = CompletableFuture
                .runAsync(() -> this.reporteService.guardar(repo));

        Mail mail = new Mail();
        mail.setAsunto("Factura");
        mail.setDestinatario("kchicaiza253@gmail.com");
        mail.setFechaEnvio(LocalDateTime.now());
        CompletableFuture<Void> completableMail = CompletableFuture
                .runAsync(() -> this.mailService.guardar(mail));

        //Espera a que las dos tareas/hilos se terminen
        CompletableFuture.allOf(completableReporte, completableMail).join();

        

    }

}
