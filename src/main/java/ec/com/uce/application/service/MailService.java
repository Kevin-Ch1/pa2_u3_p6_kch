package ec.com.uce.application.service;

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

    public void guardar(Mail mail){
        mail.persist();
        //this.mailRepositoryImpl.persist(mail);
    }

    public Mail buscarPorId(Integer id){
        return Mail.findById(id);
        //return this.mailRepositoryImpl.findById(id);
    }

}
