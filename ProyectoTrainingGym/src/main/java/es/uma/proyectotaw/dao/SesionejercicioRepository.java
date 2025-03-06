package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.SesionejercicioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SesionejercicioRepository extends JpaRepository<SesionejercicioEntity, Integer> {
}
