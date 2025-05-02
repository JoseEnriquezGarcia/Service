package com.digis01JEnriquezProgramacionNCapas.RestController;

import com.digis01JEnriquezProgramacionNCapas.DAO.ColoniaDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.DAO.DireccionDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.DAO.EstadoDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.DAO.MunicipioDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.DAO.PaisDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.DAO.RolDAOImplementation;

import com.digis01JEnriquezProgramacionNCapas.DAO.UsuarioDAOImplementation;
import com.digis01JEnriquezProgramacionNCapas.JPA.Direccion;
import com.digis01JEnriquezProgramacionNCapas.JPA.Result;
import com.digis01JEnriquezProgramacionNCapas.JPA.Usuario;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    UsuarioDAOImplementation usuarioDAOImplementation;
    @Autowired
    RolDAOImplementation rolDAOImplementation;
    @Autowired
    DireccionDAOImplementation direccionDAOImplementation;
    @Autowired
    ColoniaDAOImplementation coloniaDAOImplementation;
    @Autowired
    MunicipioDAOImplementation municipioDAOImplementation;
    @Autowired
    EstadoDAOImplementation estadoDAOImplementation;
    @Autowired
    PaisDAOImplementation paisDAOImplementation;
    
    
    @GetMapping()
    public ResponseEntity GetAll(){
        Result result = usuarioDAOImplementation.GetAll();
        
        if(result.correct == true){
            if(result.objects.isEmpty()){
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.ok(result);
            }
        }else{
            return ResponseEntity.internalServerError().body(result.errorMessage);
        }
    }
    
    @GetMapping("GetAllById/{IdUsuario}")
    public ResponseEntity GetUsuarioDireccionById(@PathVariable int IdUsuario){
        Result result = usuarioDAOImplementation.GetUsuarioDireccionById(IdUsuario);
        
        if(result.correct == true){
            if(result.object == null){
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.ok(result);
            }
        }else{
            return ResponseEntity.internalServerError().body(result.errorMessage);
        }
    }
    
    @GetMapping("GetById")
    public ResponseEntity GetUsuarioById(@RequestParam int IdUsuario){
        Result result = usuarioDAOImplementation.GetUsuarioById(IdUsuario);
        
        if(result.correct == true){
            if(result.object == null){
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.ok(result);
            }
        }else{
            return ResponseEntity.internalServerError().body(result.errorMessage);
        }
    }
    
    @PostMapping("AddUsuarioDireccion")
    public ResponseEntity AddUsuario(@RequestBody UsuarioDireccion usuarioDireccion){
        Result result = usuarioDAOImplementation.AddUsuario(usuarioDireccion);
        
        if(result.correct == true){
            return ResponseEntity.status(201).body("Agregado Correctamente");
        }else{
            return ResponseEntity.badRequest().body(result.errorMessage);
        }
    }
    
    @PatchMapping("UpdateUsuario")
    public ResponseEntity UpdateUsuario(@RequestBody Usuario usuario){
        Result result = usuarioDAOImplementation.UsuarioUpdate(usuario);
        
        if(result.correct == true){
            return ResponseEntity.status(201).body("Actualizado Correctamente");
        }else{
            return ResponseEntity.badRequest().body(result.errorMessage);
        }
    }
    
    @PatchMapping("UpdateStatus/{IdUsuario}/{Status}")
    public ResponseEntity UpdateStatus(@PathVariable int IdUsuario, @PathVariable int Status){
        Result result = usuarioDAOImplementation.UpdateStatus(IdUsuario, Status);
        
        if(result.correct == true){
            return ResponseEntity.ok().body("Actualizado Correctamente");
        }else{
            return ResponseEntity.badRequest().body(result.errorMessage);
        }
    }
    
    @DeleteMapping("DeleteUsuario/{IdUsuario}")
    public ResponseEntity DeleteUsuarioDireccion(@PathVariable int IdUsuario){
        Result result = usuarioDAOImplementation.DeleteUsuarioDireccion(IdUsuario);
        
        if(result.correct == true){
            return ResponseEntity.ok("Eliminado Correctamente");
        }else{
            return ResponseEntity.badRequest().body(result.errorMessage);
        }
    }
    
    @PostMapping("GetAllDinamico")
    public ResponseEntity GetAllDinamico(@RequestBody Usuario usuario){
        Result result = usuarioDAOImplementation.GetAllDinamico(usuario);
        if(result.correct == true){
            if(result.objects.isEmpty()){
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.ok(result);
            }
        }else{
            return ResponseEntity.internalServerError().body(result.errorMessage);
        }
    }
    @GetMapping("GetAllRol")
    public ResponseEntity GetAllRol(){
        Result result = rolDAOImplementation.GetAll();
        
        if(result.correct == true){
            if(result.objects.isEmpty()){
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.ok(result);
            }
        }else{
            return ResponseEntity.internalServerError().body(result.errorMessage);
        }
    }
    
    
    @GetMapping("GetByIdDireccion/{IdDireccion}")
    public ResponseEntity GetDireccionByIdDireccion(@PathVariable int IdDireccion){
        Result result = direccionDAOImplementation.GetDireccionByIdDireccion(IdDireccion);
        
        if(result.correct == true){
            if(result.object == null){
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.ok(result);
            }
        }else{
            return ResponseEntity.internalServerError().body(result.errorMessage);
        }
    }
    
    @PostMapping("AddDireccion")
    public ResponseEntity AddDireccion(@RequestBody UsuarioDireccion usuarioDireccion){
        Result result = direccionDAOImplementation.DireccionAdd(usuarioDireccion);
        
        if(result.correct){
            return ResponseEntity.status(201).body("Agregado Correctamente");
        }else{
            return ResponseEntity.badRequest().body(result.errorMessage);
        }
    }
    
    @PatchMapping("UpdateDireccion")
    public ResponseEntity UpdateDireccion(@RequestBody Direccion direccion){
        Result result = direccionDAOImplementation.DireccionUpdate(direccion);
        
        if(result.correct){
            return ResponseEntity.status(201).body("Actualizado Correctamente");
        }else{
            return ResponseEntity.badRequest().body(result.errorMessage);
        }
    }
    
    @DeleteMapping("DeleteDireccion/{IdDireccion}")
    public ResponseEntity DeleteDireccion(@PathVariable int IdDireccion){
        Result result = direccionDAOImplementation.DireccionDelete(IdDireccion);
        
        if (result.correct) {
            return ResponseEntity.ok("Eliminado Correctamente");
        }else{
            return ResponseEntity.badRequest().body(result.errorMessage);
        }
    }
    
    @GetMapping("GetColoniaById/{IdMunicipio}")
    public ResponseEntity GetColoniaByIdMunicipio(@PathVariable int IdMunicipio){
        Result result = coloniaDAOImplementation.ColoniaByIdMunicipio(IdMunicipio);
        
        if(result.correct == true){
            if(result.objects.isEmpty()){
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.ok().body(result);
            }
        }else{
            return ResponseEntity.internalServerError().body(result.errorMessage);
        }
    }
    
    @GetMapping("GetColoniaByCp/{CP}")
    public ResponseEntity GetColoniaByCodigoPostal(@PathVariable String CP){
        Result result = coloniaDAOImplementation.ColoniaByCP(CP);
        
        if(result.correct == true){
            if(result.objects.isEmpty()){
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.ok().body(result);
            }
        }else{
            return ResponseEntity.internalServerError().body(result.errorMessage);
        }
    }
    
    @GetMapping("MunicipioById/{IdEstado}")
    public ResponseEntity GetMunicipioByIdEstado(@PathVariable int IdEstado){
        Result result = municipioDAOImplementation.MunicipioByIdEstado(IdEstado);
        
        if(result.correct == true){
            if(result.objects.isEmpty()){
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.ok(result);
            }
        }else{
            return ResponseEntity.internalServerError().body(result.errorMessage);
        }
    }
    
    @GetMapping("EstadoById/{IdPais}")
    public ResponseEntity GetEstadoByIdPais(@PathVariable int IdPais){
        Result result = estadoDAOImplementation.EstadoByIdPais(IdPais);
        
        if(result.correct == true){
            if(result.objects.isEmpty()){
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.ok(result);
            }
        }else{
            return ResponseEntity.internalServerError().body(result.errorMessage);
        }
    }
    
    @GetMapping("GetAllPais")
    public ResponseEntity GetAllPais(){
        Result result = paisDAOImplementation.GetAll();
        
        if(result.correct == true){
            if(result.objects.isEmpty()){
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.ok(result);
            }
        }else{
            return ResponseEntity.internalServerError().body(result.errorMessage);
        }
    }
}
