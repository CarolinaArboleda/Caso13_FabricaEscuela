package sistema_organizacion.sistema.adapters.dto.request;

public class ModificarTareaRequest {
    private String idTarea;
    private String titulo;
    private String descripcion;
    private String estado;
    private String fechaLimite;

    public String getIdTarea()                { return idTarea; }
    public void setIdTarea(String idTarea)    { this.idTarea = idTarea; }

    public String getTitulo()                 { return titulo; }
    public void setTitulo(String titulo)      { this.titulo = titulo; }

    public String getDescripcion()            { return descripcion; }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado()                 { return estado; }
    public void setEstado(String estado)      { this.estado = estado; }

    public String getFechaLimite()            { return fechaLimite; }
    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
}
