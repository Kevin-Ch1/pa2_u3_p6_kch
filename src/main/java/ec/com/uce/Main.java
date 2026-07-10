package ec.com.uce;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ec.com.uce.application.service.ReporteService;
import ec.com.uce.domain.model.Reporte;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;

@QuarkusMain
public class Main {

    public static void main(String[] args) {
        Quarkus.run(App.class, args);
    }

    public static class App implements QuarkusApplication {

        @Inject
        private ReporteService reporteService;

        @Override
        public int run(String... args) throws Exception {

            List<Reporte> lista = new ArrayList<>();

            for (int i = 0; i < 100000; i++) {
                Reporte repo = new Reporte();
                repo.setCodigoReferencia(""+i);
                repo.setTitulo("Reporte de la factura");
                repo.setFechaCreacion(LocalDateTime.now());
                repo.setDescripcion("Factura - 00"+i);
                lista.add(repo);
            }

            this.reporteService.guardarListaDeReportesParalelo(lista);
            return 0;
        }
    }

}
