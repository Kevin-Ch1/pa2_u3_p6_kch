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

            for(int i = 0; i <= 500; i++){
                Libro lib = new Libro();
                lib.setTitulo("Los propios dioses");
                lib.setAutor("Isaac Asimov");
                lib.setGenero("Ciencia Ficción");
                lib.setFechaPublicacion(LocalDate.of(1972, 4, 28));
                lib.setPrecio(13.40);
                lista.add(lib);
            }

            this.libroService.guardarListaDeLibros(lista);

            return 0;
        }
    }

}
