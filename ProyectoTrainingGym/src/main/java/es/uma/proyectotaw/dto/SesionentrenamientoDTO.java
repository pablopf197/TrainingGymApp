package es.uma.proyectotaw.dto;

import lombok.Data;
@Data
public class SesionentrenamientoDTO {
    private int id;
    private String nombre;
    private String descripcion;
    private UsuarioDTO usuario;
}
