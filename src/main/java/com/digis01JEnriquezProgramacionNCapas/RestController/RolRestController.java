package com.digis01JEnriquezProgramacionNCapas.RestController;

import com.digis01JEnriquezProgramacionNCapas.DAO.RolDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rol")
public class RolRestController {

    @Autowired
    RolDAOImplementation rolDAOImplementation;

    @GetMapping("getAll")
    public ResponseEntity GetAll() {
        Result result = rolDAOImplementation.GetAll();

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
