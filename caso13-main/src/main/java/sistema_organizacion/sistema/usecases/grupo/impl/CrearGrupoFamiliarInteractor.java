package sistema_organizacion.sistema.usecases.grupo.impl;

import sistema_organizacion.sistema.entities.GrupoFamiliar;
import sistema_organizacion.sistema.entities.Usuario;
import sistema_organizacion.sistema.entities.JefeDeHogar;
import sistema_organizacion.sistema.entities.exception.AccesoDenegadoException;
import sistema_organizacion.sistema.ports.outs.GrupoFamiliarOutputPort;
import sistema_organizacion.sistema.ports.outs.UsuarioOutputPort;
import sistema_organizacion.sistema.usecases.grupo.CrearGrupoFamiliarUseCase;
import sistema_organizacion.sistema.usecases.grupo.CrearGrupoCommand;

public class CrearGrupoFamiliarInteractor implements CrearGrupoFamiliarUseCase {

    private final UsuarioOutputPort usuarioOutputPort;
    private final GrupoFamiliarOutputPort grupoOutputPort;

    public CrearGrupoFamiliarInteractor(UsuarioOutputPort usuarioOutputPort,
                                        GrupoFamiliarOutputPort grupoOutputPort) {
        this.usuarioOutputPort = usuarioOutputPort;
        this.grupoOutputPort = grupoOutputPort;
    }

    @Override
    public GrupoFamiliar ejecutar(CrearGrupoCommand command) {

        // CA-01-A y CA-01-B HU-02: verificar rol
        Usuario usuario = usuarioOutputPort
            .buscarPorId(command.getJefeId())
            .orElseThrow(AccesoDenegadoException::new);

        if (!(usuario instanceof JefeDeHogar)) {
            throw new AccesoDenegadoException();
        }

        // CA-03-A HU-02: generar código único — responsabilidad del Interactor
        String codigoUnico = "GRP-" + (int)(Math.random() * 900000 + 100000);

        // La validación del nombre ocurre en el constructor de GrupoFamiliar
        // CA-02-A, CA-02-B, CA-02-C HU-02
        GrupoFamiliar grupo = new GrupoFamiliar(
            command.getNombre(),
            codigoUnico
        );

        return grupoOutputPort.guardar(grupo);
    }
}