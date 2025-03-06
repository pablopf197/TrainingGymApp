package es.uma.proyectotaw.dto;

import lombok.Data;
@Data
public class SesionejercicioDTO {
    private int id;
    private Integer series;
    private Integer repeticiones;
    private Integer duracion;
    private EjercicioDTO ejercicio;
}
