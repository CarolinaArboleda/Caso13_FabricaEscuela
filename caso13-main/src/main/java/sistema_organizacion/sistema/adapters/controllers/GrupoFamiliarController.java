package sistema_organizacion.sistema.adapters.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sistema_organizacion.sistema.adapters.dto.request.CrearGrupoRequest;
import sistema_organizacion.sistema.adapters.dto.request.IngresarGrupoRequest;
import sistema_organizacion.sistema.adapters.dto.response.GrupoFamiliarResponse;
import sistema_organizacion.sistema.adapters.presenters.GrupoFamiliarPresenter;
import sistema_organizacion.sistema.entities.GrupoFamiliar;
import sistema_organizacion.sistema.usecases.grupo.CrearGrupoCommand;
import sistema_organizacion.sistema.usecases.grupo.CrearGrupoFamiliarUseCase;
import sistema_organizacion.sistema.usecases.grupo.IngresarGrupoCommand;
import sistema_organizacion.sistema.usecases.grupo.IngresarGrupoFamiliarUseCase;

@RestController
@RequestMapping("/api/grupos")
public class GrupoFamiliarController {

    private final CrearGrupoFamiliarUseCase crearGrupoFamiliarUseCase;
    private final IngresarGrupoFamiliarUseCase ingresarGrupoFamiliarUseCase;
    private final GrupoFamiliarPresenter presenter;

    public GrupoFamiliarController(
            CrearGrupoFamiliarUseCase crearGrupoFamiliarUseCase,
            IngresarGrupoFamiliarUseCase ingresarGrupoFamiliarUseCase,
            GrupoFamiliarPresenter presenter) {
        this.crearGrupoFamiliarUseCase = crearGrupoFamiliarUseCase;
        this.ingresarGrupoFamiliarUseCase = ingresarGrupoFamiliarUseCase;
        this.presenter = presenter;
    }

    @PostMapping
    public ResponseEntity<GrupoFamiliarResponse> crear(
            @RequestBody CrearGrupoRequest request,
            @RequestHeader("X-Usuario-Id") String jefeId) {

        CrearGrupoCommand command = new CrearGrupoCommand(
            request.getNombre(), jefeId
        );
        GrupoFamiliar grupo = crearGrupoFamiliarUseCase.ejecutar(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(presenter.toResponse(grupo));
    }

    @PostMapping("/ingresar")
    public ResponseEntity<GrupoFamiliarResponse> ingresar(
            @RequestHeader("X-Usuario-Id") String miembroId,
            @RequestBody IngresarGrupoRequest request) {

        IngresarGrupoCommand command = new IngresarGrupoCommand(
            miembroId,
            request.getCodigoAcceso()
        );
        GrupoFamiliar grupo = ingresarGrupoFamiliarUseCase.ejecutar(command);
        return ResponseEntity.ok(presenter.toResponse(grupo));
    }
}