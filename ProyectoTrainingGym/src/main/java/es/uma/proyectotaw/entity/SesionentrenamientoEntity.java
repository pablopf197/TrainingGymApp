package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.ClienteDTO;
import es.uma.proyectotaw.dto.DTO;
import es.uma.proyectotaw.dto.SesionentrenamientoDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "sesionentrenamiento", schema = "bdgym", catalog = "")
public class SesionentrenamientoEntity implements Serializable, DTO<SesionentrenamientoDTO> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "descripcion")
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SesionentrenamientoEntity that = (SesionentrenamientoEntity) o;
        return id == that.id && Objects.equals(nombre, that.nombre) && Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion);
    }

    @Override
    public SesionentrenamientoDTO toDTO() {
        SesionentrenamientoDTO dto = new SesionentrenamientoDTO();
        dto.setId(this.id);
        dto.setUsuario(this.usuario.toDTO());
        dto.setDescripcion(this.descripcion);
        dto.setNombre(this.nombre);
        return dto;
    }
}
