package sistema_organizacion.sistema.usecases.grupo;

public class IngresarGrupoCommand {
    private final String miembroId;
    private final String codigoAcceso;

    public IngresarGrupoCommand(String miembroId, String codigoAcceso) {
        this.miembroId = miembroId;
        this.codigoAcceso = codigoAcceso;
    }

    public String getMiembroId()    { return miembroId; }
    public String getCodigoAcceso() { return codigoAcceso; }
}