package ec.com.uce;

import java.time.LocalDateTime;

import ec.com.uce.application.service.MailService;
import ec.com.uce.domain.model.Mail;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;

@QuarkusMain
public class Main {

    public static void main(String[] args) {
        Quarkus.run(App.class, args);
    }

    public static class App implements QuarkusApplication {

        @Inject
        private MailService mailService;


        @Override
        public int run(String... args) throws Exception {

            String nombreHilo = Thread.currentThread().getName();
            System.out.println("Nombre de hilo Main " + nombreHilo);
            System.out.println("ID " + Thread.currentThread().threadId());

            Mail mail = new Mail();
            mail.setAsunto("Factura");
            mail.setDestinatario("kchicaiza253@gmail.com");
            mail.setFechaEnvio(LocalDateTime.now());

            this.mailService.guardarMail(mail);
            return 0;
        }
    }

}
