package com.digis01JEnriquezProgramacionNCapas.RestController;

import com.digis01JEnriquezProgramacionNCapas.DAO.DireccionDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.JPA.Direccion;
import com.digis01JEnriquezProgramacionNCapas.JPA.Result;
import com.digis01JEnriquezProgramacionNCapas.JPA.UsuarioDireccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/direccion")
public class DireccionController {

    @Autowired
    DireccionDAOImplementation direccionDAOImplementation;

    @GetMapping("getById/{IdDireccion}")
    public ResponseEntity GetById(@PathVariable int IdDireccion) {
        Result result = direccionDAOImplementation.GetDireccionByIdDireccion(IdDireccion);

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

    @PostMapping("add")
    public ResponseEntity Add(@RequestBody UsuarioDireccion usuarioDireccion) {
        Result result = direccionDAOImplementation.DireccionAdd(usuarioDireccion);

        if (result.correct) {
            return ResponseEntity.status(201).body("Agregado Correctamente");
        } else {
            return ResponseEntity.badRequest().body(result.errorMessage);
        }
    }

    @PatchMapping("update")
    public ResponseEntity Update(@RequestBody Direccion direccion) {
        Result result = direccionDAOImplementation.DireccionUpdate(direccion);

        if (result.correct) {
            return ResponseEntity.status(201).body("Actualizado Correctamente");
        } else {
            return ResponseEntity.badRequest().body(result.errorMessage);
        }
    }

    @DeleteMapping("delete/{IdDireccion}")
    public ResponseEntity Delete(@PathVariable int IdDireccion) {
        Result result = direccionDAOImplementation.DireccionDelete(IdDireccion);

        if (result.correct) {
            return ResponseEntity.ok("Eliminado Correctamente");
        } else {
            return ResponseEntity.badRequest().body(result.errorMessage);
        }
    }
}
