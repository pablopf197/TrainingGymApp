package es.uma.proyectotaw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class RutinaSesionentrenamientoEntityPK implements Serializable {

    private SesionentrenamientoEntity sesionentrenamiento;

    private RutinaPredefinidaEntity rutinaPredefinida;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RutinaSesionentrenamientoEntityPK that = (RutinaSesionentrenamientoEntityPK) o;
        return Objects.equals(sesionentrenamiento, that.sesionentrenamiento) && Objects.equals(rutinaPredefinida, that.rutinaPredefinida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sesionentrenamiento, rutinaPredefinida);
    }
}
