package sistema_organizacion.sistema.adapters.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema_organizacion.sistema.adapters.dto.request.CrearTareaRequest;
import sistema_organizacion.sistema.adapters.dto.request.ModificarTareaRequest;
import sistema_organizacion.sistema.adapters.dto.response.ModificarTareaResponse;
import sistema_organizacion.sistema.adapters.dto.response.TareaResponse;
import sistema_organizacion.sistema.adapters.presenters.TareaPresenter;
import sistema_organizacion.sistema.entities.Tarea;
import sistema_organizacion.sistema.usecases.tarea.CrearTareaCommand;
import sistema_organizacion.sistema.usecases.tarea.CrearTareaUseCase;
import sistema_organizacion.sistema.usecases.tarea.ModificarTareaCommand;
import sistema_organizacion.sistema.usecases.tarea.ModificarTareaUseCase;
import sistema_organizacion.sistema.usecases.tarea.VerDetalleTareaUseCase;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final CrearTareaUseCase crearTareaUseCase;
    private final ModificarTareaUseCase modificarTareaUseCase;
    private final VerDetalleTareaUseCase verDetalleTareaUseCase;
    private final TareaPresenter presenter;

    public TareaController(CrearTareaUseCase crearTareaUseCase,
                            ModificarTareaUseCase modificarTareaUseCase,
                            VerDetalleTareaUseCase verDetalleTareaUseCase,
                            TareaPresenter presenter) {
        this.crearTareaUseCase = crearTareaUseCase;
        this.modificarTareaUseCase = modificarTareaUseCase;
        this.verDetalleTareaUseCase = verDetalleTareaUseCase;
        this.presenter = presenter;
    }

    // HU-11: crear tarea
    @PostMapping
    public ResponseEntity<TareaResponse> crear(
            @RequestBody CrearTareaRequest request,
            @RequestHeader("X-Usuario-Id") String jefeId) {

        CrearTareaCommand command = new CrearTareaCommand(
            request.getTitulo(),
            request.getDescripcion(),
            request.getFechaLimite(),
            request.getGrupoId(),
            jefeId
        );
        Tarea tarea = crearTareaUseCase.ejecutar(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(presenter.toResponse(tarea));
    }

    // HU-13: modificar tarea
    @PutMapping("/{tareaId}")
    public ResponseEntity<ModificarTareaResponse> modificar(
            @PathVariable String tareaId,
            @RequestHeader("X-Usuario-Id") String jefeId,
            @RequestBody ModificarTareaRequest request) {

        ModificarTareaCommand command = new ModificarTareaCommand(
            tareaId,
            jefeId,
            request.getTitulo(),
            request.getDescripcion(),
            request.getFechaLimite() != null ? java.time.LocalDate.parse(request.getFechaLimite()) : null
        );

        Tarea tarea = modificarTareaUseCase.ejecutar(command);
        ModificarTareaResponse response = new ModificarTareaResponse();
        response.setMensaje("La tarea se ha modificado correctamente");
        response.setTarea(presenter.toResponse(tarea));
        return ResponseEntity.ok(response);
    }

    // HU-12: ver detalle de la tarea
    @GetMapping("/{tareaId}")
    public ResponseEntity<TareaResponse> verDetalle(
            @PathVariable String tareaId,
            @RequestParam String grupoId) {

        Tarea tarea = verDetalleTareaUseCase.ejecutar(tareaId, grupoId);
        return ResponseEntity.ok(presenter.toResponse(tarea));
    }
}