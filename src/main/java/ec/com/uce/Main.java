package ec.com.uce;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ec.com.uce.application.service.LibroService;
import ec.com.uce.domain.model.Libro;
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
        private LibroService libroService;

        @Override
        public int run(String... args) throws Exception {

            List<Libro> lista = new ArrayList<>();

            for(int i = 0; i <= 500000; i++){
                Libro lib = new Libro();
                lib.setTitulo("Memento Mori: Recuerda tu muerte ");
                lib.setAutor("Montesinos Humberto");
                lib.setGenero("Autoayuda");
                lib.setFechaPublicacion(LocalDate.of(2025, 8, 1));
                lib.setPrecio(24.50);
                lista.add(lib);
            }

            this.libroService.guardarListaDeLibrosParalelo(lista);

            return 0;
        }
    }

}
