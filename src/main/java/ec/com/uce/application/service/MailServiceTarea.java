package ec.com.uce.application.service;

import ec.com.uce.domain.model.Mail;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class MailServiceTarea implements Runnable {

    @Inject
    private MailService mailService;

    private Mail mail;

    @Override
    public void run() {
        System.out.println("Prueba desde MAIL");
        this.mailService.guardar(this.mail);
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

}
