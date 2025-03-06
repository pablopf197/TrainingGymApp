package es.uma.proyectotaw.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.uma.proyectotaw.dto.EjercicioDTO;
import es.uma.proyectotaw.dto.RutinaSesionentrenamientoDTO;
import es.uma.proyectotaw.dto.SesionejercicioDTO;
import es.uma.proyectotaw.dto.SesionentrenamientoDTO;
import es.uma.proyectotaw.dto.SesionentrenamientoHasSesionejercicioDTO;
import es.uma.proyectotaw.dto.UsuarioDTO;
import es.uma.proyectotaw.dto.ValoracionDTO;
import es.uma.proyectotaw.service.EjercicioService;
import es.uma.proyectotaw.service.RutinaSesionentrenamientoService;
import es.uma.proyectotaw.service.SesionEjercicioService;
import es.uma.proyectotaw.service.SesionentrenamientoHasSesionejercicioService;
import es.uma.proyectotaw.service.SesionentrenamientoService;
import es.uma.proyectotaw.service.ValoracionService;
import jakarta.servlet.http.HttpSession;

@Controller
public class SesionEntrenadorController extends BaseController{



    @Autowired
    private SesionentrenamientoService sesionEntrenamientoService;

    @Autowired
    private SesionentrenamientoHasSesionejercicioService sesionentrenamientoHasSesionejercicioService;

    @Autowired
    private EjercicioService ejercicioService;

    @Autowired
    private SesionEjercicioService sesionEjercicioService;

    @Autowired
    private RutinaSesionentrenamientoService rutinaSesionentrenamientoService;

    @Autowired
    private ValoracionService valoracionService;

    @GetMapping("/entrenadorMain/sesiones")
    public String doSesiones(Model model, HttpSession session) {
        if(!estaAutenticado(session)) return "redirect:/acceso";
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
        List<SesionentrenamientoDTO> sesiones = sesionEntrenamientoService.findByUsuario(usuario);
        model.addAttribute("sesiones", sesiones);
        return "entrenador/sesionesEntrenador";
    }

    @PostMapping("/entrenadorMain/sesiones/filtrar")
    public String doFiltrar(@RequestParam("filtro") String filtro, Model model, HttpSession session) {
        if (!estaAutenticado(session)) return "redirect:/acceso";
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
        List<SesionentrenamientoDTO> listaSesiones = sesionEntrenamientoService.findByUsuario(usuario);
        List<SesionentrenamientoDTO> listaFiltrada = new ArrayList<>();

        for (SesionentrenamientoDTO sesion : listaSesiones) {
            if (sesion.getNombre() != null && sesion.getNombre().toLowerCase().contains(filtro.toLowerCase())){
                listaFiltrada.add(sesion);
            }
        }

        if(filtro.isEmpty()){
            listaFiltrada = listaSesiones;
        }

        model.addAttribute("filtro", filtro);
        model.addAttribute("sesiones", listaFiltrada);
        model.addAttribute("entrenador", usuario);
        return "entrenador/sesionesEntrenador";
    }

    @GetMapping("/entrenadorMain/sesiones/crear")
    public String doCrearSesion(Model model, HttpSession session) {
        if(!estaAutenticado(session)) return "redirect:/acceso";
        List<EjercicioDTO> listaEjercicios = ejercicioService.listarEjercicios();
        model.addAttribute("listaEjercicios", listaEjercicios);
        SesionentrenamientoDTO sesion = new SesionentrenamientoDTO();
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
        sesion.setUsuario(usuario);
        sesion = sesionEntrenamientoService.saveNew(sesion);
        model.addAttribute("sesion", sesion);
        return "entrenador/crearSesionEntrenador";
    }

