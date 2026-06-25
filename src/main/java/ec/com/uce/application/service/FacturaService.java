package ec.com.uce.application.service;

import ec.com.uce.domain.model.Factura;
import ec.com.uce.infrastructure.repository.FacturaRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class FacturaService {

    @Inject
    private FacturaRepositoryImpl facturaRepositoryImpl; 

    public void guardar(Factura factura) {
        factura.persist();
        //this.facturaRepositoryImpl.persist(factura);
    }

    public Factura buscarPorId(Integer id) {
        return Factura.findById(id);
        //return this.facturaRepositoryImpl.findById(id);
    }
}
