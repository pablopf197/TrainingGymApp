package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.UsuarioDTO;
import es.uma.proyectotaw.dto.ValoracionDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "valoracion", schema = "bdgym", catalog = "")
@IdClass(ValoracionEntityPK.class)
public class ValoracionEntity implements Serializable, DTO<ValoracionDTO> {
    @Basic
    @Column(name = "puntuacion")
    private Integer puntuacion;
    @Basic
    @Column(name = "descripcion")
    private String descripcion;

    @Id
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "sesionejercicio_id")
    private SesionejercicioEntity sesionejercicio;

    @Id
    @ManyToOne
    @JoinColumn(name = "rutina_asignada_id")
    private RutinaAsignadaEntity rutinaAsignada;

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

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
        if (!(o instanceof ValoracionEntity)) return false;
        ValoracionEntity that = (ValoracionEntity) o;
        return Objects.equals(getPuntuacion(), that.getPuntuacion()) && Objects.equals(getDescripcion(), that.getDescripcion()) && Objects.equals(getUsuario(), that.getUsuario()) && Objects.equals(getSesionejercicio(), that.getSesionejercicio()) && Objects.equals(getRutinaAsignada(), that.getRutinaAsignada());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPuntuacion(), getDescripcion(), getUsuario(), getSesionejercicio(), getRutinaAsignada());
    }

    @Override
    public ValoracionDTO toDTO() {
        ValoracionDTO dto = new ValoracionDTO();
        dto.setPuntuacion(getPuntuacion());
        dto.setDescripcion(getDescripcion());
        dto.setUsuario(getUsuario().toDTO());
        dto.setSesionejercicio(getSesionejercicio().toDTO());
        dto.setRutinaAsignada(getRutinaAsignada().toDTO());
        return dto;
    }
}
