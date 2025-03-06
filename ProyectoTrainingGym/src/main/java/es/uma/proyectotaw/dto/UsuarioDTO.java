package es.uma.proyectotaw.dto;

import es.uma.proyectotaw.entity.TipoentrenamientoEntity;
import es.uma.proyectotaw.entity.TrolEntity;
import es.uma.proyectotaw.entity.UsuarioEntity;
import lombok.Data;

import java.sql.Date;


@Data
public class UsuarioDTO {
    private int id;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private String dni;
    private String genero;
    private String correo;
    private String telefono;
    private Date fechaIngreso;
    private String nombreUsuario;
    private String contraseña;
    private byte validado;
    private TipoentrenamientoDTO tipoEntrenamiento;
    private TrolDTO rol;
    private UsuarioDTO entrenador;
}
