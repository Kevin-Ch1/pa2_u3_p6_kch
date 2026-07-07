package ec.com.uce.application.service;

import ec.com.uce.domain.model.Reporte;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class ReporteServiceTarea implements Runnable {

    @Inject
    private ReporteService reporteService;

    private Reporte reporte;

    @Override
    public void run() {
        String nombreHilo = Thread.currentThread().getName();
        System.out.println("Nombre de hilo ReporteService " + nombreHilo);
        System.out.println(this.reporteService);
        this.reporteService.guardar(this.reporte);
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

}
