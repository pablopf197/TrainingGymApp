package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.TipoejerciciobodubuildingRepository;
import es.uma.proyectotaw.dto.TipoejerciciobodybuildingDTO;
import es.uma.proyectotaw.entity.TipoejerciciobodybuildingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TipoejerciciobodybuildingService extends DTOService<TipoejerciciobodybuildingDTO, TipoejerciciobodybuildingEntity> {

    @Autowired
    protected TipoejerciciobodubuildingRepository tipoejerciciobodybuildingRepository;

    public List<TipoejerciciobodybuildingDTO> listarTipoejercicios () {
        List<TipoejerciciobodybuildingEntity> entities = this.tipoejerciciobodybuildingRepository.findAll();
        return this.entidadesADTO(entities);
    }
}
