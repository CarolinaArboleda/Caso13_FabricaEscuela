package sistema_organizacion.sistema.entities;

import java.time.LocalDate;
import java.util.Arrays;

import sistema_organizacion.sistema.entities.exception.TareaInvalidaException;

public class Tarea {
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDate fechaLimite;
    private EstadoTarea estado;
    private Long grupoId;
    private Long miembroAsignadoId;
    private String nombreMiembroAsignado;

    public Tarea(Long id, String titulo, String descripcion,
            LocalDate fechaLimite, Long grupoId) {
        validarTitulo(titulo);
        validarDescripcion(descripcion);
        validarFechaLimite(fechaLimite);
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.grupoId = grupoId;
        this.estado = EstadoTarea.PENDIENTE;
    }

    // Cambio de estado propio de la entidad
    public void actualizar(String nuevoTitulo, String nuevaDescripcion,
                            LocalDate nuevaFechaLimite) {
        if (nuevoTitulo == null || nuevoTitulo.isBlank())
            throw new TareaInvalidaException("El nombre de la tarea es obligatorio");
        if (nuevaFechaLimite == null)
            throw new TareaInvalidaException("La fecha límite es obligatoria");
        validarTitulo(nuevoTitulo);
        validarDescripcion(nuevaDescripcion);
        validarFechaLimite(nuevaFechaLimite);
        this.titulo = nuevoTitulo;
        this.descripcion = nuevaDescripcion;
        this.fechaLimite = nuevaFechaLimite;
    }

    // Cambio de estado propio
    public void actualizarEstado(EstadoTarea nuevoEstado) {
        this.estado = nuevoEstado;
    }

    // Asignación es cambio de estado propio
    public void asignarMiembro(Long miembroId, String nombreMiembro) {
        this.miembroAsignadoId = miembroId;
        this.nombreMiembroAsignado = nombreMiembro;
    }

    private void validarTitulo(String titulo) {
        if (titulo == null || titulo.trim().length() < 3
                || titulo.trim().length() > 60)
            throw new TareaInvalidaException(
                "El nombre debe tener entre 3 y 60 caracteres");
        if (!titulo.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ0-9 ]+"))
            throw new TareaInvalidaException(
                "El nombre solo permite letras, números, espacios, tildes y ñ");
    }

    private void validarDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) return;
        long palabras = Arrays.stream(
            descripcion.trim().split("\\s+")).count();
        if (palabras > 100)
            throw new TareaInvalidaException(
                "La descripción no debe superar las 100 palabras");
    }

    private void validarFechaLimite(LocalDate fecha) {
        if (fecha == null || !fecha.isAfter(LocalDate.now()))
            throw new TareaInvalidaException(
                "La fecha límite no puede estar en el pasado");
    }

    public Long getId()                      { return id; }
    public String getTitulo()                { return titulo; }
    public String getDescripcion()           { return descripcion; }
    public LocalDate getFechaLimite()        { return fechaLimite; }
    public EstadoTarea getEstado()           { return estado; }
    public Long getGrupoId()                 { return grupoId; }
    public Long getMiembroAsignadoId()       { return miembroAsignadoId; }
    public String getNombreMiembroAsignado() { return nombreMiembroAsignado; }
    public void setId(Long id)               { this.id = id; }
}