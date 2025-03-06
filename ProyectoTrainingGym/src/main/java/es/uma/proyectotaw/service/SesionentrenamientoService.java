package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.SesionentrenamientoRepository;
import es.uma.proyectotaw.dao.UsuarioRepository;
import es.uma.proyectotaw.dto.SesionentrenamientoDTO;
import es.uma.proyectotaw.dto.UsuarioDTO;
import es.uma.proyectotaw.entity.SesionejercicioEntity;
import es.uma.proyectotaw.entity.SesionentrenamientoEntity;
import es.uma.proyectotaw.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SesionentrenamientoService extends DTOService<SesionentrenamientoDTO, SesionentrenamientoEntity>{

    @Autowired
    private SesionentrenamientoRepository sesionentrenamientoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<SesionentrenamientoDTO> findByUsuario(UsuarioDTO usuario) {
        UsuarioEntity user = this.usuarioRepository.findById(usuario.getId()).orElse(null);
        return this.entidadesADTO(this.sesionentrenamientoRepository.findByUsuario(user));
    }

    public SesionentrenamientoDTO buscarPorId(int id) {
        SesionentrenamientoEntity sesion = this.sesionentrenamientoRepository.findById(id).orElse(null);
        return sesion.toDTO();
    }

    public SesionentrenamientoDTO saveNew(SesionentrenamientoDTO sesion) {
        SesionentrenamientoEntity sesionEntity = new SesionentrenamientoEntity();
        UsuarioEntity usuario = this.usuarioRepository.findById(sesion.getUsuario().getId()).orElse(null);
        sesionEntity.setUsuario(usuario);
        this.sesionentrenamientoRepository.save(sesionEntity);
        return sesionEntity.toDTO();
    }

    public void delete(SesionentrenamientoDTO sesionentrenamientoDTO) {
        SesionentrenamientoEntity sesion = this.sesionentrenamientoRepository.findById(sesionentrenamientoDTO.getId()).orElse(null);
        this.sesionentrenamientoRepository.delete(sesion);
    }

    public void save(SesionentrenamientoDTO sesion) {
        SesionentrenamientoEntity sesionEntity = this.sesionentrenamientoRepository.findById(sesion.getId()).orElse(null);
        UsuarioEntity usuario = this.usuarioRepository.findById(sesion.getUsuario().getId()).orElse(null);
        sesionEntity.setUsuario(usuario);
        sesionEntity.setNombre(sesion.getNombre());
        sesionEntity.setDescripcion(sesion.getDescripcion());
        this.sesionentrenamientoRepository.save(sesionEntity);
    }
}
