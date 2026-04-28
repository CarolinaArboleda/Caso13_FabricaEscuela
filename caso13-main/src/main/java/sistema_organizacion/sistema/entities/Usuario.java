package sistema_organizacion.sistema.entities;

import sistema_organizacion.sistema.entities.exception.ContrasenaInvalidaException;
import sistema_organizacion.sistema.entities.exception.CorreoInvalidoException;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")

public abstract class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "correo", unique = true)
    private String correo;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "contrasena")
    private String contrasena;
    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private RolUsuario rol;

    @ManyToOne
    @JoinColumn(name = "id_grupo")
    private GrupoFamiliar grupo;


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