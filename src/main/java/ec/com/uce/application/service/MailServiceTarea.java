package ec.com.uce.application.service;

import ec.com.uce.domain.model.Mail;
import jakarta.inject.Inject;

public class MailServiceTarea implements Runnable {

    // @Inject
    private MailService mailService;
    
    private Mail mail;

    public MailServiceTarea(Mail mail, MailService mailService) {
        this.mailService = mailService;
        this.mail = mail;
    }

    @Override
    public void run() {
        System.out.println("Prueba desde MAIL");
        this.mailService.guardar(this.mail);
    }

}
