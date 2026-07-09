package ec.com.uce.application.service;

import java.time.LocalDateTime;

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
public class FacturaService {

    @Inject
    private FacturaRepositoryImpl facturaRepositoryImpl;

    @Inject
    private ReporteService reporteService;

    @Inject
    private MailService mailService;

    @Auditar
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
        this.reporteService.guardarRepo(repo);

        Mail mail = new Mail();
        mail.setAsunto("Factura");
        mail.setDestinatario("kchicaiza253@gmail.com");
        mail.setFechaEnvio(LocalDateTime.now());
        this.mailService.guardar(mail);

    }

    public Factura buscarPorId(Integer id) {
        return this.facturaRepositoryImpl.findById(id);
    }
}
