package ec.com.uce.application.service;

import ec.com.uce.domain.model.Auditoria;
import ec.com.uce.infrastructure.repository.AuditoriaRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuditoriaService {

    @Inject
    private AuditoriaRepositoryImpl auditoriaRepositoryImpl;

    
    public void guardarAud(Auditoria auditoria) {
        this.auditoriaRepositoryImpl.persist(auditoria);
    }

}
