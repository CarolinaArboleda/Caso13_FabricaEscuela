package sistema_organizacion.sistema.entities;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sistema_organizacion.sistema.entities.exception.MiembroYaEnGrupoException;
import sistema_organizacion.sistema.entities.exception.NombreGrupoInvalidoException;

public class GrupoFamiliar {
    private Long id;
    private String nombre;
    private String codigoAcceso;
    private Long jefeId;
    private LocalDate fechaCreacion;
    private List<MiembroHogar> miembros;

    public GrupoFamiliar(Long id, String nombre,
                    String codigoAcceso, Long jefeId) {
        // el nombre debe ser válido
        validarNombre(nombre);
        this.id = id;
        this.nombre = nombre;
        this.codigoAcceso = codigoAcceso;
        this.jefeId = jefeId;
        this.fechaCreacion = LocalDate.now();
        this.miembros = new ArrayList<>();
    }

    
    private void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().length() < 6
                || nombre.trim().length() > 25) {
            throw new NombreGrupoInvalidoException(
                "El nombre debe tener entre 6 y 25 caracteres"
            );
        }
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ0-9 ]+")) {
            throw new NombreGrupoInvalidoException(
                "El nombre solo permite letras, números, espacios, tildes y ñ"
            );
        }
    }

    // no puede haber miembros duplicados
    public void agregarMiembro(MiembroHogar miembro) {
        boolean yaExiste = miembros.stream()
            .anyMatch(m -> m.getId().equals(miembro.getId()));
        if (yaExiste) {
            throw new MiembroYaEnGrupoException(
                "El miembro ya pertenece al grupo"
            );
        }
        miembros.add(miembro);
    }

    public Long getId()                 { return id; }
    public String getNombre()           { return nombre; }
    public String getCodigoAcceso()     { return codigoAcceso; }
    public Long getJefeId()             { return jefeId; }
    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public List<MiembroHogar> getMiembros() {
        return Collections.unmodifiableList(miembros);
    }
    public void setId(Long id)          { this.id = id; }
}