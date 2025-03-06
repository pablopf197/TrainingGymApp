package es.uma.proyectotaw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class SesionentrenamientoHasSesionejercicioEntityPK implements Serializable {

    private SesionentrenamientoEntity sesionentrenamiento;

    private SesionejercicioEntity sesionejercicio;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SesionentrenamientoHasSesionejercicioEntityPK that = (SesionentrenamientoHasSesionejercicioEntityPK) o;
        return Objects.equals(sesionentrenamiento, that.sesionentrenamiento) && Objects.equals(sesionejercicio, that.sesionejercicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sesionentrenamiento, sesionejercicio);
    }
}
