package sistema_organizacion.sistema.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistema_organizacion.sistema.entities.Tarea;
import java.util.List;
import java.util.Optional;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByGrupoId(Long grupoId);
    Optional<Tarea> findByIdAndGrupoId(Long id, Long grupoId);
}
