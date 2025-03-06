package es.uma.proyectotaw.service;
import es.uma.proyectotaw.dao.RutinaAsignadaRepository;
import es.uma.proyectotaw.dao.RutinaPredefinidaRepository;
import es.uma.proyectotaw.dao.UsuarioRepository;
import es.uma.proyectotaw.dto.RutinaAsignadaDTO;
import es.uma.proyectotaw.dto.RutinaPredefinidaDTO;
import es.uma.proyectotaw.dto.UsuarioDTO;
import es.uma.proyectotaw.entity.RutinaAsignadaEntity;
import es.uma.proyectotaw.entity.RutinaPredefinidaEntity;
import es.uma.proyectotaw.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class RutinaAsignadaService extends DTOService<RutinaAsignadaDTO, RutinaAsignadaEntity> {

    @Autowired
    RutinaAsignadaRepository rutinaAsignadaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RutinaPredefinidaRepository rutinaPredefinidaRepository;
    
    public List<RutinaAsignadaDTO> findByRutinaPredefinida(RutinaPredefinidaDTO rutina) {
        RutinaPredefinidaEntity r = this.rutinaPredefinidaRepository.findById(rutina.getId()).orElse(null);
        return this.entidadesADTO(this.rutinaAsignadaRepository.findByRutinaPredefinida(r)) ;
    }

    public void delete(RutinaAsignadaDTO rutinaAsignada) {
        RutinaAsignadaEntity r = this.rutinaAsignadaRepository.findById(rutinaAsignada.getId()).orElse(null);
        this.rutinaAsignadaRepository.delete(r);
    }
    public RutinaAsignadaDTO buscarPorId(int id) {
        RutinaAsignadaEntity rutinaAsignada = this.rutinaAsignadaRepository.findById(id).orElse(null);
        return rutinaAsignada.toDTO();
    }

    public RutinaAsignadaDTO buscarPorUsuarioYFecha(UsuarioDTO usuarioDTO, Date fecha){
        UsuarioEntity usuario = this.usuarioRepository.findById(usuarioDTO.getId()).get();
        RutinaAsignadaEntity rutinaAsignada = this.rutinaAsignadaRepository.findByUsuarioAndFecha(usuario, fecha);
        if(rutinaAsignada == null){
            return null;
        }
        return rutinaAsignada.toDTO();
    }

    public void save(RutinaAsignadaDTO rutinaAsignada) {
        RutinaAsignadaEntity rutinaAsignadaEntity = new RutinaAsignadaEntity();
        RutinaPredefinidaEntity rutinaPredefinidaEntity = this.rutinaPredefinidaRepository.findById(rutinaAsignada.getRutinaPredefinida().getId()).orElse(null);
        rutinaAsignadaEntity.setRutinaPredefinida(rutinaPredefinidaEntity);
        rutinaAsignadaEntity.setFecha(rutinaAsignada.getFecha());
        UsuarioEntity user = this.usuarioRepository.findById(rutinaAsignada.getUsuario().getId()).orElse(null);
        rutinaAsignadaEntity.setUsuario(user);
        rutinaAsignadaRepository.save(rutinaAsignadaEntity);

    }
}