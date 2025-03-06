package es.uma.proyectotaw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.uma.proyectotaw.dto.ClienteDTO;
import es.uma.proyectotaw.dto.EjercicioDTO;
import es.uma.proyectotaw.dto.TipoejerciciobodybuildingDTO;
import es.uma.proyectotaw.dto.TipoejerciciocrosstrainingDTO;
import es.uma.proyectotaw.dto.TipoentrenamientoDTO;
import es.uma.proyectotaw.dto.UsuarioDTO;
import es.uma.proyectotaw.service.ClienteService;
import es.uma.proyectotaw.service.EjercicioService;
import es.uma.proyectotaw.service.TipoejerciciobodybuildingService;
import es.uma.proyectotaw.service.TipoejerciciocrosstrainingService;
import es.uma.proyectotaw.service.TipoentrenamientoService;
import es.uma.proyectotaw.service.UsuarioService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController extends BaseController{

    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    protected ClienteService clienteService;

    @Autowired
    protected EjercicioService ejercicioService;

    @Autowired
    protected TipoentrenamientoService tipoentrenamientoService;

    @Autowired
    protected TipoejerciciocrosstrainingService tipoejerciciocrosstrainingService;

    @Autowired
    protected TipoejerciciobodybuildingService tipoejerciciobodybuildingService;

    @GetMapping("/adminMain/inicio")
    public String doAdminMain() {
        return "admin/mainAdmin";
    }

    // RAMA DE LISTA DE ENTRENADORES //////////////////////////////////////////////////////////////////

    @GetMapping("/adminMain/entrenadores")
    public String doEntrenadores(HttpSession session, Model model) {
        if(!estaAutenticado(session)) return "redirect:/acceso";

        List<UsuarioDTO> entrenadoresBodyBuilding = usuarioService.listarEntrenadoresBodyBuilding();
        List<UsuarioDTO> entrenadoresCrossTraining = usuarioService.listarEntrenadoresCrossTraining();

        model.addAttribute("entrenadoresBodyBuilding", entrenadoresBodyBuilding);
        model.addAttribute("entrenadoresCrossTraining", entrenadoresCrossTraining);

        return "admin/listaEntrenadores";
    }

    @GetMapping("/adminMain/borrarEntrenador/{id}")
    public String borrarEntrenador(@PathVariable("id") int id){
        usuarioService.borrarEntrenador(id);
        return "redirect:/adminMain/entrenadores";
    }

    @GetMapping("/adminMain/clientesEntrenador/{id}")
    public String doClientesEntrenador(@PathVariable("id") int id, Model model, HttpSession session) {
        if(!estaAutenticado(session)) return "redirect:/acceso";

        List<UsuarioDTO> listaClientes = usuarioService.buscarClientesPorEntrenador(id);
        UsuarioDTO entrenador = usuarioService.buscarUsuario(id);

        model.addAttribute("entrenador", entrenador);
        model.addAttribute("listaClientes", listaClientes);

        return "admin/clientesAsignadosEntrenador";
    }

    @GetMapping("/adminMain/entrenador/desasignarCliente/{id}")
    public String desasignarCliente(@PathVariable("id") int id, HttpSession session){
        if(!estaAutenticado(session)) return "redirect:/acceso";

        int idEntrenador = usuarioService.buscarEntrenadorDeCliente(id).getId();
        usuarioService.desasignarEntrenadorACliente(id);

        return "redirect:/adminMain/clientesEntrenador/"+idEntrenador;
    }

    @PostMapping("/adminMain/filtrar/clientesAsignados")
    public String filtrarClientesAsignados(@RequestParam("idEntrenador") int idEntrenador, @RequestParam("filtro") String filtro, Model model, HttpSession session){
        if(!estaAutenticado(session)) return "redirect:/acceso";

        UsuarioDTO entrenador = usuarioService.buscarUsuario(idEntrenador);
        // si la lista filtrada está vacía, se mostrará la lista completa
        List<UsuarioDTO> listaFiltrada = usuarioService.filtrarClientesAsignadosEntrenador(idEntrenador, filtro);

        model.addAttribute("entrenador", entrenador);
        model.addAttribute("listaClientes", listaFiltrada);

        return "admin/clientesAsignadosEntrenador";
    }

    @GetMapping("/adminMain/nuevosClientesEntrenador/{id}")
    public String listaClientesSinAsignar(@PathVariable("id") int id, Model model, HttpSession session) {
        if(!estaAutenticado(session)) return "redirect:/acceso";

        UsuarioDTO entrenador = usuarioService.buscarUsuario(id);
        List<UsuarioDTO> listaClientes = usuarioService.buscarClientesSinEntrenadorPorTipoEntrenamiento(entrenador.getTipoEntrenamiento().getTipo().name());

        model.addAttribute("entrenador", entrenador);
        model.addAttribute("listaClientes", listaClientes);

        return "admin/clientesSinEntrenador";
    }

    @GetMapping("/adminMain/asignarClienteEntrenador")
    public String asignarClienteEntrenador(@RequestParam("idEntrenador") int idEntrenador, @RequestParam("idCliente") int idCliente, HttpSession session){
        if(!estaAutenticado(session)) return "redirect:/acceso";

        UsuarioDTO entrenador = usuarioService.buscarUsuario(idEntrenador);
        usuarioService.asignarEntrenadorACliente(idEntrenador, idCliente);

        return "redirect:/adminMain/clientesEntrenador/"+entrenador.getId();
    }

    @PostMapping("/adminMain/filtrar/clientesSinAsignar")
    public String filtrarClientesSinAsignar(@RequestParam("idEntrenador") int idEntrenador, @RequestParam("filtro") String filtro, Model model, HttpSession session){
        if(!estaAutenticado(session)) return "redirect:/acceso";

        UsuarioDTO entrenador = usuarioService.buscarUsuario(idEntrenador);
        List<UsuarioDTO> listaFiltrada = usuarioService.filtrarClientesSinEntrenadorPorTipoEntrenamiento(entrenador.getTipoEntrenamiento().getTipo().name(), filtro);

        model.addAttribute("entrenador", entrenador);
        model.addAttribute("listaClientes", listaFiltrada);
        return "admin/clientesSinEntrenador";
    }

    // RAMA DE LISTA DE CLIENTES //////////////////////////////////////////////////////////////////////

    @GetMapping("/adminMain/clientes")
    public String doClientes(HttpSession session, Model model) {
        if(!estaAutenticado(session)) return "redirect:/acceso";

        List<UsuarioDTO> listaClientes = usuarioService.listarUsuariosPorRol("cliente");
        List<UsuarioDTO> entrenadoresBodyBuilding = usuarioService.listarEntrenadoresBodyBuilding();
        System.out.println(entrenadoresBodyBuilding);
        List<UsuarioDTO> entrenadoresCrossTraining = usuarioService.listarEntrenadoresCrossTraining();
        System.out.println(entrenadoresCrossTraining);

        model.addAttribute("entrenadoresBodyBuilding", entrenadoresBodyBuilding);
        model.addAttribute("entrenadoresCrossTraining", entrenadoresCrossTraining);
        model.addAttribute("listaClientes", listaClientes);
        return "admin/listaClientes";
    }

    @PostMapping("/adminMain/editarCliente")
    public String editarCliente(@RequestParam("idEntrenador") int idEntrenador, @RequestParam("idCliente") int idCliente, HttpSession session){
        if(!estaAutenticado(session)) return "redirect:/acceso";

        usuarioService.asignarEntrenadorACliente(idEntrenador, idCliente);

        return "redirect:/adminMain/clientes";
    }

    @GetMapping("/adminMain/cliente/borrar/{id}")
    public String borrarCliente(@PathVariable("id") int id){
        usuarioService.borrarCliente(id);
        return "redirect:/adminMain/clientes";
    }

    @PostMapping("/adminMain/filtrar/clientes")
    public String filtrarClientesAsignados(@RequestParam("filtro") String filtro, Model model, HttpSession session){
        if(!estaAutenticado(session)) return "redirect:/acceso";

        List<UsuarioDTO> entrenadoresBodyBuilding = usuarioService.listarEntrenadoresBodyBuilding();
        List<UsuarioDTO> entrenadoresCrossTraining = usuarioService.listarEntrenadoresCrossTraining();
        List<UsuarioDTO> listaFiltrada = usuarioService.filtrarClientes(filtro);

        model.addAttribute("entrenadoresBodyBuilding", entrenadoresBodyBuilding);
        model.addAttribute("entrenadoresCrossTraining", entrenadoresCrossTraining);
        model.addAttribute("listaClientes", listaFiltrada);
        return "admin/listaClientes";
    }

    // RAMA DE LISTA DE SOLICITUDES /////////////////////////////////////////////////////////////////

    @GetMapping("/adminMain/solicitudes")
    public String doSolicitudes(HttpSession session, Model model) {
        if(!estaAutenticado(session)) return "redirect:/acceso";

        List<UsuarioDTO> listaEntrenadores = usuarioService.buscarUsuarioPorRolYValidado("entrenador", (byte) 0);
        List<UsuarioDTO> listaClientes = usuarioService.buscarUsuarioPorRolYValidado("cliente", (byte) 0);

        model.addAttribute("listaEntrenadores", listaEntrenadores);
        model.addAttribute("listaClientes", listaClientes);
        return "admin/listaSolicitudes";
    }

    @GetMapping("/adminMain/solicitud/aceptar/{id}")
    public String aceptarSolicitud(@PathVariable("id") int id){
        usuarioService.aceptarSolicitud(id);
        return "redirect:/adminMain/solicitudes";
    }

    @GetMapping("/adminMain/solicitud/rechazar/{id}")
    public String rechazarSolicitud(@PathVariable("id") int id){
        usuarioService.borrarUsuario(id);
        return "redirect:/adminMain/solicitudes";
    }

    @GetMapping("/adminMain/solicitud/entrenador/{id}")
    public String doSolicitudEntrenador(@PathVariable("id") int id, Model model, HttpSession session) {
        if(!estaAutenticado(session)) return "redirect:/acceso";

        UsuarioDTO usuario = usuarioService.buscarUsuario(id);

        model.addAttribute("usuario", usuario);
        return "admin/solicitudEntrenador";
    }

    @GetMapping("/adminMain/solicitud/cliente/{id}")
    public String doSolicitudCliente(@PathVariable("id") int id, Model model, HttpSession session) {
        if(!estaAutenticado(session)) return "redirect:/acceso";

        UsuarioDTO usuario = usuarioService.buscarUsuario(id);
        ClienteDTO cliente = clienteService.buscarCliente(id);

        model.addAttribute("usuario", usuario);
        model.addAttribute("cliente", cliente);
        return "admin/solicitudCliente";
    }


    // RAMA DE LISTA DE EJERCICIOS /////////////////////////////////////////////////////////////////


    @GetMapping("/adminMain/ejercicios")
    public String doEjercicios(HttpSession session, Model model) {
        if(!estaAutenticado(session)) return "redirect:/acceso";

        List<EjercicioDTO> listaEjercicios = ejercicioService.listarEjercicios();

        model.addAttribute("listaEjercicios", listaEjercicios);
        return "admin/listaEjercicios";
    }

    @PostMapping("/adminMain/filtrar/ejercicios")
    public String filtrarEjercicios(@RequestParam("filtro") String filtro, Model model, HttpSession session){
        if(!estaAutenticado(session)) return "redirect:/acceso";

        List<EjercicioDTO> listaFiltrada = ejercicioService.filtrarEjercicios(filtro);

        model.addAttribute("listaEjercicios", listaFiltrada);
        return "admin/listaEjercicios";
    }

    @GetMapping("/adminMain/borrarEjercicio/{id}")
    public String borrarEjercicio(@PathVariable("id") int id){
        ejercicioService.borrarEjercicio(id);
        return "redirect:/adminMain/ejercicios";
    }

    @GetMapping("/adminMain/nuevoEjercicio")
    public String doNuevoEjercicio(HttpSession session, Model model) {
        if(!estaAutenticado(session)) return "redirect:/acceso";

        List<TipoentrenamientoDTO> listaTiposEntrenamiento = tipoentrenamientoService.listarTipoentrenamientos();
        List<TipoejerciciobodybuildingDTO> listaTiposEjercicioBodyBuilding = tipoejerciciobodybuildingService.listarTipoejercicios();
        List<TipoejerciciocrosstrainingDTO> listaTiposEjercicioCrossTraining = tipoejerciciocrosstrainingService.listarTipoejercicios();

        model.addAttribute("listaTiposEntrenamiento", listaTiposEntrenamiento);
        model.addAttribute("listaTiposEjercicioBodyBuilding", listaTiposEjercicioBodyBuilding);
        model.addAttribute("listaTiposEjercicioCrossTraining", listaTiposEjercicioCrossTraining);
        return "admin/nuevoEjercicio";
    }

    @GetMapping("/adminMain/datosEjercicio/{id}")
    public String doDatosEjercicio(@PathVariable("id") int id, HttpSession session, Model model) {
        if(!estaAutenticado(session)) return "redirect:/acceso";

        EjercicioDTO ejercicio = ejercicioService.buscarEjercicio(id);
        List<TipoentrenamientoDTO> listaTiposEntrenamiento = tipoentrenamientoService.listarTipoentrenamientos();
        List<TipoejerciciobodybuildingDTO> listaTiposEjercicioBodyBuilding = tipoejerciciobodybuildingService.listarTipoejercicios();
        List<TipoejerciciocrosstrainingDTO> listaTiposEjercicioCrossTraining = tipoejerciciocrosstrainingService.listarTipoejercicios();

        model.addAttribute("ejercicio", ejercicio);
        model.addAttribute("listaTiposEntrenamiento", listaTiposEntrenamiento);
        model.addAttribute("listaTiposEjercicioBodyBuilding", listaTiposEjercicioBodyBuilding);
        model.addAttribute("listaTiposEjercicioCrossTraining", listaTiposEjercicioCrossTraining);
        return "admin/datosEjercicio";
    }

    @PostMapping("/adminMain/editarEjercicio")
    public String editarEjercicio(@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion,
                                 @RequestParam("video") String video, @RequestParam("tipoentrenamiento") int tipoentrenamiento,
                                 @RequestParam("tipoejercicio") String tipoejercicio, @RequestParam("id") int id, HttpSession session){
        if(!estaAutenticado(session)) return "redirect:/acceso";

        ejercicioService.editarEjercicio(nombre, descripcion, video, tipoentrenamiento, tipoejercicio, id);

        return "redirect:/adminMain/ejercicios";
    }


    @PostMapping("/adminMain/crearEjercicio")
    public String crearEjercicio(@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion,
                                 @RequestParam("video") String video, @RequestParam("tipoentrenamiento") int tipoentrenamiento,
                                 @RequestParam("tipoejercicio") String tipoejercicio, HttpSession session){

        if(!estaAutenticado(session)) return "redirect:/acceso";

        ejercicioService.crearEjercicio(nombre, descripcion, video, tipoentrenamiento, tipoejercicio);

        return "redirect:/adminMain/ejercicios";
    }

}
