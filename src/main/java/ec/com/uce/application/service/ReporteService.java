package ec.com.uce.application.service;

import ec.com.uce.domain.model.Reporte;
import ec.com.uce.infrastructure.repository.ReporteRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class ReporteService {

    @Inject
    private ReporteRepositoryImpl reporteRepositoryImpl;

    public void guardar(Reporte reporte){
        reporte.persist();
        //this.reporteRepositoryImpl.persist(reporte);
    }

    public Reporte buscarPorId(Integer id){
        return Reporte.findById(id);
        //return this.reporteRepositoryImpl.findById(id);
    }

}
