package sistema_organizacion.sistema.ports.outs;

import java.util.List;
import java.util.Optional;

import sistema_organizacion.sistema.entities.Estado;
import sistema_organizacion.sistema.entities.EstadoTarea;
import sistema_organizacion.sistema.entities.Tarea;

public interface TareaOutputPort {
    Tarea guardar(Tarea tarea);
    Optional<Tarea> buscarPorId(Long id);
    List<Tarea> buscarPorGrupoId(Long grupoId);
    Estado buscarEstadoPorNombre(EstadoTarea nombreEstado);
}