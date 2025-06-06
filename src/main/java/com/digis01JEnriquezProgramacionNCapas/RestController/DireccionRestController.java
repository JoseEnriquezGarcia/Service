package com.digis01JEnriquezProgramacionNCapas.RestController;

import com.digis01JEnriquezProgramacionNCapas.DAO.DireccionDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.JPA.Direccion;
import com.digis01JEnriquezProgramacionNCapas.JPA.Result;
import com.digis01JEnriquezProgramacionNCapas.JPA.UsuarioDireccion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/direccion")
public class DireccionRestController {

    @Autowired
    DireccionDAOImplementation direccionDAOImplementation;

    @Operation(summary = "Obtener dirección por Id", description = "Obtiene la dirección por su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación completada", content = {
            @Content(
                    schema = @Schema(implementation = Direccion.class)
            )
        }),
        @ApiResponse(responseCode = "204", description = "Sin resultados", content = {
            @Content(
                    schema = @Schema(implementation = Result.class)
            )
        }),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {
            @Content(
                    schema = @Schema(implementation = Result.class)
            )
        }),})
    @GetMapping("getById/{IdDireccion}")
    public ResponseEntity GetById(@PathVariable int IdDireccion) {
        Result result = direccionDAOImplementation.GetById(IdDireccion);

        if (result.correct == true) {
            if (result.object == null) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(result);
            }
        } else {
            return ResponseEntity.internalServerError().body(result.errorMessage);
        }
    }

    @Operation(summary = "Agregar una dirección", description = "Agrega una dirección a un usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación completada", content = {
            @Content(
                    schema = @Schema(implementation = Result.class)
            )
        }),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(
                    schema = @Schema(implementation = Result.class)
            )
        }),
    })
    @PostMapping("add")
    public ResponseEntity Add(@RequestBody UsuarioDireccion usuarioDireccion) {
        Result result = direccionDAOImplementation.Add(usuarioDireccion);

        if (result.correct) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result.errorMessage);
        }
    }
    
    @Operation(summary = "Actualizar una dirección", description = "Actualiza una dirección a un usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación completada", content = {
            @Content(
                    schema = @Schema(implementation = Result.class)
            )
        }),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(
                    schema = @Schema(implementation = Result.class)
            )
        }),
    })
    @PutMapping("update")
    public ResponseEntity Update(@RequestBody Direccion direccion) {
        Result result = direccionDAOImplementation.Update(direccion);

        if (result.correct) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result.errorMessage);
        }
    }
    
    @Operation(summary = "Elimina una dirección", description = "Elimina una dirección a un usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación completada", content = {
            @Content(
                    schema = @Schema(implementation = Result.class)
            )
        }),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
            @Content(
                    schema = @Schema(implementation = Result.class)
            )
        }),
    })
    @DeleteMapping("delete/{IdDireccion}")
    public ResponseEntity Delete(@PathVariable int IdDireccion) {
        Result result = direccionDAOImplementation.Delete(IdDireccion);

        if (result.correct) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result.errorMessage);
        }
    }
}
