
package es.uma.proyectotaw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.uma.proyectotaw.dto.ClienteDTO;
import es.uma.proyectotaw.dto.UsuarioDTO;
import es.uma.proyectotaw.service.ClienteService;
import es.uma.proyectotaw.service.UsuarioService;
import jakarta.servlet.http.HttpSession;

@Controller
public class clientePerfilController extends BaseController{

    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    protected ClienteService clienteService;


    @GetMapping("/clienteMain")
    public String doClienteMain(Model model, HttpSession session) {
        if(!estaAutenticado(session)) return "redirect:/acceso";

        return "redirect:/clienteMain/inicio";
    }

    @GetMapping("/clienteMain/inicio")
    public String doMainInicio(Model model, HttpSession session) {
        if(!estaAutenticado(session)) return "redirect:/acceso";
        ClienteDTO cliente = (ClienteDTO) session.getAttribute("cliente");
        model.addAttribute("cliente", cliente);
        return "cliente/mainCliente";
    }

    @GetMapping("/clienteMain/perfil")
    public String doPerfil(Model model, HttpSession session) {
        if(!estaAutenticado(session)) return "redirect:/acceso";
        ClienteDTO cliente = (ClienteDTO) session.getAttribute("cliente");
        model.addAttribute("cliente", cliente);
        return "cliente/perfilCliente";
    }

    @PostMapping("/clienteMain/perfil")
    public String cambiosPerfil(@RequestParam("correo") String email, @RequestParam("telefono") String telefono,
                                @RequestParam("altura") Float altura, @RequestParam("peso") Float peso,
                                @RequestParam("objetivos") String objetivos, Model model, HttpSession session) {
        if(!estaAutenticado(session)) return "redirect:/acceso";
        ClienteDTO cliente = (ClienteDTO) session.getAttribute("cliente");
        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
        usuario.setCorreo(email);
        usuario.setTelefono(telefono);

        cliente.setAltura(altura);
        cliente.setPeso(peso);
        cliente.setObjetivos(objetivos);

        usuarioService.actualizarUsuario(usuario);
        clienteService.actualizarCliente(cliente);

        model.addAttribute("cliente", cliente);
        return "cliente/mainCliente";
    }

}
