package ec.com.uce.application.service;

import java.util.List;

import ec.com.uce.application.service.interceptor.Auditar;
import ec.com.uce.domain.model.Libro;
import ec.com.uce.infrastructure.repository.LibroRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class LibroService {

    @Inject
    private LibroRepositoryImpl libroRepositoryImpl;

    public void guardarLibro(Libro libro){
        String nombreHilo = Thread.currentThread().getName();
        System.out.println("Nombre de hilo LibroService " + nombreHilo);
        System.out.println("ID " + Thread.currentThread().threadId());
        // try {
        //     Thread.sleep(3000);
        // } catch (InterruptedException e) {

        //     e.printStackTrace();
        // }
        this.libroRepositoryImpl.persist(libro);
    }

    @Auditar
    public void guardarListaDeLibros(List<Libro> lista){
        for (Libro p : lista) {
            this.guardarLibro(p);
        }
    }

    @Auditar
    public void guardarListaDeLibrosParalelo(List<Libro> lista){
        lista.parallelStream().forEach(libro  -> {
            this.guardarLibro(libro);
        });
    }

}
