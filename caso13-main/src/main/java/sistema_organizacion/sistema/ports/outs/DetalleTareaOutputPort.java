package sistema_organizacion.sistema.ports.outs;

import sistema_organizacion.sistema.entities.DetalleTarea;
import java.util.List;
import java.util.Optional;

public interface DetalleTareaOutputPort {
    DetalleTarea guardar(DetalleTarea detalle);
    Optional<DetalleTarea> buscarPorId(Long id);
    List<DetalleTarea> buscarPorTareaId(Long tareaId);
}