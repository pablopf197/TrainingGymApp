package es.uma.proyectotaw.dto;

import lombok.Data;
@Data
public class ValoracionDTO {
    private int puntuacion;
    private String descripcion;
    private UsuarioDTO usuario;
    private SesionejercicioDTO sesionejercicio;
    private RutinaAsignadaDTO rutinaAsignada;
}
