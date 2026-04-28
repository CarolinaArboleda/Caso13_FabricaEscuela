package sistema_organizacion.sistema.adapters.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import sistema_organizacion.sistema.entities.Estado;
import sistema_organizacion.sistema.entities.EstadoTarea;
import sistema_organizacion.sistema.entities.Tarea;
import sistema_organizacion.sistema.infrastructure.repositories.EstadoRepository;
import sistema_organizacion.sistema.infrastructure.repositories.TareaRepository;
import sistema_organizacion.sistema.ports.outs.TareaOutputPort;

@Component
public class JpaTareaAdapter implements TareaOutputPort {

    private final TareaRepository tareaRepository;
    private final EstadoRepository estadoRepository;

    //Constructor con ambos parámetros, sin asignar null
    public JpaTareaAdapter(TareaRepository tareaRepository,
                            EstadoRepository estadoRepository) {
        this.tareaRepository = tareaRepository;
        this.estadoRepository = estadoRepository;
    }

    @Override
    public Tarea guardar(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @Override
    public Optional<Tarea> buscarPorId(Long id) {
        return tareaRepository.findById(id);
    }

    @Override
    public List<Tarea> buscarPorGrupoId(Long grupoId) {
        return tareaRepository.findByGrupo_Id(grupoId);
    }

    @Override
    public Estado buscarEstadoPorNombre(EstadoTarea nombreEstado) {
        return estadoRepository.findByNombreEstado(nombreEstado)
            .orElseThrow(() -> new RuntimeException("Estado no encontrado: " + nombreEstado));
}
}