package sistema_organizacion.sistema.usecases.grupo.impl;

import sistema_organizacion.sistema.entities.GrupoFamiliar;
import sistema_organizacion.sistema.entities.MiembroHogar;
import sistema_organizacion.sistema.entities.Usuario;
import sistema_organizacion.sistema.entities.exception.AccesoDenegadoException;
import sistema_organizacion.sistema.entities.exception.CodigoAccesoInvalidoException;
import sistema_organizacion.sistema.ports.outs.GrupoFamiliarOutputPort;
import sistema_organizacion.sistema.ports.outs.UsuarioOutputPort;
import sistema_organizacion.sistema.usecases.grupo.IngresarGrupoCommand;
import sistema_organizacion.sistema.usecases.grupo.IngresarGrupoFamiliarUseCase;

public class IngresarGrupoFamiliarInteractor implements IngresarGrupoFamiliarUseCase {

    private final UsuarioOutputPort usuarioOutputPort;
    private final GrupoFamiliarOutputPort grupoOutputPort;

    public IngresarGrupoFamiliarInteractor(UsuarioOutputPort usuarioOutputPort,
                                           GrupoFamiliarOutputPort grupoOutputPort) {
        this.usuarioOutputPort = usuarioOutputPort;
        this.grupoOutputPort = grupoOutputPort;
    }

    @Override
    public GrupoFamiliar ejecutar(IngresarGrupoCommand command) {

        Usuario usuario = usuarioOutputPort
            .buscarPorId(command.getMiembroId())
            .orElseThrow(AccesoDenegadoException::new);

        if (!(usuario instanceof MiembroHogar)) {
            throw new AccesoDenegadoException();
        }

        MiembroHogar miembro = (MiembroHogar) usuario;

        // CA-02-A y CA-02-B HU-04: validar código
        GrupoFamiliar grupo = grupoOutputPort
            .buscarPorCodigo(command.getCodigoAcceso())
            .orElseThrow(CodigoAccesoInvalidoException::new);

        //  miembro: no puede estar en dos grupos
        // rupo: no puede tener miembros duplicados
        miembro.asignarGrupo(grupo.getId());
        grupo.agregarMiembro(miembro);

        usuarioOutputPort.guardar(miembro);
        return grupoOutputPort.guardar(grupo);
    }
}