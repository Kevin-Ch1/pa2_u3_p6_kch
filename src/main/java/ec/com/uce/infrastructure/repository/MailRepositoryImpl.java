package ec.com.uce.infrastructure.repository;

import ec.com.uce.domain.model.Mail;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class MailRepositoryImpl implements PanacheRepositoryBase<Mail, Integer> {

}