    //Borrar Sesion
    @GetMapping("/entrenadorMain/sesiones/borrar")
    public String doBorrarSesion(Model model, HttpSession session, @RequestParam("id") Integer id) {
        if(!estaAutenticado(session)) return "redirect:/acceso";
        SesionentrenamientoDTO sesion = sesionEntrenamientoService.buscarPorId(id);
        List<RutinaSesionentrenamientoDTO> rutinasSesiones = rutinaSesionentrenamientoService.buscarPorSesionentrenamientoOrdenadoPorPosicion(sesion);
        for (RutinaSesionentrenamientoDTO rutinaSesion : rutinasSesiones) {
            rutinaSesionentrenamientoService.delete(rutinaSesion);
        }
        List<SesionentrenamientoHasSesionejercicioDTO> sesionesHasSesiones = sesionentrenamientoHasSesionejercicioService.buscarPorSesionentrenamientoOrdenadoPorPosicion(sesion.getId());
        for (SesionentrenamientoHasSesionejercicioDTO sesionHasSesion : sesionesHasSesiones) {
            SesionejercicioDTO sesionEjercicio = sesionHasSesion.getSesionejercicio();
            List<ValoracionDTO> valoraciones = valoracionService.buscarPorSesionEjercicio(sesionEjercicio);
            for (ValoracionDTO valoracion : valoraciones) {
                valoracionService.delete(valoracion);
            }
            // Eliminar la relación entre la sesión de entrenamiento y la sesión de ejercicio
            sesionentrenamientoHasSesionejercicioService.delete(sesionHasSesion);

            // Eliminar la sesión de ejercicio
            sesionEjercicioService.delete(sesionEjercicio);
        }
        sesionEntrenamientoService.delete(sesion);
        return "redirect:/entrenadorMain/sesiones";
    }
    @GetMapping("/entrenadorMain/sesiones/ver")
    public String doVerSesion(Model model, HttpSession session, @RequestParam("id") Integer id) {
        if(!estaAutenticado(session)) return "redirect:/acceso";
        SesionentrenamientoDTO sesion = sesionEntrenamientoService.buscarPorId(id);
        List<SesionentrenamientoHasSesionejercicioDTO> listaSesionHasSesion = sesionentrenamientoHasSesionejercicioService.buscarPorSesionentrenamientoOrdenadoPorPosicion(sesion.getId());
        model.addAttribute("listaSesionHasSesion", listaSesionHasSesion);
        model.addAttribute("sesion", sesion);
        List<EjercicioDTO> listaEjercicios = ejercicioService.buscarPorTipoEntrenamiento(sesion.getUsuario().getTipoEntrenamiento());
        model.addAttribute("listaEjercicios", listaEjercicios);

        return "entrenador/verSesionEntrenador";
    }



    @GetMapping("/entrenadorMain/sesiones/ver/ejercicio")
    public String doVerEjercicio(Model model, HttpSession session, @Param("id") Integer id ){
        if(!estaAutenticado(session)) return "redirect:/acceso";
        EjercicioDTO ejercicio = ejercicioService.buscarEjercicio(id);
        model.addAttribute("ejercicio", ejercicio);
        return "entrenador/verEjercicioEntrenador";
    }

    private List<Integer> convertirAEnteros(List<String> valores) {
        List<Integer> enteros = new ArrayList<>();
        for (String valor : valores) {
            try {
                enteros.add(Integer.parseInt(valor));
            } catch (NumberFormatException e) {
                enteros.add(null); // Si no es un entero, agregar null
            }
        }
        return enteros;
    }

