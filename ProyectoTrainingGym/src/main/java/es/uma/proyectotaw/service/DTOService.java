package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dto.DTO;

import java.util.ArrayList;
import java.util.List;

public abstract class DTOService<DTOClass, EntityClass> {

    protected List<DTOClass> entidadesADTO (List<EntityClass> entidades) {
        List<DTOClass> lista = new ArrayList<>();
        for (EntityClass entidad : entidades) {
            DTO<DTOClass> clase = (DTO<DTOClass>) entidad;
            lista.add(clase.toDTO());
        }
        return lista;
    }

}
