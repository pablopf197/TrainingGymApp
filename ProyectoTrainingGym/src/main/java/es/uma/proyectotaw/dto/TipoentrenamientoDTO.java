package es.uma.proyectotaw.dto;


import es.uma.proyectotaw.entity.TipoentrenamientoEnum;
import lombok.Data;

@Data
public class TipoentrenamientoDTO {
    private int id;
    private TipoentrenamientoEnum tipo;
}
