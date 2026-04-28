package sistema_organizacion.sistema.adapters.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import sistema_organizacion.sistema.entities.JefeDeHogar;
import sistema_organizacion.sistema.entities.MiembroHogar;
import sistema_organizacion.sistema.entities.RolUsuario;
import sistema_organizacion.sistema.entities.Usuario;
import sistema_organizacion.sistema.ports.outs.UsuarioOutputPort;

@Component
public class InMemoryUsuarioAdapter implements UsuarioOutputPort {

    private final Map<Long, Usuario> usuarios = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public InMemoryUsuarioAdapter() {
        guardar(new JefeDeHogar(
            1L, "Ana", "Lopez", "usuario@gmail.com", "ana", "1234"
        ));
        guardar(new MiembroHogar(
            2L, "Luis", "Perez", "luis@gmail.com", "luis", "1234"
        ));
        idGenerator.set(3L);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return Optional.ofNullable(usuarios.get(id));
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarios.values().stream()
            .filter(usuario -> usuario.getCorreo().equalsIgnoreCase(correo))
            .findFirst();
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        if (usuario.getId() == null) {
            usuario = new JefeDeHogar(
                idGenerator.getAndIncrement(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getCorreo(),
                usuario.getUsername(),
                usuario.getContrasena()
            );
        }
        usuarios.put(usuario.getId(), usuario);
        return usuario;
    }

    @Override
    public boolean existeAdministrador() {
        return usuarios.values().stream()
            .anyMatch(usuario -> usuario.getRol() == RolUsuario.ADMIN);
    }
}
