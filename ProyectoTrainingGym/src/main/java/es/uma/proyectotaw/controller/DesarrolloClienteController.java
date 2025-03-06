
package es.uma.proyectotaw.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.uma.proyectotaw.dto.ClienteDTO;
import es.uma.proyectotaw.dto.RutinaAsignadaDTO;
import es.uma.proyectotaw.dto.RutinaSesionentrenamientoDTO;
import es.uma.proyectotaw.dto.SesionejercicioDTO;
import es.uma.proyectotaw.dto.SesionentrenamientoDTO;
import es.uma.proyectotaw.dto.SesionentrenamientoHasSesionejercicioDTO;
import es.uma.proyectotaw.dto.UsuarioDTO;
import es.uma.proyectotaw.dto.ValoracionDTO;
import es.uma.proyectotaw.service.RutinaAsignadaService;
import es.uma.proyectotaw.service.RutinaSesionentrenamientoService;
import es.uma.proyectotaw.service.SesionentrenamientoHasSesionejercicioService;
import es.uma.proyectotaw.service.SesionentrenamientoService;
import es.uma.proyectotaw.service.UsuarioService;
import es.uma.proyectotaw.service.ValoracionService;
import jakarta.servlet.http.HttpSession;

@Controller
public class DesarrolloClienteController extends BaseController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RutinaAsignadaService rutinaAsignadaService;

    @Autowired
    private RutinaSesionentrenamientoService rutinaSesionentrenamientoService;

    @Autowired
    private SesionentrenamientoService sesionEntrenamientoService;

    @Autowired
    private SesionentrenamientoHasSesionejercicioService sesionentrenamientoHasSesionejercicioService;

    @Autowired
    private ValoracionService valoracionService;

    @GetMapping("/clienteMain/desarrollo")
    public String doDesarrollo(@RequestParam("fecha") String fecha, Model model, HttpSession session) {
        if(!estaAutenticado(session)) return "redirect:/acceso";

        ClienteDTO cliente = (ClienteDTO) session.getAttribute("cliente");
        UsuarioDTO usuario = this.usuarioService.buscarUsuario(cliente.getId());

        model.addAttribute("usuario", usuario);
        Date fechaDate = Date.valueOf(fecha);
        LocalDate fechaLocal = fechaDate.toLocalDate();
        model.addAttribute("semana", fechaLocal);
        RutinaAsignadaDTO rutinaAsignada = this.rutinaAsignadaService.buscarPorUsuarioYFecha(usuario,fechaDate);

        if (rutinaAsignada!=null) {
            model.addAttribute("rutinaAsignada", rutinaAsignada);
            List<RutinaSesionentrenamientoDTO> rutinasSesiones = this.rutinaSesionentrenamientoService.buscarPorRutinaPredefinidaOrdenadaPorPosicion(rutinaAsignada.getRutinaPredefinida());
            List<ValoracionDTO> valoraciones = this.valoracionService.buscarPorUsuarioYRutinaAsignadaOrdenadoPorSesionejercicio(usuario.getId(),rutinaAsignada.getId());
            Map<Integer, Double> mediasValoraciones = new HashMap<>();
            List<Integer> sesionesSinValoracion = new ArrayList<>();
            for (RutinaSesionentrenamientoDTO rutinaSesion : rutinasSesiones) {
                List<SesionejercicioDTO> ejerciciosSesion = sesionentrenamientoHasSesionejercicioService.buscarPorSesionentrenamientoOrdenadoPorPosicion(rutinaSesion.getSesionentrenamiento().getId())
                        .stream().map(SesionentrenamientoHasSesionejercicioDTO::getSesionejercicio).toList();
                List<ValoracionDTO> valoracionesSesion = valoraciones.stream()
                        .filter(val -> ejerciciosSesion.contains(val.getSesionejercicio()))
                        .toList();
                if (valoracionesSesion.isEmpty()) {
                    sesionesSinValoracion.add(rutinaSesion.getSesionentrenamiento().getId());
                } else {
                    double media = valoracionesSesion.stream().mapToDouble(ValoracionDTO::getPuntuacion).average().orElse(0.0);
                    mediasValoraciones.put(rutinaSesion.getSesionentrenamiento().getId(), media);
                }
            }
            model.addAttribute("sesionesSinValoracion", sesionesSinValoracion);
            model.addAttribute("mediasValoraciones", mediasValoraciones);
            model.addAttribute("rutinasSesiones", rutinasSesiones);
        } else {
            model.addAttribute("rutinaAsignada", null);
        }

        return "cliente/desarrolloCliente";
    }
    @GetMapping("/clienteMain/desarrollo/sesion")
    public String verSesion(@RequestParam("rutina") Integer rutinaId ,@RequestParam("sesion") Integer sesionId,Model model, HttpSession session) {
        if(!estaAutenticado(session)) return "redirect:/acceso";
        ClienteDTO cliente = (ClienteDTO) session.getAttribute("cliente");
        UsuarioDTO usuario = usuarioService.buscarUsuario(cliente.getId());
        SesionentrenamientoDTO sesion = sesionEntrenamientoService.buscarPorId(sesionId);
        RutinaAsignadaDTO rutinaAsignada = rutinaAsignadaService.buscarPorId(rutinaId);
        List<SesionentrenamientoHasSesionejercicioDTO> sesionesHasSesionesEjercicios = sesionentrenamientoHasSesionejercicioService.buscarPorSesionentrenamientoOrdenadoPorPosicion(sesion.getId());
        List<SesionejercicioDTO> sesionesEjercicio = sesionesHasSesionesEjercicios.stream().map(SesionentrenamientoHasSesionejercicioDTO::getSesionejercicio).toList();
        List<ValoracionDTO>  valoraciones = valoracionService.buscarPorUsuarioYRutinaAsignadaYSesionejercicioDentro(usuario.getId(),rutinaAsignada.getId(), sesionesEjercicio);
        LocalDate fechaLocal = rutinaAsignada.getFecha().toLocalDate();
        model.addAttribute("rutinaAsignada", rutinaAsignada);
        model.addAttribute("semana", fechaLocal);
        model.addAttribute("sesion", sesion);
        model.addAttribute("valoraciones", valoraciones);
        model.addAttribute("sesionesEjercicio", sesionesEjercicio);
        model.addAttribute("usuario", usuario);
        return "cliente/valoracionesCliente";

    }

    @GetMapping("/clienteMain/desarrollo/sesionesValoradas")
    public String listarSesionesValoradas(Model model, HttpSession session) {
        if(!estaAutenticado(session)) return "redirect:/acceso";
        ClienteDTO cliente = (ClienteDTO) session.getAttribute("cliente");
        UsuarioDTO usuario = usuarioService.buscarUsuario(cliente.getId());
        List<ValoracionDTO>  valoraciones = valoracionService.buscarPorUsuario(usuario);
        model.addAttribute("usuario", usuario);
        model.addAttribute("valoraciones", valoraciones);
        return "cliente/sesionesValoradas";
    }

    @PostMapping("/clienteMain/desarrollo/sesionesValoradas/filtrar")
    public String filtrarSesionesValoradas(@RequestParam(value = "puntuacion", required = false) Integer puntuacion
                                           ,Model model, HttpSession session) {
        if(!estaAutenticado(session)) return "redirect:/acceso";


        String strTo = "sesionesValoradas";

        if (puntuacion == null ) {
            strTo = "redirect:/clienteMain/desarrollo/sesionesValoradas";
        } else {
            ClienteDTO cliente = (ClienteDTO) session.getAttribute("cliente");
            UsuarioDTO usuario = usuarioService.buscarUsuario(cliente.getId());
            List<ValoracionDTO>  valoraciones = valoracionService.buscarPorPuntuacionYUsuario(puntuacion,usuario);
            model.addAttribute("valoraciones", valoraciones);
            model.addAttribute("usuario", usuario);
        }

        return strTo;
    }
}