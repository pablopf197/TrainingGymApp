package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.TipoejerciciocrosstrainingRepository;
import es.uma.proyectotaw.dto.TipoejerciciocrosstrainingDTO;
import es.uma.proyectotaw.entity.TipoejerciciocrosstrainingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TipoejerciciocrosstrainingService extends DTOService<TipoejerciciocrosstrainingDTO, TipoejerciciocrosstrainingEntity>{

    @Autowired
    protected TipoejerciciocrosstrainingRepository tipoejerciciocrosstrainingRepository;

    public List<TipoejerciciocrosstrainingDTO> listarTipoejercicios () {
        List<TipoejerciciocrosstrainingEntity> entities = this.tipoejerciciocrosstrainingRepository.findAll();
        return this.entidadesADTO(entities);
    }
}
