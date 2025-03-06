package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.EjercicioRepository;
import es.uma.proyectotaw.dao.SesionejercicioRepository;
import es.uma.proyectotaw.dao.SesionentrenamientoHasSesionejercicioRepository;
import es.uma.proyectotaw.dao.SesionentrenamientoRepository;
import es.uma.proyectotaw.dto.SesionentrenamientoHasSesionejercicioDTO;
import es.uma.proyectotaw.entity.EjercicioEntity;
import es.uma.proyectotaw.entity.SesionejercicioEntity;
import es.uma.proyectotaw.entity.SesionentrenamientoEntity;
import es.uma.proyectotaw.entity.SesionentrenamientoHasSesionejercicioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SesionentrenamientoHasSesionejercicioService extends DTOService<SesionentrenamientoHasSesionejercicioDTO, SesionentrenamientoHasSesionejercicioEntity>{

    @Autowired
    private SesionentrenamientoHasSesionejercicioRepository sesionentrenamientoHasSesionejercicioRepository;
    @Autowired
    private SesionentrenamientoRepository sesionentrenamientoRepository;
    @Autowired
    private EjercicioRepository ejercicioRepository;
    @Autowired
    private SesionejercicioRepository sesionEjercicioRepository;

    public List<SesionentrenamientoHasSesionejercicioDTO> buscarPorSesionentrenamientoOrdenadoPorPosicion(int SesionentreamientoId){
        SesionentrenamientoEntity sesionentrenamiento = sesionentrenamientoRepository.findById(SesionentreamientoId).get();

        List<SesionentrenamientoHasSesionejercicioEntity> sesionesHasSesionesEjercicios = sesionentrenamientoHasSesionejercicioRepository.findBySesionentrenamientoOrderByPosicion(sesionentrenamiento);

        return this.entidadesADTO(sesionesHasSesionesEjercicios);
    }

    public void delete(SesionentrenamientoHasSesionejercicioDTO sesionHasSesion) {
        SesionentrenamientoEntity sesionentrenamientoEntity = this.sesionentrenamientoRepository.findById(sesionHasSesion.getSesionejercicio().getId()).orElse(null);
        this.sesionentrenamientoHasSesionejercicioRepository.deleteBySesionentrenamiento(sesionentrenamientoEntity);
    }

    public void save(SesionentrenamientoHasSesionejercicioDTO sesionHasSesion) {
        SesionejercicioEntity sesionejercicio = this.sesionEjercicioRepository.findById(sesionHasSesion.getSesionejercicio().getId()).orElse(null);
        SesionentrenamientoEntity sesionEntrenamiento = this.sesionentrenamientoRepository.findById(sesionHasSesion.getSesionentrenamiento().getId()).orElse(null);
        SesionentrenamientoHasSesionejercicioEntity s = this.sesionentrenamientoHasSesionejercicioRepository.findBySesionejercicioAndAndSesionentrenamiento(sesionejercicio,sesionEntrenamiento);
        s.getSesionejercicio().setSeries(sesionHasSesion.getSesionejercicio().getSeries());
        s.getSesionejercicio().setRepeticiones(sesionHasSesion.getSesionejercicio().getRepeticiones());
        s.getSesionejercicio().setDuracion(sesionHasSesion.getSesionejercicio().getDuracion());
        s.setPosicion(sesionHasSesion.getPosicion());
        this.sesionentrenamientoHasSesionejercicioRepository.save(s);
    }

    public void saveNew(SesionentrenamientoHasSesionejercicioDTO sesionHasSesion) {
        SesionentrenamientoHasSesionejercicioEntity s = new SesionentrenamientoHasSesionejercicioEntity();
        SesionentrenamientoEntity sesionentrenamientoEntity = this.sesionentrenamientoRepository.findById(sesionHasSesion.getSesionentrenamiento().getId()).orElse(null);
        SesionejercicioEntity sesionejercicioEntity = this.sesionEjercicioRepository.findById(sesionHasSesion.getSesionejercicio().getId()).orElse(null);
        s.setSesionejercicio(sesionejercicioEntity);
        s.setPosicion(sesionHasSesion.getPosicion());
        s.setSesionentrenamiento(sesionentrenamientoEntity);
        this.sesionentrenamientoHasSesionejercicioRepository.save(s);

    }
}
