package ec.com.uce.infrastructure.repository;

import ec.com.uce.domain.model.Auditoria;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class AuditoriaRepositoryImpl implements PanacheRepositoryBase<Auditoria, Integer> {

}
