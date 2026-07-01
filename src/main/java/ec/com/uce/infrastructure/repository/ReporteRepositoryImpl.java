package ec.com.uce.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.List;

import ec.com.uce.domain.model.Reporte;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class ReporteRepositoryImpl implements PanacheRepositoryBase<Reporte, Integer> {

    @Inject
    private EntityManager em;

    public List<Reporte> buscarPorFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        TypedQuery<Reporte> q1 = this.em.createQuery(
                "SELECT r FROM Reporte r WHERE r.fechaCreacion >= :fechaInicio AND r.fechaCreacion < :fechaFin",
                Reporte.class);
        q1.setParameter("fechaInicio", fechaInicio);
        q1.setParameter("fechaFin", fechaFin);

        return q1.getResultList();

    }

}
