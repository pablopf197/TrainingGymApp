package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.RutinaAsignadaEntity;
import es.uma.proyectotaw.entity.RutinaPredefinidaEntity;
import es.uma.proyectotaw.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface RutinaAsignadaRepository extends JpaRepository<RutinaAsignadaEntity, Integer>{
    RutinaAsignadaEntity findByUsuarioAndFecha(UsuarioEntity cliente, Date lunesDeEstaSemana);
    List<RutinaAsignadaEntity> findByRutinaPredefinida(RutinaPredefinidaEntity rutinaPredefinidaEntity);
}
