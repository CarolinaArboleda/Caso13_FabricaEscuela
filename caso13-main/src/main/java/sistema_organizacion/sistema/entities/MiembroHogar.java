package sistema_organizacion.sistema.entities;

import sistema_organizacion.sistema.entities.exception.MiembroYaEnGrupoException;

public class MiembroHogar extends Usuario {
    private Long grupoFamiliarId;
    private EstadoMiembro estadoMiembro;

    public MiembroHogar(Long id, String nombre, String apellido,
                        String correo, String username, String contrasena) {
        super(id, nombre, apellido, correo, username,
                contrasena, RolUsuario.USER);
        this.estadoMiembro = EstadoMiembro.SIN_GRUPO;
    }

    //  un miembro no puede estar en dos grupos
    public void asignarGrupo(Long grupoId) {
        if (this.grupoFamiliarId != null) {
            throw new MiembroYaEnGrupoException(
                "Ya perteneces a un grupo familiar"
            );
        }
        this.grupoFamiliarId = grupoId;
        this.estadoMiembro = EstadoMiembro.ACTIVO;
    }

    public boolean tieneGrupo()               { return grupoFamiliarId != null; }
    public Long getGrupoFamiliarId()          { return grupoFamiliarId; }
    public EstadoMiembro getEstadoMiembro()   { return estadoMiembro; }
}