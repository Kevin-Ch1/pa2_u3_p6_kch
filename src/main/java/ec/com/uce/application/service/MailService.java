package ec.com.uce.application.service;

import ec.com.uce.application.service.interceptor.MedirTiempo;
import ec.com.uce.domain.model.Mail;
import ec.com.uce.infrastructure.repository.MailRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class MailService {

    @Inject
    private MailRepositoryImpl mailRepositoryImpl;

    @MedirTiempo
    public void guardar(Mail mail) {
        String nombreHilo = Thread.currentThread().getName();
        System.out.println("Nombre de hilo MailService " + nombreHilo);
        System.out.println("ID " + Thread.currentThread().threadId());
        this.mailRepositoryImpl.persist(mail);
    }

    public Mail buscarPorId(Integer id) {
        return this.mailRepositoryImpl.findById(id);
    }

}
