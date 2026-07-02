package ec.com.uce.application.service;

import ec.com.uce.domain.model.Reporte;
import jakarta.inject.Inject;

public class ReporteServiceTarea implements Runnable {

    @Inject
    private ReporteService reporteService;

    private Reporte reporte;

    public ReporteServiceTarea(Reporte reporte) {
        this.reporte = reporte;
    }


    @Override
    public void run() {
        this.reporteService.guardar(this.reporte);
    }

}
