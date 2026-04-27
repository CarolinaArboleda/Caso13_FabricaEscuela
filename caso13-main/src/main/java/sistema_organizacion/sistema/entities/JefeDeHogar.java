package sistema_organizacion.sistema.entities;

import java.time.LocalDate;

import sistema_organizacion.sistema.entities.exception.NombreGrupoInvalidoException;

public class JefeDeHogar extends Usuario {

    public JefeDeHogar(String id, String nombre, String apellido,
                       String correo, String username, String contrasena) {
        super(id, nombre, apellido, correo, username,
              contrasena, RolUsuario.ADMIN);
    }

    //  verificar si puede administrar
    // Los interactors lo llaman para hacer la comprobación de rol
    public boolean esAdministrador() {
        return this.getRol() == RolUsuario.ADMIN;
    }
}