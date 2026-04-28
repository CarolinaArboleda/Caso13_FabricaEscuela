package sistema_organizacion.sistema.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estados")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estados {

    @Id
    @Column(name = "id_estado")
    private Integer id;

    @Column(name = "nombre_estado")
    private String nombreEstado;
}