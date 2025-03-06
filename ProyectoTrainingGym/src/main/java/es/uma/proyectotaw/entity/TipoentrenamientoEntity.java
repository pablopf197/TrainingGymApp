package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.TipoentrenamientoDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tipoentrenamiento", schema = "bdgym", catalog = "")
public class TipoentrenamientoEntity implements Serializable, DTO<TipoentrenamientoDTO> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoentrenamientoEnum tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoentrenamientoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoentrenamientoEnum tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoentrenamientoEntity that = (TipoentrenamientoEntity) o;
        return id == that.id && Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo);
    }


    @Override
    public TipoentrenamientoDTO toDTO() {
        TipoentrenamientoDTO dto = new TipoentrenamientoDTO();
        dto.setId(this.id);
        dto.setTipo(this.tipo);
        return dto;
    }
}
