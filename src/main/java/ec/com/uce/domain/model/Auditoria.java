package ec.com.uce.domain.model;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "auditoria")
public class Auditoria extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "seq_auditoria_generador", sequenceName = "seq_auditoria", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_auditoria_generador")
    private Integer id;

    @Column(name = "audi_nombreMetodo")
    private String nombreMetodo;

    @Column(name = "audi_argumentos")
    private String argumentos;

    @Column(name = "audi_fechaHoraEjecucion")
    private LocalDateTime fechaHoraEjecucion;

    @Column(name = "audi_tiempoHora")
    private Long tiempoEjecucuionMS;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreMetodo() {
        return nombreMetodo;
    }

    public void setNombreMetodo(String nombreMetodo) {
        this.nombreMetodo = nombreMetodo;
    }

    public String getArgumentos() {
        return argumentos;
    }

    public void setArgumentos(String argumentos) {
        this.argumentos = argumentos;
    }

    public Long getTiempoEjecucuionMS() {
        return tiempoEjecucuionMS;
    }

    public void setTiempoEjecucuionMS(Long tiempoEjecucuionMS) {
        this.tiempoEjecucuionMS = tiempoEjecucuionMS;
    }

    public LocalDateTime getFechaHoraEjecucion() {
        return fechaHoraEjecucion;
    }

    public void setFechaHoraEjecucion(LocalDateTime fechaHoraEjecucion) {
        this.fechaHoraEjecucion = fechaHoraEjecucion;
    }

}
