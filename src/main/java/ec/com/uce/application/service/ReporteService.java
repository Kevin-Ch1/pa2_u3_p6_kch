package ec.com.uce.application.service;

import java.util.List;

import ec.com.uce.application.service.interceptor.Auditar;
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

    
    public void guardarRepo(Reporte reporte) {
        String nombreHilo = Thread.currentThread().getName();
        System.out.println("Nombre de hilo ReporteService " + nombreHilo);
        System.out.println("ID " + Thread.currentThread().threadId());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.reporteRepositoryImpl.persist(reporte);

    }

    @Auditar
    public void guardarListaDeReportes(List<Reporte> lista) {
        for (Reporte p : lista) {
            this.guardarRepo(p);
        }
    }

    public Reporte buscarPorId(Integer id) {
        return this.reporteRepositoryImpl.findById(id);
    }

}
