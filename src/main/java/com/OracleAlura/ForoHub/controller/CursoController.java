package com.OracleAlura.ForoHub.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.OracleAlura.ForoHub.domain.curso.*;


@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Curso", description = "Puede pertenecer a una de las muchas categorías definidas")
public class CursoController {

    @Autowired
    private CursoRepository repository;


    //Crear un Topico
    @PostMapping
    @Transactional
    @Operation(summary = "Registrar un nuevo curso en la BD.")
    public ResponseEntity<DetalleCursoDTO> crearTopico(@RequestBody @Valid CrearCursoDTO crearCursoDTO,
                                                       UriComponentsBuilder uriBuilder){
        Curso curso = new Curso(crearCursoDTO);
        repository.save(curso);
        var uri = uriBuilder.path("/cursos/{i}").buildAndExpand(curso.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalleCursoDTO(curso));

    }
    //muestra cursos
    @GetMapping("/all")
    @Operation(summary = "Lee todos los cursos independientemente de su estado")
    public ResponseEntity<Page<DetalleCursoDTO>> listarCursos(@PageableDefault(size = 5, sort = {"id"})Pageable pageable){
        var pagina = repository.findAll(pageable).map(DetalleCursoDTO::new);
        return ResponseEntity.ok(pagina);
    }
    //Mostrar cursos Activo
    @GetMapping
    @Operation(summary = "Lista de cursos activos")
    public ResponseEntity<Page<DetalleCursoDTO>> listarCursosActivos(@PageableDefault(size = 5, sort = {"id"}) Pageable pageable){
        var pagina = repository.findAllByActivoTrue(pageable).map(DetalleCursoDTO::new);
        return ResponseEntity.ok(pagina);
    }
    //Mostrar curso por Id
    @GetMapping("/{id}")
    @Operation(summary = "Lee un solo curso por su ID")
    public ResponseEntity<DetalleCursoDTO> ListarUnCurso(@PathVariable Long id){
        Curso curso = repository.getReferenceById(id);
        var datosDelCurso = new DetalleCursoDTO(
                curso.getId(),
                curso.getName(),
                curso.getCategoria(),
                curso.getActivo()
        );
        return ResponseEntity.ok(datosDelCurso);
    }

    //Actualizar un Curso

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualiza el nombre, la categoría o el estado de un curso")
    public ResponseEntity<DetalleCursoDTO> actualizarCurso(@RequestBody @Valid ActualizarCursoDTO actualizarCursoDTO, @PathVariable Long id){

        Curso curso = repository.getReferenceById(id);

        curso.actualizarCurso(actualizarCursoDTO);

        var datosDelCurso = new DetalleCursoDTO(
                curso.getId(),
                curso.getName(),
                curso.getCategoria(),
                curso.getActivo()
        );
        return ResponseEntity.ok(datosDelCurso);
    }

    //Eliminar curso - Cambia el estado de activo true a false.
    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Elimina un curso")
    public ResponseEntity<?> eliminarCurso(@PathVariable Long id){
        Curso curso = repository.getReferenceById(id);
        curso.eliminarCurso();
        return ResponseEntity.noContent().build();
    }

}

