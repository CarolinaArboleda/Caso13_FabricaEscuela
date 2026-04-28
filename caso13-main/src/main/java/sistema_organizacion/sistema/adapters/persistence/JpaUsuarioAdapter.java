package sistema_organizacion.sistema.adapters.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import sistema_organizacion.sistema.entities.JefeDeHogar;
import sistema_organizacion.sistema.entities.MiembroHogar;
import sistema_organizacion.sistema.entities.Rol;
import sistema_organizacion.sistema.entities.RolUsuario;
import sistema_organizacion.sistema.entities.Usuario;
import sistema_organizacion.sistema.infrastructure.repositories.RolRepository;
import sistema_organizacion.sistema.infrastructure.repositories.UsuarioRepository;
import sistema_organizacion.sistema.ports.outs.UsuarioOutputPort;

@Component
public class JpaUsuarioAdapter implements UsuarioOutputPort {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public JpaUsuarioAdapter(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        initializeDefaultData();
    }

    private void initializeDefaultData() {
        // Inicializar roles si no existen
        if (rolRepository.findByNombreRol(RolUsuario.ADMIN).isEmpty()) {
            rolRepository.save(new Rol(null, RolUsuario.ADMIN));
        }
        if (rolRepository.findByNombreRol(RolUsuario.USER).isEmpty()) {
            rolRepository.save(new Rol(null, RolUsuario.USER));
        }

        // Crear usuario admin por defecto
        if (usuarioRepository.findByCorreo("usuario@gmail.com").isEmpty()) {
            Rol rolAdmin = rolRepository.findByNombreRol(RolUsuario.ADMIN)
                .orElseThrow(() -> new RuntimeException("Rol ADMIN no encontrado"));
            usuarioRepository.save(new JefeDeHogar(
                "Ana", "Lopez", "usuario@gmail.com", "ana", "1234", rolAdmin
            ));
        }

        // Crear usuario normal por defecto
        if (usuarioRepository.findByCorreo("luis@gmail.com").isEmpty()) {
            Rol rolUser = rolRepository.findByNombreRol(RolUsuario.USER)
                .orElseThrow(() -> new RuntimeException("Rol USER no encontrado"));
            usuarioRepository.save(new MiembroHogar(
                "Luis", "Perez", "luis@gmail.com", "luis", "1234", rolUser
            ));
        }
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        @SuppressWarnings("null")
        Optional<Usuario> result = usuarioRepository.findById(id);
        return result;
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    @SuppressWarnings("null")
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public boolean existeAdministrador() {
        return usuarioRepository.findAll().stream()
            .anyMatch(usuario -> usuario.getRol().getNombreRol() == RolUsuario.ADMIN);
    }

    @Override
    public Rol buscarRolPorNombre(RolUsuario nombreRol) {
        return rolRepository.findByNombreRol(nombreRol)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + nombreRol));
}
}
