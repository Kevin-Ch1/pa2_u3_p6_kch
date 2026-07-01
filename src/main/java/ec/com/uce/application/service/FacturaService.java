package ec.com.uce.application.service;


import ec.com.uce.application.service.interceptor.MedirTiempo;
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


    @MedirTiempo
    public void guardar(Factura factura) {
        String nombreHilo = Thread.currentThread().getName();
        System.out.println("Nombre de hilo FacturaService Guardar " + nombreHilo);
        System.out.println("ID " + Thread.currentThread().threadId());

        this.facturaRepositoryImpl.persist(factura);
    }

    @MedirTiempo
    public Factura buscarPorIdFactura(Integer id) {
        String nombreHilo = Thread.currentThread().getName();
        System.out.println("Nombre de hilo FacturaService Buscar Id " + nombreHilo);
        System.out.println("ID " + Thread.currentThread().threadId());
        return this.facturaRepositoryImpl.findById(id);
    }
}
