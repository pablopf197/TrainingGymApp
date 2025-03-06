package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.RutinaAsignadaDTO;
import es.uma.proyectotaw.dto.RutinaPredefinidaDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "rutina_predefinida", schema = "bdgym", catalog = "")
public class RutinaPredefinidaEntity implements Serializable, DTO<RutinaPredefinidaDTO> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "objetivos")
    private String objetivos;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;
    @ManyToOne
    @JoinColumn(name = "tipoentrenamiento_id")
    private  TipoentrenamientoEntity tipoEntrenamiento;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public TipoentrenamientoEntity getTipoEntrenamiento() {
        return tipoEntrenamiento;
    }

    public void setTipoEntrenamiento(TipoentrenamientoEntity tipoEntrenamiento) {
        this.tipoEntrenamiento = tipoEntrenamiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RutinaPredefinidaEntity that = (RutinaPredefinidaEntity) o;
        return id == that.id && Objects.equals(nombre, that.nombre) && Objects.equals(objetivos, that.objetivos) && Objects.equals(usuario, that.usuario) && Objects.equals(tipoEntrenamiento, that.tipoEntrenamiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, objetivos, usuario, tipoEntrenamiento);
    }

    @Override
    public RutinaPredefinidaDTO toDTO() {
        RutinaPredefinidaDTO dto = new RutinaPredefinidaDTO();
        dto.setId(this.id);
        dto.setUsuario(this.usuario.toDTO());
        dto.setObjetivos(objetivos);
        dto.setNombre(this.nombre);
        dto.setTipoentrenamiento(tipoEntrenamiento.toDTO());
        return dto;
    }

}
