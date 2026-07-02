package ec.com.uce.application.service;

import ec.com.uce.application.service.interceptor.MedirTiempo;
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

    //@MedirTiempo
    public void guardar(Reporte reporte) {
        String nombreHilo = Thread.currentThread().getName();
        System.out.println("Nombre de hilo ReporteService " + nombreHilo);
        System.out.println("ID " + Thread.currentThread().threadId());
        this.reporteRepositoryImpl.persist(reporte);
    }

    public Reporte buscarPorId(Integer id) {
        return this.reporteRepositoryImpl.findById(id);
    }

}
