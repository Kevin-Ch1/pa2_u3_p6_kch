package ec.com.uce.application.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import ec.com.uce.application.service.interceptor.MedirTiempo;
import ec.com.uce.domain.model.Factura;
import ec.com.uce.domain.model.Mail;
import ec.com.uce.domain.model.Reporte;
import ec.com.uce.infrastructure.repository.MailRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class MailService {

    @Inject
    private MailRepositoryImpl mailRepositoryImpl;

    @Inject
    private ReporteService reporteService;

    @Inject
    private FacturaService facturaService;

    @MedirTiempo
    public void guardarMail(Mail mail) {
        String nombreHilo = Thread.currentThread().getName();
        System.out.println("Nombre de hilo MailService Guardar" + nombreHilo);
        System.out.println("ID " + Thread.currentThread().threadId());

        this.mailRepositoryImpl.persist(mail);

        Reporte repo = new Reporte();
        repo.setCodigoReferencia("123456");
        repo.setTitulo("Reporte de la factura");
        repo.setFechaCreacion(LocalDateTime.now());
        repo.setDescripcion("Factura - 001");
        this.reporteService.guardar(repo);

        Factura fac = new Factura();
        fac.setFecha(LocalDate.of(2024, 04, 21));
        fac.setNumero("2351");
        fac.setRuc("123421357135289");
        this.facturaService.guardar(fac);

        this.facturaService.buscarPorIdFactura(1);
        
        this.reporteService.buscarPorFechaReporte(LocalDateTime.of(2024, 01, 01, 0,0,0), LocalDateTime.of(2026, 12, 31, 0,0,0));

        this.reporteService.buscarPorIdReporte(1);
    }

    public Mail buscarPorIdMail(Integer id) {
        return this.mailRepositoryImpl.findById(id);
    }

    public void actualizarMail(Mail mail) {
        this.mailRepositoryImpl.getEntityManager().merge(mail);
    }

}
