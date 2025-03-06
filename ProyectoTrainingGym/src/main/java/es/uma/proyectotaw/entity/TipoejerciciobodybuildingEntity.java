package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.TipoejerciciobodybuildingDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tipoejerciciobodybuilding", schema = "bdgym", catalog = "")
public class TipoejerciciobodybuildingEntity implements Serializable, DTO<TipoejerciciobodybuildingDTO> {
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
        TipoejerciciobodybuildingEntity that = (TipoejerciciobodybuildingEntity) o;
        return id == that.id && Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo);
    }

    @Override
    public TipoejerciciobodybuildingDTO toDTO() {
        TipoejerciciobodybuildingDTO dto = new TipoejerciciobodybuildingDTO();
        dto.setId(this.id);
        dto.setTipo(this.tipo);
        return dto;
    }
}
