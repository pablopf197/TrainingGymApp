package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.ClienteRepository;
import es.uma.proyectotaw.dao.TipoentrenamientoRepository;
import es.uma.proyectotaw.dao.TrolRepository;
import es.uma.proyectotaw.dao.UsuarioRepository;
import es.uma.proyectotaw.dto.ClienteDTO;
import es.uma.proyectotaw.dto.UsuarioDTO;
import es.uma.proyectotaw.entity.ClienteEntity;
import es.uma.proyectotaw.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends DTOService<ClienteDTO, ClienteEntity> {

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected UsuarioRepository usuarioRepository;

    @Autowired
    private TrolRepository trolRepository;

    @Autowired
    private TipoentrenamientoRepository tipoentrenamientoRepository;

    public ClienteDTO buscarCliente(int id){
        ClienteEntity usuario = clienteRepository.findById(id).get();
        return usuario.toDTO();
    }

    public void actualizarCliente(ClienteDTO clienteDTO) {
        ClienteEntity clienteEntity = clienteRepository.findById(clienteDTO.getId()).orElseThrow();
        clienteEntity.setAltura(clienteDTO.getAltura());
        clienteEntity.setPeso(clienteDTO.getPeso());
        clienteEntity.setObjetivos(clienteDTO.getObjetivos());
        clienteRepository.save(clienteEntity);
    }

    public void crearCliente(ClienteDTO clienteDTO, UsuarioDTO usuarioDTO) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setFechaNacimiento(usuarioDTO.getFechaNacimiento());
        usuario.setDni(usuarioDTO.getDni());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
        usuario.setContraseña(usuarioDTO.getContraseña());
        usuario.setGenero(usuarioDTO.getGenero());
        usuario.setRol(this.trolRepository.findById(usuarioDTO.getRol().getId()).get());
        usuario.setTipoEntrenamiento(this.tipoentrenamientoRepository.findById(usuarioDTO.getTipoEntrenamiento().getId()).get());
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setAltura(clienteDTO.getAltura());
        clienteEntity.setPeso(clienteDTO.getPeso());
        clienteEntity.setObjetivos(clienteDTO.getObjetivos());
        clienteEntity.setUsuario(usuario);
        clienteRepository.save(clienteEntity);
    }
}
