package es.uma.proyectotaw.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class ValoracionEntityPK implements Serializable {

    private UsuarioEntity usuario;

    private SesionejercicioEntity sesionejercicio;

    private RutinaAsignadaEntity rutinaAsignada;

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public SesionejercicioEntity getSesionejercicio() {
        return sesionejercicio;
    }

    public void setSesionejercicio(SesionejercicioEntity sesionejercicio) {
        this.sesionejercicio = sesionejercicio;
    }

    public RutinaAsignadaEntity getRutinaAsignada() {
        return rutinaAsignada;
    }

    public void setRutinaAsignada(RutinaAsignadaEntity rutinaAsignada) {
        this.rutinaAsignada = rutinaAsignada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValoracionEntityPK)) return false;
        ValoracionEntityPK that = (ValoracionEntityPK) o;
        return Objects.equals(getUsuario(), that.getUsuario()) && Objects.equals(getSesionejercicio(), that.getSesionejercicio()) && Objects.equals(getRutinaAsignada(), that.getRutinaAsignada());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsuario(), getSesionejercicio(), getRutinaAsignada());
    }
}
