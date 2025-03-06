package es.uma.proyectotaw.controller;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.uma.proyectotaw.dto.ClienteDTO;
import es.uma.proyectotaw.dto.TipoentrenamientoDTO;
import es.uma.proyectotaw.dto.UsuarioDTO;
import es.uma.proyectotaw.entity.RolEnum;
import es.uma.proyectotaw.entity.TipoentrenamientoEnum;
import es.uma.proyectotaw.service.ClienteService;
import es.uma.proyectotaw.service.TRolService;
import es.uma.proyectotaw.service.TipoentrenamientoService;
import es.uma.proyectotaw.service.UsuarioService;
import es.uma.proyectotaw.ui.Usuario;
import jakarta.servlet.http.HttpSession;

@Controller
public class GymController extends BaseController{

    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    protected ClienteService clienteService;

    @Autowired
    private TRolService trolService;

    @Autowired
    private TipoentrenamientoService tipoentrenamientoService;

    @GetMapping("/")
    public String redirectToInicio(Model model) {
        return "redirect:/inicio";
    }
    @GetMapping("/inicio")
    public String doInicio(Model model) {
        return "inicio";
    }
    @GetMapping("/trabaja")
    public String doTrabaja(Model model) {
        List<TipoentrenamientoDTO> tiposEntrenamientos = tipoentrenamientoService.listarTipoentrenamientos();
        model.addAttribute("tiposEntrenamientos", tiposEntrenamientos);
        return "trabaja";
    }
    @GetMapping("/acceso")
    public String doAcceso(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "acceso";
    }
    @GetMapping("/registro")
    public String doRegistro(Model model) {

        return "registro";
    }

    @PostMapping("/registerEntrenador")
    public String registerEntrenador(@RequestParam(value = "nombre", required = false) String nombre, @RequestParam("apellidos") String apellidos,
                           @RequestParam("Fecha_nacimiento") String FechaNac, @RequestParam("DNI") String DNI,
                           @RequestParam("eMail") String eMail, @RequestParam("telefono") String telefono, @RequestParam("sexo") String sexo,
                           @RequestParam("tipoentrenamiento") TipoentrenamientoEnum tipoEntrenamiento,
                           @RequestParam("usuario") String nombre_usuario, @RequestParam("contraseña") String contraseña, Model model) throws ParseException {
        try{
            UsuarioDTO usuario = new UsuarioDTO();
            usuario.setNombre(nombre);
            usuario.setApellidos(apellidos);
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(FechaNac, formato);
            Date fechaSql = Date.valueOf(fecha);
            usuario.setFechaNacimiento(fechaSql);
            usuario.setDni(DNI);
            usuario.setCorreo(eMail);
            usuario.setTelefono(telefono);
            usuario.setNombreUsuario(nombre_usuario);
            usuario.setContraseña(contraseña);
            usuario.setGenero(sexo);
            usuario.setRol(trolService.buscarRolPorId(3));

            TipoentrenamientoDTO tipoent = new TipoentrenamientoDTO();
            tipoent.setTipo(tipoEntrenamiento);
            tipoent = tipoentrenamientoService.crearTipoentrenamiento(tipoent);
            usuario.setTipoEntrenamiento(tipoent);
            usuarioService.crearUsuario(usuario);
            return "redirect:/";
        }catch (Exception e) {

            return "errorRegistro";
        }
    }


    @PostMapping("/login")
    public String login(@ModelAttribute("usuario") Usuario user,
                        Model model, HttpSession session) {
        try{
            UsuarioDTO usuario = usuarioService.buscarUsuarioPorUsuarioYContraseña(user.getUser(),user.getPassword());
            if (usuario != null) {
                RolEnum rol = usuario.getRol().getRol();
                session.setAttribute("usuario", usuario);
                if(rol == RolEnum.admin) {
                    return "redirect:/adminMain";
                }else if(rol == RolEnum.entrenador) {
                    return "redirect:/entrenadorMain";
                }else if(rol == RolEnum.cliente){
                    ClienteDTO cliente = clienteService.buscarCliente(usuario.getId());
                    session.setAttribute("cliente", cliente);
                    return "redirect:/clienteMain";
                }
            }
        }catch (Exception e) {
            model.addAttribute("error", "Nombre de usuario o contraseña incorrectos");
            return "acceso"; // Reenvía al mismo formulario de inicio de sesión
        }
        return "";
    }


    @PostMapping("/register")
    public String register(@RequestParam(value = "nombre", required = false) String nombre, @RequestParam("apellidos") String apellidos,
                           @RequestParam("Fecha_nacimiento") String FechaNac, @RequestParam("DNI") String DNI,
                           @RequestParam("eMail") String eMail, @RequestParam("telefono") String telefono, @RequestParam("altura") String altura,
                           @RequestParam("peso") String peso, @RequestParam("sexo") String sexo,
                           @RequestParam("tipoEntrenamiento") TipoentrenamientoEnum tipoEntrenamiento, @RequestParam("objetivos") String objetivos,
                           @RequestParam("usuario") String nombre_usuario, @RequestParam("contraseña") String contraseña, Model model) throws ParseException {

        try {
            UsuarioDTO usuario = new UsuarioDTO();
            usuario.setNombre(nombre);
            usuario.setApellidos(apellidos);
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(FechaNac, formato);
            Date fechaSql = Date.valueOf(fecha);
            usuario.setFechaNacimiento(fechaSql);
            usuario.setDni(DNI);
            usuario.setCorreo(eMail);
            usuario.setTelefono(telefono);
            usuario.setNombreUsuario(nombre_usuario);
            usuario.setContraseña(contraseña);
            usuario.setGenero(sexo);
            usuario.setRol(trolService.buscarRolPorId(2));

            TipoentrenamientoDTO tipoent = new TipoentrenamientoDTO();

            tipoent.setTipo(tipoEntrenamiento);
            tipoent = tipoentrenamientoService.crearTipoentrenamiento(tipoent);
            usuario.setTipoEntrenamiento(tipoent);

            ClienteDTO cliente = new ClienteDTO();
            cliente.setAltura(Float.parseFloat(altura));
            cliente.setPeso(Float.parseFloat(peso));
            cliente.setObjetivos(objetivos);
            cliente.setUsuario(usuario);
            clienteService.crearCliente(cliente, usuario);
            return "redirect:/";
        }catch (Exception e) {

            return "errorRegistro";
        }

    }


    @GetMapping("/adminMain")
    public String doAdminMain() {
        // Aquí puedes agregar lógica para obtener datos necesarios para la vista de administrador
        return "admin/mainAdmin"; // Retorna el nombre de la vista de administrador
    }





}
