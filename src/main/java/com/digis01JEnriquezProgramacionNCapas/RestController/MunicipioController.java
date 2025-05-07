package com.digis01JEnriquezProgramacionNCapas.RestController;

import com.digis01JEnriquezProgramacionNCapas.DAO.MunicipioDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/municipio")
public class MunicipioController {

    @Autowired
    MunicipioDAOImplementation municipioDAOImplementation;

    @GetMapping("byIdEstado/{IdEstado}")
    public ResponseEntity GetByIdEstado(@PathVariable int IdEstado) {
        Result result = municipioDAOImplementation.MunicipioByIdEstado(IdEstado);

        if (result.correct == true) {
            if (result.objects.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(result);
            }
        } else {
            return ResponseEntity.internalServerError().body(result.errorMessage);
        }
    }
}
