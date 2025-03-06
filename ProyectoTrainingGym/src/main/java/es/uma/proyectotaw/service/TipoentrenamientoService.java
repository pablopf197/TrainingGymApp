package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.TipoentrenamientoRepository;
import es.uma.proyectotaw.dto.TipoentrenamientoDTO;
import es.uma.proyectotaw.entity.TipoentrenamientoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoentrenamientoService extends DTOService<TipoentrenamientoDTO, TipoentrenamientoEntity>{

    @Autowired
    protected TipoentrenamientoRepository tipoentrenamientoRepository;

    public List<TipoentrenamientoDTO> listarTipoentrenamientos () {
        List<TipoentrenamientoEntity> entities = this.tipoentrenamientoRepository.findAll();
        return this.entidadesADTO(entities);
    }

    public TipoentrenamientoDTO crearTipoentrenamiento (TipoentrenamientoDTO tipoentrenamientoDTO) {
        TipoentrenamientoEntity entity = new TipoentrenamientoEntity();
        entity.setTipo(tipoentrenamientoDTO.getTipo());
        this.tipoentrenamientoRepository.save(entity);
        return entity.toDTO();
    }
}
