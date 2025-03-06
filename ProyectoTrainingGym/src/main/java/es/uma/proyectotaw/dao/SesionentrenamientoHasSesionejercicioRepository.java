package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.SesionejercicioEntity;
import es.uma.proyectotaw.entity.SesionentrenamientoEntity;
import es.uma.proyectotaw.entity.SesionentrenamientoHasSesionejercicioEntity;
import es.uma.proyectotaw.entity.SesionentrenamientoHasSesionejercicioEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SesionentrenamientoHasSesionejercicioRepository extends JpaRepository<SesionentrenamientoHasSesionejercicioEntity, SesionentrenamientoHasSesionejercicioEntityPK> {
    List<SesionentrenamientoHasSesionejercicioEntity> findBySesionentrenamientoOrderByPosicion(SesionentrenamientoEntity sesionEntrenamiento);

    void deleteBySesionentrenamiento(SesionentrenamientoEntity sesionentrenamiento);

    SesionentrenamientoHasSesionejercicioEntity findBySesionejercicioAndAndSesionentrenamiento(SesionejercicioEntity sesionejercicioEntity, SesionentrenamientoEntity sesionentrenamientoEntity);
}
