package ec.com.uce.application.service;

import ec.com.uce.domain.model.Mail;
import jakarta.inject.Inject;

// Lo que se ejecute en esta clase se ejecute en un hilo diferente
// Ejecutar una tarea en un hilo propio
public class MailServiceTarea implements Runnable {

    @Inject
    private MailService mailService;
    
    private Mail mail;

    public MailServiceTarea(Mail mail) {
        this.mail = mail;
    }

    // El metodo se ejecuta en un hilo en especifico
    @Override
    public void run() {
        System.out.println("Prueba desde MAIL");
        this.mailService.guardar(this.mail);
    }

}
