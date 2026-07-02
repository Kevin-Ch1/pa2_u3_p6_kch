package ec.com.uce.application.service;

import ec.com.uce.domain.model.Reporte;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

public class ReporteServiceTarea implements Runnable {

    // @Inject
    private ReporteService reporteService;

    private Reporte reporte;

    public ReporteServiceTarea(Reporte reporte, ReporteService reporteService) {
        this.reporteService = reporteService;
        this.reporte = reporte;
    }


    @Override
    public void run() {
        System.out.println(this.reporteService);
        this.reporteService.guardar(this.reporte);
    }

}
