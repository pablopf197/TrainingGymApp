package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.SesionejercicioDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "sesionejercicio", schema = "bdgym", catalog = "")
public class SesionejercicioEntity implements Serializable, DTO<SesionejercicioDTO> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "series")
    private Integer series;
    @Basic
    @Column(name = "repeticiones")
    private Integer repeticiones;
    @Basic
    @Column(name = "duracion")
    private Integer duracion;
    @ManyToOne
    @JoinColumn(name = "ejercicio_id")
    private EjercicioEntity ejercicio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(Integer repeticiones) {
        this.repeticiones = repeticiones;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public EjercicioEntity getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(EjercicioEntity ejercicio) {
        this.ejercicio = ejercicio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SesionejercicioEntity that = (SesionejercicioEntity) o;
        return id == that.id && Objects.equals(series, that.series) && Objects.equals(repeticiones, that.repeticiones) && Objects.equals(duracion, that.duracion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, series, repeticiones, duracion);
    }

    @Override
    public SesionejercicioDTO toDTO() {
        SesionejercicioDTO dto = new SesionejercicioDTO();
        dto.setId(id);
        if (series != null){
            dto.setSeries(series);
        }
        if (repeticiones != null){
            dto.setRepeticiones(repeticiones);
        }
        if(duracion != null){
            dto.setDuracion(duracion);
        }
        dto.setEjercicio(ejercicio.toDTO());
        return dto;
    }
}
