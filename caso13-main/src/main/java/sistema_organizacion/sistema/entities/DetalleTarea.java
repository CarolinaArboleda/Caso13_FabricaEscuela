package sistema_organizacion.sistema.entities;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "detalle_tareas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleTarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Long id;

    @Column(name = "id_tarea", nullable = false)
    private Long tareaId;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "observacion")
    private String observacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDate fechaActualizacion;
}
