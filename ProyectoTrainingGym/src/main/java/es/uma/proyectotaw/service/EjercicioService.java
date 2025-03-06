package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.EjercicioRepository;
import es.uma.proyectotaw.dao.TipoentrenamientoRepository;
import es.uma.proyectotaw.dto.EjercicioDTO;
import es.uma.proyectotaw.dto.TipoentrenamientoDTO;
import es.uma.proyectotaw.entity.EjercicioEntity;
import es.uma.proyectotaw.entity.TipoentrenamientoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class EjercicioService extends DTOService<EjercicioDTO, EjercicioEntity>{

    @Autowired
    protected EjercicioRepository ejercicioRepository;

    @Autowired
    protected TipoentrenamientoRepository tipoentrenamientoRepository;

    public List<EjercicioDTO> listarEjercicios () {
        List<EjercicioEntity> entities = this.ejercicioRepository.findAll();
        return this.entidadesADTO(entities);
    }

    public List<EjercicioDTO> filtrarEjercicios (String filtro) {
        List<EjercicioEntity> listaEjercicios = ejercicioRepository.findAll();
        List<EjercicioDTO> listaFiltrada = new ArrayList<>();
        for (EjercicioEntity ejercicio : listaEjercicios) {
            if(ejercicio.getNombre().toLowerCase().contains(filtro.toLowerCase()) ||
                    ejercicio.getTipoEntrenamiento().getTipo().name().toLowerCase().contains(filtro.toLowerCase())){
                listaFiltrada.add(ejercicio.toDTO());
            }
        }
        // si la lista filtrada esta vacia, devolvemos la lista completa
        if(listaFiltrada.isEmpty()){
            listaFiltrada = this.entidadesADTO(listaEjercicios);
        }
        return listaFiltrada;
    }

    public void borrarEjercicio (Integer id) {
        this.ejercicioRepository.deleteById(id);
    }

    public EjercicioDTO buscarEjercicio(int id){
        EjercicioEntity ejercicio = ejercicioRepository.findById(id).get();
        return ejercicio.toDTO();
    }

    public void editarEjercicio(String nombre, String descripcion, String video, int tipoentrenamiento, String tipoejercicio, int id){
        EjercicioEntity nuevoEjercicio = ejercicioRepository.findById(id).get();
        nuevoEjercicio.setNombre(nombre);
        nuevoEjercicio.setDescripcion(descripcion);
        nuevoEjercicio.setVideo(video);
        nuevoEjercicio.setTipoEntrenamiento(tipoentrenamientoRepository.findById(tipoentrenamiento).get());

        String[] partes = tipoejercicio.split("_");
        int idtipoejercicio = Integer.parseInt(partes[1]);
        if (partes[0].equals("bb")) { // bodybuilding
            nuevoEjercicio.setTipoejerciciocrosstrainingId(null);
            nuevoEjercicio.setTipoejerciciobodybuildingId(idtipoejercicio);
        } else{ // crosstraining
            nuevoEjercicio.setTipoejerciciobodybuildingId(null);
            nuevoEjercicio.setTipoejerciciocrosstrainingId(idtipoejercicio);
        }

        ejercicioRepository.save(nuevoEjercicio);
    }

    public void crearEjercicio(String nombre, String descripcion, String video, int tipoentrenamiento, String tipoejercicio){
        EjercicioEntity nuevoEjercicio = new EjercicioEntity();
        nuevoEjercicio.setNombre(nombre);
        nuevoEjercicio.setDescripcion(descripcion);
        nuevoEjercicio.setVideo(video);
        nuevoEjercicio.setTipoEntrenamiento(tipoentrenamientoRepository.findById(tipoentrenamiento).get());

        String[] partes = tipoejercicio.split("_");
        int idtipoejercicio = Integer.parseInt(partes[1]);
        if (partes[0].equals("bb")) { // bodybuilding
            nuevoEjercicio.setTipoejerciciobodybuildingId(idtipoejercicio);
        } else{ // crosstraining
            nuevoEjercicio.setTipoejerciciocrosstrainingId(idtipoejercicio);
        }

        ejercicioRepository.save(nuevoEjercicio);
    }

    public List<EjercicioDTO> buscarPorTipoEntrenamiento(TipoentrenamientoDTO tipoEntrenamiento) {
        TipoentrenamientoEntity tipoentrenamientoEntity = this.tipoentrenamientoRepository.findById(tipoEntrenamiento.getId()).orElse(null);
        List<EjercicioEntity> ejercicios = this.ejercicioRepository.findByTipoEntrenamiento(tipoentrenamientoEntity);
        return this.entidadesADTO(ejercicios);
    }
}
