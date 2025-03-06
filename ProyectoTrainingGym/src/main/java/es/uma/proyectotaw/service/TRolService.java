package es.uma.proyectotaw.service;


import es.uma.proyectotaw.dao.TrolRepository;
import es.uma.proyectotaw.dto.TrolDTO;
import es.uma.proyectotaw.entity.TrolEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TRolService extends DTOService<TrolDTO,TrolEntity>{

    @Autowired
    protected TrolRepository trolRepository;

    public TrolDTO buscarRolPorId(int id){
        TrolEntity trolEntity = this.trolRepository.findById(id).get();

        return trolEntity.toDTO();
    }

}
