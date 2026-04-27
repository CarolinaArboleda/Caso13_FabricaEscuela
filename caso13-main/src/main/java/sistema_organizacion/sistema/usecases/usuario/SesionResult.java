package sistema_organizacion.sistema.usecases.usuario;

import sistema_organizacion.sistema.entities.RolUsuario;
import sistema_organizacion.sistema.entities.Usuario;

public class SesionResult {
    private final Usuario usuario;
    private final RolUsuario rol;
    private final boolean tienGrupo;       // CA-02 HU-10: miembro sin hogar
    private final String redireccion;

    public SesionResult(Usuario usuario, boolean tieneGrupo) {
        this.usuario = usuario;
        this.rol = usuario.getRol();
        this.tienGrupo = tieneGrupo;

        // CA-01-A HU-10: ADMIN → panel principal
        // CA-01-B HU-10: USER con grupo → panel de tareas
        // CA-02   HU-10: USER sin grupo → búsqueda de hogar
        if (rol == RolUsuario.ADMIN) {
            this.redireccion = "/panel/admin";
        } else if (tieneGrupo) {
            this.redireccion = "/panel/tareas";
        } else {
            this.redireccion = "/grupo/buscar";
        }
    }

    public Usuario getUsuario()      { return usuario; }
    public RolUsuario getRol()       { return rol; }
    public boolean isTieneGrupo()    { return tienGrupo; }
    public String getRedireccion()   { return redireccion; }
}