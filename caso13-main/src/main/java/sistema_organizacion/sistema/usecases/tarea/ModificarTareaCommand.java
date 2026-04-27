package sistema_organizacion.sistema.usecases.tarea;

import java.time.LocalDate;

public class ModificarTareaCommand {
    private final String tareaId;
    private final String jefeId;
    private final String nuevoTitulo;
    private final String nuevaDescripcion;
    private final LocalDate nuevaFechaLimite;

    public ModificarTareaCommand(String tareaId, String jefeId,
                                  String nuevoTitulo, String nuevaDescripcion,
                                  LocalDate nuevaFechaLimite) {
        this.tareaId = tareaId;
        this.jefeId = jefeId;
        this.nuevoTitulo = nuevoTitulo;
        this.nuevaDescripcion = nuevaDescripcion;
        this.nuevaFechaLimite = nuevaFechaLimite;
    }

    public String getTareaId()           { return tareaId; }
    public String getJefeId()            { return jefeId; }
    public String getNuevoTitulo()       { return nuevoTitulo; }
    public String getNuevaDescripcion()  { return nuevaDescripcion; }
    public LocalDate getNuevaFechaLimite() { return nuevaFechaLimite; }
}