package sistema_organizacion.sistema.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rol {

    @Id
    @Column(name = "id_rol")
    private Integer id;

    @Column(name = "nombre_rol")
    private String nombreRol;
}
