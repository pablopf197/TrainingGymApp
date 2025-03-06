package es.uma.proyectotaw.dto;

import lombok.Data;

@Data
public class RutinaPredefinidaDTO {
    private int id;
    private String nombre;
    private String objetivos;
    private UsuarioDTO usuario;
    private TipoentrenamientoDTO tipoentrenamiento;
}
