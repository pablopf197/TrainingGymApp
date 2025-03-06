package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.ClienteDTO;
import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.SesionentrenamientoHasSesionejercicioDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "sesionentrenamiento_has_sesionejercicio", schema = "bdgym", catalog = "")
@IdClass(SesionentrenamientoHasSesionejercicioEntityPK.class)
public class SesionentrenamientoHasSesionejercicioEntity implements Serializable, DTO<SesionentrenamientoHasSesionejercicioDTO> {
    @Id
    @ManyToOne
    @JoinColumn(name = "sesionentrenamiento_id")
    private SesionentrenamientoEntity sesionentrenamiento;

    @Id
    @ManyToOne
    @JoinColumn(name = "sesionejercicio_id")
    private SesionejercicioEntity sesionejercicio;


    @Basic
    @Column(name = "posicion")
    private Integer posicion;

    public SesionentrenamientoEntity getSesionentrenamiento() {
        return sesionentrenamiento;
    }

    public void setSesionentrenamiento(SesionentrenamientoEntity sesionentrenamiento) {
        this.sesionentrenamiento = sesionentrenamiento;
    }

    public SesionejercicioEntity getSesionejercicio() {
        return sesionejercicio;
    }

    public void setSesionejercicio(SesionejercicioEntity sesionejercicio) {
        this.sesionejercicio = sesionejercicio;
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
        SesionentrenamientoHasSesionejercicioEntity that = (SesionentrenamientoHasSesionejercicioEntity) o;
        return Objects.equals(sesionentrenamiento, that.sesionentrenamiento) && Objects.equals(sesionejercicio, that.sesionejercicio) && Objects.equals(posicion, that.posicion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sesionentrenamiento, sesionejercicio, posicion);
    }

    @Override
    public SesionentrenamientoHasSesionejercicioDTO toDTO() {
        SesionentrenamientoHasSesionejercicioDTO dto = new  SesionentrenamientoHasSesionejercicioDTO();
        dto.setPosicion(posicion);
        dto.setSesionentrenamiento(sesionentrenamiento.toDTO());
        dto.setSesionejercicio(sesionejercicio.toDTO());
        return dto;
    }
}
