package sistema_organizacion.sistema.adapters.dto.response;

public class UsuarioResponse {
    private String id;
    private String nombreCompleto;
    private String correo;
    private String username;
    private String rol;

    public String getId()                   { return id; }
    public void setId(String id)            { this.id = id; }
    public String getNombreCompleto()       { return nombreCompleto; }
    public void setNombreCompleto(String n) { this.nombreCompleto = n; }
    public String getCorreo()               { return correo; }
    public void setCorreo(String c)         { this.correo = c; }
    public String getUsername()             { return username; }
    public void setUsername(String u)       { this.username = u; }
    public String getRol()                  { return rol; }
    public void setRol(String r)            { this.rol = r; }
}