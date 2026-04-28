package sistema_organizacion.sistema.entities;

import sistema_organizacion.sistema.entities.exception.ContrasenaInvalidaException;
import sistema_organizacion.sistema.entities.exception.CorreoInvalidoException;

public abstract class Usuario {
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String username;
    private String contrasena;
    private RolUsuario rol;

    public Usuario(Long id, String nombre, String apellido,
                   String correo, String username,
                   String contrasena, RolUsuario rol) {
        validarCorreo(correo);
        validarContrasena(contrasena);
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.username = username;
        this.contrasena = contrasena;
        this.rol = rol;
    }
 //  validar formato del correo
//    private void validarCorreo(String correo) {
  //      if (correo == null || !correo.matches("^[a-zA-Z0-9._%+\\-]+@gmail\\.com$")) {
    //        throw new CorreoInvalidoException(
      //          "El correo electrónico debe ser una cuenta @gmail.com válida"
      //      );
       // }
    //}

    private void validarCorreo(String correo) {
    System.out.println("=== VALIDANDO CORREO ===");
    System.out.println("Valor recibido: [" + correo + "]");
    System.out.println("Longitud: " + (correo == null ? "null" : correo.length()));

    if (correo == null || !correo.trim().matches("^[a-zA-Z0-9._%+\\-]+@gmail\\.com$")) {
        throw new CorreoInvalidoException(
            "El correo electrónico debe ser una cuenta @gmail.com válida"
        );
    }
}

    // validar formato de contraseña
    private void validarContrasena(String contrasena) {
        if (contrasena == null || contrasena.length() > 10
                || !contrasena.matches("[a-zA-Z0-9@#$%&*]+")) {
            throw new ContrasenaInvalidaException(
                "La contraseña debe tener máximo 10 caracteres y solo "
                + "contener letras, números y símbolos permitidos (@, #, $, %, &, *)"
            );
        }
    }

    public Long getId()           { return id; }
    public String getNombre()     { return nombre; }
    public String getApellido()   { return apellido; }
    public String getCorreo()     { return correo; }
    public String getUsername()   { return username; }
    public String getContrasena() { return contrasena; }
    public RolUsuario getRol()    { return rol; }
    public String getNombreCompleto() { return nombre + " " + apellido; }
}