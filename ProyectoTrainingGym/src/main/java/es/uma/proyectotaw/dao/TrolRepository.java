package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.TrolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrolRepository extends JpaRepository<TrolEntity, Integer>{

}