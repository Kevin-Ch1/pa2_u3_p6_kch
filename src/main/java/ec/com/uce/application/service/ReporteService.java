package ec.com.uce.application.service;

import java.time.LocalDateTime;
import java.util.List;

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

    @MedirTiempo
    public void guardar(Reporte reporte) {
        String nombreHilo = Thread.currentThread().getName();
        System.out.println("Nombre de hilo ReporteService " + nombreHilo);
        System.out.println("ID " + Thread.currentThread().threadId());
        this.reporteRepositoryImpl.persist(reporte);
    }

    @MedirTiempo
    public Reporte buscarPorIdReporte(Integer id) {
        String nombreHilo = Thread.currentThread().getName();
        System.out.println("Nombre de hilo ReporteService Buscar Id " + nombreHilo);
        System.out.println("ID " + Thread.currentThread().threadId());
        return this.reporteRepositoryImpl.findById(id);
    }

    public void eliminarPorIdReporte(Integer id){
        this.reporteRepositoryImpl.deleteById(id);
    }

    @MedirTiempo
    public List<Reporte> buscarPorFechaReporte(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        String nombreHilo = Thread.currentThread().getName();
        System.out.println("Nombre de hilo ReporteService Buscar Fecha " + nombreHilo);
        System.out.println("ID " + Thread.currentThread().threadId());
        return this.reporteRepositoryImpl.buscarPorFecha(fechaInicio, fechaFin);
    } 

}