    @PostMapping("/entrenadorMain/sesiones/guardar")
    public String doGuardarSesion(@RequestParam("id") Integer id,
                                  @RequestParam("nombre") String nombre,
                                  @RequestParam("descripcion") String descripcion,
                                  @RequestParam(value = "ejercicios", required = false) List<Integer> ejercicios,
                                  @RequestParam(value = "series", required = false) List<String> series,
                                  @RequestParam(value = "repeticiones", required = false) List<String> repeticiones,
                                  @RequestParam(value = "duracion", required = false) List<String> duracion,
                                  HttpSession session) {
        if (!estaAutenticado(session)) return "redirect:/acceso";

        SesionentrenamientoDTO sesion = sesionEntrenamientoService.buscarPorId(id);
        sesion.setNombre(nombre);
        sesion.setDescripcion(descripcion);
        sesionEntrenamientoService.save(sesion);

        List<SesionentrenamientoHasSesionejercicioDTO> sesionesHasSesiones = sesionentrenamientoHasSesionejercicioService.buscarPorSesionentrenamientoOrdenadoPorPosicion(sesion.getId());

        // Crear una lista de ejercicios IDs existentes
        List<Integer> ejerciciosExistentes = new ArrayList<>();
        for (SesionentrenamientoHasSesionejercicioDTO sesionHasSesion : sesionesHasSesiones) {
            ejerciciosExistentes.add(sesionHasSesion.getSesionejercicio().getEjercicio().getId());
        }

        // Si no hay ejercicios nuevos, eliminar todos los ejercicios existentes
        if (ejercicios == null) {
            for (SesionentrenamientoHasSesionejercicioDTO sesionHasSesion : sesionesHasSesiones) {
                SesionejercicioDTO sesionEjercicio = sesionHasSesion.getSesionejercicio();
                sesionentrenamientoHasSesionejercicioService.delete(sesionHasSesion);
                sesionEjercicioService.delete(sesionEjercicio);
            }
            return "redirect:/entrenadorMain/sesiones";
        }

        List<Integer> seriesInt = convertirAEnteros(series);
        List<Integer> repeticionesInt = convertirAEnteros(repeticiones);
        List<Integer> duracionInt = convertirAEnteros(duracion);

        // Mapear ejercicios existentes por ID para una fácil búsqueda
        Map<Integer, SesionentrenamientoHasSesionejercicioDTO> mapSesionesExistentes = sesionesHasSesiones.stream()
                .collect(Collectors.toMap(s -> s.getSesionejercicio().getEjercicio().getId(), s -> s));

        // Actualizar o eliminar ejercicios existentes
        for (SesionentrenamientoHasSesionejercicioDTO sesionHasSesion : sesionesHasSesiones) {
            Integer ejercicioId = sesionHasSesion.getSesionejercicio().getEjercicio().getId();
            if (!ejercicios.contains(ejercicioId)) {
                // Si el ejercicio no está en la nueva lista, eliminarlo
                SesionejercicioDTO sesionEjercicio = sesionHasSesion.getSesionejercicio();
                sesionentrenamientoHasSesionejercicioService.delete(sesionHasSesion);
                sesionEjercicioService.delete(sesionEjercicio);

            }
        }

        // Agregar nuevos ejercicios o actualizar existentes
        for (int i = 0; i < ejercicios.size(); i++) {
            Integer ejercicioId = ejercicios.get(i);
            if (mapSesionesExistentes.containsKey(ejercicioId)) {
                // Si el ejercicio ya existe, actualizarlo
                SesionentrenamientoHasSesionejercicioDTO sesionHasSesion = mapSesionesExistentes.get(ejercicioId);
                sesionHasSesion.getSesionejercicio().setSeries(seriesInt.get(i));
                sesionHasSesion.getSesionejercicio().setRepeticiones(repeticionesInt.get(i));
                sesionHasSesion.getSesionejercicio().setDuracion(duracionInt.get(i));
                sesionHasSesion.setPosicion(i); // Actualizar la posición
                sesionentrenamientoHasSesionejercicioService.save(sesionHasSesion);
            } else {
                // Si el ejercicio es nuevo, agregarlo
                SesionentrenamientoHasSesionejercicioDTO sesionHasSesion = new SesionentrenamientoHasSesionejercicioDTO();
                sesionHasSesion.setSesionentrenamiento(sesion);
                SesionejercicioDTO sesionEjercicio = new SesionejercicioDTO();
                sesionEjercicio.setEjercicio(ejercicioService.buscarEjercicio(ejercicioId));
                sesionEjercicio.setSeries(seriesInt.get(i));
                sesionEjercicio.setRepeticiones(repeticionesInt.get(i));
                sesionEjercicio.setDuracion(duracionInt.get(i));
                sesionEjercicio = sesionEjercicioService.saveNew(sesionEjercicio);
                sesionHasSesion.setSesionejercicio(sesionEjercicio);
                sesionHasSesion.setPosicion(i); // Establecer la posición
                sesionentrenamientoHasSesionejercicioService.saveNew(sesionHasSesion);
            }
        }

        return "redirect:/entrenadorMain/sesiones";
    }
}
