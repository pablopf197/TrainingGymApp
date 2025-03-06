package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.RutinaSesionentrenamientoDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "rutina_sesionentrenamiento", schema = "bdgym", catalog = "")
@IdClass(RutinaSesionentrenamientoEntityPK.class)
public class RutinaSesionentrenamientoEntity implements Serializable, DTO<RutinaSesionentrenamientoDTO> {
    @Id
    @ManyToOne
    @JoinColumn(name = "sesionentrenamiento_id")
    private SesionentrenamientoEntity sesionentrenamiento;

    @Id
    @ManyToOne
    @JoinColumn(name = "rutina_predefinida_id")
    private RutinaPredefinidaEntity rutinaPredefinida;
    @Basic
    @Column(name = "posicion")
    private Integer posicion;

    public SesionentrenamientoEntity getSesionentrenamiento() {
        return sesionentrenamiento;
    }

    public void setSesionentrenamiento(SesionentrenamientoEntity sesionentrenamiento) {
        this.sesionentrenamiento = sesionentrenamiento;
    }

    public RutinaPredefinidaEntity getRutinaPredefinida() {
        return rutinaPredefinida;
    }

    public void setRutinaPredefinida(RutinaPredefinidaEntity rutinaPredefinida) {
        this.rutinaPredefinida = rutinaPredefinida;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RutinaSesionentrenamientoEntity that = (RutinaSesionentrenamientoEntity) o;
        return Objects.equals(sesionentrenamiento, that.sesionentrenamiento) && Objects.equals(rutinaPredefinida, that.rutinaPredefinida) && Objects.equals(posicion, that.posicion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sesionentrenamiento, rutinaPredefinida, posicion);
    }

    @Override
    public RutinaSesionentrenamientoDTO toDTO() {
        RutinaSesionentrenamientoDTO dto = new RutinaSesionentrenamientoDTO();
        dto.setPosicion(posicion);
        dto.setRutinaPredefinida(rutinaPredefinida.toDTO());
        dto.setSesionentrenamiento(sesionentrenamiento.toDTO());
        return dto;
    }

}
