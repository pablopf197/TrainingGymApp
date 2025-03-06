package es.uma.proyectotaw.dto;

import es.uma.proyectotaw.entity.TipoentrenamientoEntity;
import lombok.Data;

@Data
public class EjercicioDTO {
    private int id;
    private String nombre;
    private String descripcion;
    private String video;
    private TipoentrenamientoDTO tipoEntrenamiento;
    private Integer tipoejerciciobodybuildingId;
    private Integer tipoejerciciocrosstrainingId;
}
