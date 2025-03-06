package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.RutinaPredefinidaEntity;
import es.uma.proyectotaw.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RutinaPredefinidaRepository extends JpaRepository<RutinaPredefinidaEntity, Integer> {
    List<RutinaPredefinidaEntity> findByUsuario(UsuarioEntity usuario);

}
