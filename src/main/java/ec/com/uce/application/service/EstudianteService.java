package ec.com.uce.application.service;

import ec.com.uce.application.service.interceptor.Archivo;
import ec.com.uce.application.service.interceptor.Auditar;
import ec.com.uce.domain.model.Estudiante;
import ec.com.uce.infrastructure.repository.EstudianteRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional

public class EstudianteService {

    @Inject
    private EstudianteRepositoryImpl estudianteRepositoryImpl;

    @Auditar
    @Archivo
    public void guardarEstu(Estudiante Estudiante){
        this.estudianteRepositoryImpl.persist(Estudiante);
    }

    @Auditar
    public void actualizarEstu(Integer id, Estudiante estudiante){
        Estudiante e1 = this.estudianteRepositoryImpl.findById(id);

        if(e1 != null){
            e1.setApellido(estudiante.getApellido());
            e1.setNombre(estudiante.getNombre());
        }
    }

    @Auditar
    public void eliminarEstu(Integer id){
        
        Estudiante e1 = this.estudianteRepositoryImpl.findById(id);

        if(e1 != null){
            this.estudianteRepositoryImpl.delete(e1);
        }
    }

}
