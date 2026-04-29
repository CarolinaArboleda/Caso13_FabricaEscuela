package sistema_organizacion.sistema.infrastructure.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistema_organizacion.sistema.entities.DetalleTarea;
import java.util.List;
@Repository
public interface DetalleTareaRepository extends JpaRepository<DetalleTarea, Long> {
    List<DetalleTarea> findByTarea_Id(Long tareaId);
}