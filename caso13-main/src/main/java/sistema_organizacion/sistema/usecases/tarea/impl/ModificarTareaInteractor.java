package sistema_organizacion.sistema.usecases.tarea.impl;

import sistema_organizacion.sistema.entities.JefeDeHogar;
import sistema_organizacion.sistema.entities.Tarea;
import sistema_organizacion.sistema.entities.Usuario;
import sistema_organizacion.sistema.entities.exception.AccesoDenegadoException;
import sistema_organizacion.sistema.entities.exception.TareaNoEncontradaException;
import sistema_organizacion.sistema.ports.outs.TareaOutputPort;
import sistema_organizacion.sistema.ports.outs.UsuarioOutputPort;
import sistema_organizacion.sistema.usecases.tarea.ModificarTareaCommand;
import sistema_organizacion.sistema.usecases.tarea.ModificarTareaUseCase;

public class ModificarTareaInteractor implements ModificarTareaUseCase {

    private final UsuarioOutputPort usuarioOutputPort;
    private final TareaOutputPort tareaOutputPort;

    public ModificarTareaInteractor(UsuarioOutputPort usuarioOutputPort,
                                    TareaOutputPort tareaOutputPort) {
        this.usuarioOutputPort = usuarioOutputPort;
        this.tareaOutputPort = tareaOutputPort;
    }

    @Override
    public Tarea ejecutar(ModificarTareaCommand command) {

        // CA-01 HU-14: solo administrador
        Usuario usuario = usuarioOutputPort
            .buscarPorId(command.getJefeId())
            .orElseThrow(AccesoDenegadoException::new);

        if (!(usuario instanceof JefeDeHogar)) {
            throw new AccesoDenegadoException();
        }

        // CA-04 HU-14: la tarea debe existir
        Tarea tarea = tareaOutputPort
            .buscarPorId(command.getTareaId())
            .orElseThrow(() ->
                new TareaNoEncontradaException(command.getTareaId()));

        // CA-02 HU-14: validaciones y cambio de estado en la propia entidad
        tarea.actualizar(
            command.getNuevoTitulo(),
            command.getNuevaDescripcion(),
            command.getNuevaFechaLimite()
        );

        // CA-03 y CA-04 HU-14: persistir cambios
        return tareaOutputPort.guardar(tarea);
    }
}