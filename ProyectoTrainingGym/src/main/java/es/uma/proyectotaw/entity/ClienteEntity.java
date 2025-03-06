package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.ClienteDTO;
import es.uma.proyectotaw.dto.DTO;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "cliente")
public class ClienteEntity implements Serializable, DTO<ClienteDTO> {
    @Id
    @Column(name = "usuario_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @Column(name = "peso")
    private Float peso;

    @Column(name = "altura")
    private Float altura;

    @Column(name = "objetivos", length = 250)
    private String objetivos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    @Override
    public ClienteDTO toDTO() {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(this.id);
        dto.setUsuario(this.usuario.toDTO());
        dto.setPeso(this.peso);
        dto.setAltura(this.altura);
        dto.setObjetivos(this.objetivos);
        return dto;
    }

}