package sistema_organizacion.sistema.adapters.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import sistema_organizacion.sistema.entities.Tarea;
import sistema_organizacion.sistema.infrastructure.repositories.TareaRepository;
import sistema_organizacion.sistema.ports.outs.TareaOutputPort;

@Component
public class JpaTareaAdapter implements TareaOutputPort {

    private final TareaRepository tareaRepository;

    public JpaTareaAdapter(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @Override
    @SuppressWarnings("null")
    public Tarea guardar(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @Override
    public Optional<Tarea> buscarPorId(Long id) {
        @SuppressWarnings("null")
        Optional<Tarea> result = tareaRepository.findById(id);
        return result;
    }

    @Override
    public List<Tarea> buscarPorGrupoId(Long grupoId) {
        return tareaRepository.findByGrupoId(grupoId);
    }
}
