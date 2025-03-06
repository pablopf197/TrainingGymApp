package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.RutinaPredefinidaRepository;
import es.uma.proyectotaw.dao.TipoentrenamientoRepository;
import es.uma.proyectotaw.dao.UsuarioRepository;
import es.uma.proyectotaw.dto.RutinaPredefinidaDTO;
import es.uma.proyectotaw.dto.SesionentrenamientoDTO;
import es.uma.proyectotaw.dto.UsuarioDTO;
import es.uma.proyectotaw.entity.RutinaPredefinidaEntity;
import es.uma.proyectotaw.entity.SesionentrenamientoEntity;
import es.uma.proyectotaw.entity.TipoentrenamientoEntity;
import es.uma.proyectotaw.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RutinaPredefinidaService extends DTOService<RutinaPredefinidaDTO, RutinaPredefinidaEntity>{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RutinaPredefinidaRepository rutinaPredefinidaRepository;

    @Autowired
    private TipoentrenamientoRepository tipoentrenamientoRepository;


    public List<RutinaPredefinidaDTO> findByUsuario(UsuarioDTO usuario) {
        UsuarioEntity user = this.usuarioRepository.findById(usuario.getId()).orElse(null);
        List<RutinaPredefinidaEntity> rutinas = this.rutinaPredefinidaRepository.findByUsuario(user);
        return this.entidadesADTO(rutinas);
    }


    public void save(RutinaPredefinidaDTO rutina) {
        RutinaPredefinidaEntity rutinaEntity = this.rutinaPredefinidaRepository.findById(rutina.getId()).orElse(null);
        rutinaEntity.setNombre(rutina.getNombre());
        rutinaEntity.setObjetivos(rutina.getObjetivos());
        this.rutinaPredefinidaRepository.save(rutinaEntity);

    }

    public RutinaPredefinidaDTO findById(Integer id) {
        RutinaPredefinidaEntity rutinaEntity = this.rutinaPredefinidaRepository.findById(id).orElse(null);
        return rutinaEntity.toDTO();
    }

    public void delete(RutinaPredefinidaDTO rutina) {
        RutinaPredefinidaEntity rutinaEntity = this.rutinaPredefinidaRepository.findById(rutina.getId()).orElse(null);
        this.rutinaPredefinidaRepository.delete(rutinaEntity);
    }

    public RutinaPredefinidaDTO saveNew(RutinaPredefinidaDTO rutina) {
        RutinaPredefinidaEntity r = new RutinaPredefinidaEntity();
        r.setNombre(rutina.getNombre());
        r.setObjetivos(rutina.getObjetivos());

        UsuarioEntity usuario = this.usuarioRepository.findById(rutina.getUsuario().getId()).orElse(null);
        r.setUsuario(usuario);
        TipoentrenamientoEntity t = this.tipoentrenamientoRepository.findById(rutina.getTipoentrenamiento().getId()).orElse(null);
        r.setTipoEntrenamiento(t);
        this.rutinaPredefinidaRepository.save(r);
        return r.toDTO();
    }
}
