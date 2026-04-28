package sistema_organizacion.sistema.ports.outs;

import java.util.Optional;
import sistema_organizacion.sistema.entities.GrupoFamiliar;

public interface GrupoFamiliarOutputPort {
    GrupoFamiliar guardar(GrupoFamiliar grupo);
    Optional<GrupoFamiliar> buscarPorId(Long id);
    Optional<GrupoFamiliar> buscarPorCodigo(String codigo);
}