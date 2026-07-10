package ec.com.uce.infrastructure.repository;

import ec.com.uce.domain.model.Libro;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class LibroRepositoryImpl implements PanacheRepositoryBase<Libro, Integer> {

}
