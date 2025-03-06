package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.EjercicioRepository;
import es.uma.proyectotaw.dao.SesionejercicioRepository;
import es.uma.proyectotaw.dto.SesionejercicioDTO;
import es.uma.proyectotaw.entity.EjercicioEntity;
import es.uma.proyectotaw.entity.SesionejercicioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SesionEjercicioService {

    @Autowired
    SesionejercicioRepository sesionejercicioRepository;

    @Autowired
    EjercicioRepository ejercicioRepository;

    public SesionejercicioDTO buscarPorId(Integer ejercicioId) {
        SesionejercicioEntity sesionejercicio = sesionejercicioRepository.findById(ejercicioId).orElse(null);
        if (sesionejercicio == null){
            return null;
        }
        return sesionejercicio.toDTO();
    }

    public void delete(SesionejercicioDTO sesionEjercicio) {
        SesionejercicioEntity sesionejercicioEntity = sesionejercicioRepository.findById(sesionEjercicio.getId()).orElse(null);
        this.sesionejercicioRepository.delete(sesionejercicioEntity);
    }

    public SesionejercicioDTO saveNew(SesionejercicioDTO sesionEjercicio) {
        SesionejercicioEntity sesionejercicioEntity = new SesionejercicioEntity();
        EjercicioEntity ejercicio = this.ejercicioRepository.findById(sesionEjercicio.getEjercicio().getId()).orElse(null);
        sesionejercicioEntity.setEjercicio(ejercicio);
        sesionejercicioEntity.setSeries(sesionEjercicio.getSeries());
        sesionejercicioEntity.setRepeticiones(sesionEjercicio.getRepeticiones());
        sesionejercicioEntity.setDuracion(sesionEjercicio.getDuracion());
        this.sesionejercicioRepository.save(sesionejercicioEntity);
        return sesionejercicioEntity.toDTO();
    }
}
