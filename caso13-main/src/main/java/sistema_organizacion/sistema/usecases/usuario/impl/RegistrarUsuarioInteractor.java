package sistema_organizacion.sistema.usecases.usuario.impl;

import java.util.UUID;
import sistema_organizacion.sistema.entities.JefeDeHogar;
import sistema_organizacion.sistema.entities.MiembroHogar;
import sistema_organizacion.sistema.entities.RolUsuario;
import sistema_organizacion.sistema.entities.Usuario;
import sistema_organizacion.sistema.entities.exception.AdministradorYaExisteException;
import sistema_organizacion.sistema.entities.exception.RolNoSeleccionadoException;
import sistema_organizacion.sistema.ports.outs.UsuarioOutputPort;
import sistema_organizacion.sistema.usecases.usuario.RegistrarUsuarioCommand;
import sistema_organizacion.sistema.usecases.usuario.RegistrarUsuarioUseCase;

public class RegistrarUsuarioInteractor implements RegistrarUsuarioUseCase {

    private final UsuarioOutputPort usuarioOutputPort;

    public RegistrarUsuarioInteractor(UsuarioOutputPort usuarioOutputPort) {
        this.usuarioOutputPort = usuarioOutputPort;
    }

    @Override
    public Usuario ejecutar(RegistrarUsuarioCommand command) {

        // CA-04-A HU-01: rol obligatorio
        if (command.getRol() == null) {
            throw new RolNoSeleccionadoException();
        }

        String id = UUID.randomUUID().toString();
        Usuario nuevo;

        if (command.getRol() == RolUsuario.ADMIN) {
            // CA-04-B HU-01: no puede haber dos administradores
            if (usuarioOutputPort.existeAdministrador()) {
                throw new AdministradorYaExisteException();
            }
            // Validaciones de correo y contraseña ocurren en el constructor
            nuevo = new JefeDeHogar(
                id,
                command.getNombre(),
                command.getApellido(),
                command.getCorreo(),
                command.getUsername(),
                command.getContrasena()
            );
        } else {
            nuevo = new MiembroHogar(
                id,
                command.getNombre(),
                command.getApellido(),
                command.getCorreo(),
                command.getUsername(),
                command.getContrasena()
            );
        }

        return usuarioOutputPort.guardar(nuevo);
    }
}