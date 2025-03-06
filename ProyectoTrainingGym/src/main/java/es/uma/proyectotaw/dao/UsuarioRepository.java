package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{
    UsuarioEntity findByNombreUsuarioAndContraseña(String nombreUsuario, String contraseña);
    List<UsuarioEntity> findClientesByEntrenadorId(int id);

    @Query(value = "SELECT u.* FROM bdgym.usuario u JOIN bdgym.trol r ON u.trol_id = r.id WHERE r.rol = :rol", nativeQuery = true)
    List<UsuarioEntity> findUsuariosByRol(@Param("rol") String rol);

    @Query(value = "SELECT u.* FROM bdgym.usuario u JOIN bdgym.trol r ON u.trol_id = r.id JOIN bdgym.tipoentrenamiento t ON u.tipoentrenamiento_id = t.id" +
            " WHERE r.rol = 'cliente' AND u.entrenador_id IS NULL AND t.tipo = :tipoEntrenamiento", nativeQuery = true)
    List<UsuarioEntity> findUsuariosWithoutCoachByTipoEntrenamiento(@Param("tipoEntrenamiento") String tipoEntrenamiento);


    @Query(value = "SELECT u.* FROM bdgym.usuario u JOIN bdgym.trol r ON u.trol_id = r.id WHERE r.rol = :rol AND u.validado = :validado", nativeQuery = true)
    List<UsuarioEntity> findClientesByValidadoAAndRol(@Param("validado") byte validado, @Param("rol") String rol);

    @Query(value = "SELECT u.* FROM bdgym.usuario u JOIN bdgym.trol r ON u.trol_id = r.id JOIN bdgym.tipoentrenamiento t ON u.tipoentrenamiento_id = t.id" +
            " WHERE r.rol = :rol AND t.tipo = :tipoEntrenamiento", nativeQuery = true)
    List<UsuarioEntity> findUsuariosByRolAndTipoEntrenamiento(@Param("rol") String rol, @Param("tipoEntrenamiento") String tipoEntrenamiento);
}
