package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.TipoejerciciocrosstrainingDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tipoejerciciocrosstraining", schema = "bdgym", catalog = "")
public class TipoejerciciocrosstrainingEntity implements Serializable, DTO<TipoejerciciocrosstrainingDTO> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "tipo")
    private Object tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getTipo() {
        return tipo;
    }

    public void setTipo(Object tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoejerciciocrosstrainingEntity that = (TipoejerciciocrosstrainingEntity) o;
        return id == that.id && Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo);
    }

    @Override
    public TipoejerciciocrosstrainingDTO toDTO() {
        TipoejerciciocrosstrainingDTO dto = new TipoejerciciocrosstrainingDTO();
        dto.setId(this.id);
        dto.setTipo(this.tipo);
        return dto;
    }
}
