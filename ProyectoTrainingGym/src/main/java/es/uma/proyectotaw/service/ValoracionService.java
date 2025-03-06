package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.RutinaAsignadaRepository;
import es.uma.proyectotaw.dao.SesionejercicioRepository;
import es.uma.proyectotaw.dao.UsuarioRepository;
import es.uma.proyectotaw.dao.ValoracionRepository;
import es.uma.proyectotaw.dto.*;
import es.uma.proyectotaw.entity.RutinaAsignadaEntity;
import es.uma.proyectotaw.entity.SesionejercicioEntity;
import es.uma.proyectotaw.entity.UsuarioEntity;
import es.uma.proyectotaw.entity.ValoracionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValoracionService extends DTOService<ValoracionDTO, ValoracionEntity>{

    @Autowired
    private ValoracionRepository valoracionRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RutinaAsignadaRepository rutinaAsignadaRepository;
    @Autowired
    private SesionejercicioRepository sesionejercicioRepository;

    public List<ValoracionDTO> buscarPorUsuarioYRutinaAsignadaOrdenadoPorSesionejercicio(int usuarioId, int rutinaAsignadaId){

        UsuarioEntity usuario = this.usuarioRepository.findById(usuarioId).get();
        RutinaAsignadaEntity rutinaAsignada = this.rutinaAsignadaRepository.findById(rutinaAsignadaId).get();

        List<ValoracionEntity> valoraciones = this.valoracionRepository.findByUsuarioAndRutinaAsignadaOrderBySesionejercicio(usuario, rutinaAsignada);

        return this.entidadesADTO(valoraciones);
    }

    public List<ValoracionDTO> buscarPorUsuarioYRutinaAsignadaYSesionejercicioDentro(int usuarioId, int rutinaAsignadaId, List<SesionejercicioDTO> sesionesEjercicioDTO){

        UsuarioEntity usuario = this.usuarioRepository.findById(usuarioId).get();
        RutinaAsignadaEntity rutinaAsignada = this.rutinaAsignadaRepository.findById(rutinaAsignadaId).get();

        List<SesionejercicioEntity> sesionesEjercicioEnt = new ArrayList<>();
        for(SesionejercicioDTO sesionesEjercicio: sesionesEjercicioDTO){

            sesionesEjercicioEnt.add(sesionejercicioRepository.findById(sesionesEjercicio.getId()).get());
        }
        List<ValoracionEntity> valoraciones = this.valoracionRepository.findByUsuarioAndRutinaAsignadaAndSesionejercicioIn(usuario,rutinaAsignada, sesionesEjercicioEnt);

        return this.entidadesADTO(valoraciones);
    }

    public void crearValoracion(ValoracionDTO valoracionDTO){
        UsuarioEntity usuario = this.usuarioRepository.findById(valoracionDTO.getUsuario().getId()).get();
        RutinaAsignadaEntity rutinaAsignada = this.rutinaAsignadaRepository.findById(valoracionDTO.getRutinaAsignada().getId()).get();
        SesionejercicioEntity sesionejercicio = this.sesionejercicioRepository.findById(valoracionDTO.getSesionejercicio().getId()).get();

        ValoracionEntity valoracion = new ValoracionEntity();
        valoracion.setPuntuacion(valoracionDTO.getPuntuacion());
        valoracion.setDescripcion(valoracionDTO.getDescripcion());
        valoracion.setUsuario(usuario);
        valoracion.setRutinaAsignada(rutinaAsignada);
        valoracion.setSesionejercicio(sesionejercicio);

        valoracionRepository.save(valoracion);
    }


    public List<ValoracionDTO> buscarPorRutinaAsignada(RutinaAsignadaDTO rutinaAsignadaDTO) {
        RutinaAsignadaEntity rutinaAsignada = this.rutinaAsignadaRepository.findById(rutinaAsignadaDTO.getId()).get();
        List<ValoracionEntity> valoraciones = this.valoracionRepository.findByRutinaAsignada(rutinaAsignada);
        return this.entidadesADTO(valoraciones);
    }

    public void delete(ValoracionDTO valoracion) {
        UsuarioEntity usuario = this.usuarioRepository.findById(valoracion.getUsuario().getId()).orElse(null);
        RutinaAsignadaEntity rutinaAsignada = this.rutinaAsignadaRepository.findById(valoracion.getRutinaAsignada().getId()).orElse(null);
        SesionejercicioEntity sesion = this.sesionejercicioRepository.findById(valoracion.getSesionejercicio().getId()).orElse(null);

        ValoracionEntity valoracionEntity = this.valoracionRepository.findByUsuarioAndRutinaAsignadaAndSesionejercicio(usuario, rutinaAsignada, sesion);
        this.valoracionRepository.delete(valoracionEntity);
    }

    public List<ValoracionDTO> buscarPorSesionEjercicio(SesionejercicioDTO sesionEjercicio) {
        SesionejercicioEntity sesion = this.sesionejercicioRepository.findById(sesionEjercicio.getId()).orElse(null);
        List<ValoracionEntity> valoraciones = this.valoracionRepository.findBySesionejercicio(sesion);
        return this.entidadesADTO(valoraciones);
    }

    public List<ValoracionDTO> buscarPorUsuario(UsuarioDTO usuario) {
        UsuarioEntity usuarioEntity = this.usuarioRepository.findById(usuario.getId()).orElse(null);
        List<ValoracionEntity> valoraciones = this.valoracionRepository.findByUsuario(usuarioEntity);
        return this.entidadesADTO(valoraciones);
    }

    public List<ValoracionDTO> buscarPorPuntuacionYUsuario(Integer puntuacion, UsuarioDTO usuario) {
        UsuarioEntity usuarioEntity = this.usuarioRepository.findById(usuario.getId()).orElse(null);
        List<ValoracionEntity> valoraciones = this.valoracionRepository.findByPuntuacionAndUsuario(puntuacion,usuarioEntity);
        return this.entidadesADTO(valoraciones);
    }
}
