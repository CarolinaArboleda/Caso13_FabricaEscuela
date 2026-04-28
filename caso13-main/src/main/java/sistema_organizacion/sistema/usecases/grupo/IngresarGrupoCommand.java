package sistema_organizacion.sistema.usecases.grupo;

public class IngresarGrupoCommand {
    private final Long miembroId;
    private final String codigoAcceso;

    public IngresarGrupoCommand(Long miembroId, String codigoAcceso) {
        this.miembroId = miembroId;
        this.codigoAcceso = codigoAcceso;
    }

    public Long getMiembroId()      { return miembroId; }
    public String getCodigoAcceso() { return codigoAcceso; }
}