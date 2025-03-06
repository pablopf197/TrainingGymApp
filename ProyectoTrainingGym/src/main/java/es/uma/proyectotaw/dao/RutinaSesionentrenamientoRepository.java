package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.RutinaPredefinidaEntity;
import es.uma.proyectotaw.entity.RutinaSesionentrenamientoEntity;
import es.uma.proyectotaw.entity.RutinaSesionentrenamientoEntityPK;
import es.uma.proyectotaw.entity.SesionentrenamientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RutinaSesionentrenamientoRepository extends JpaRepository<RutinaSesionentrenamientoEntity, RutinaSesionentrenamientoEntityPK> {

    List<RutinaSesionentrenamientoEntity> findByRutinaPredefinidaOrderByPosicion(RutinaPredefinidaEntity rutina);

    List<RutinaSesionentrenamientoEntity> findBySesionentrenamientoOrderByPosicion(SesionentrenamientoEntity sesion);

    RutinaSesionentrenamientoEntity findBySesionentrenamientoAndRutinaPredefinida(SesionentrenamientoEntity sesion, RutinaPredefinidaEntity rutina);
}
