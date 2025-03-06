package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValoracionRepository extends JpaRepository<ValoracionEntity, ValoracionEntityPK> {
    List<ValoracionEntity> findByUsuarioAndRutinaAsignadaAndSesionejercicioIn(UsuarioEntity usuario, RutinaAsignadaEntity rutinaAsignada, List<SesionejercicioEntity> sesionesEjercicio);

    List<ValoracionEntity> findByUsuarioAndRutinaAsignadaOrderBySesionejercicio(UsuarioEntity cliente, RutinaAsignadaEntity rutinaAsignada);

    List<ValoracionEntity> findBySesionejercicio(SesionejercicioEntity sesionEjercicio);

    List<ValoracionEntity> findByRutinaAsignada(RutinaAsignadaEntity rutinaAsignada);

    ValoracionEntity findByUsuarioAndRutinaAsignadaAndSesionejercicio(UsuarioEntity usuario, RutinaAsignadaEntity rutinaAsignada, SesionejercicioEntity sesion);

    List<ValoracionEntity> findByPuntuacionAndUsuario(Integer puntuacion, UsuarioEntity usuario);

    List<ValoracionEntity> findByUsuario(UsuarioEntity usuario);
}
