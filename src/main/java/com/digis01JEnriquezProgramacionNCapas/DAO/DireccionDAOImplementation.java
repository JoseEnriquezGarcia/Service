package com.digis01JEnriquezProgramacionNCapas.DAO;


import com.digis01JEnriquezProgramacionNCapas.JPA.Usuario;
import com.digis01JEnriquezProgramacionNCapas.JPA.Direccion;
import com.digis01JEnriquezProgramacionNCapas.JPA.Result;
import com.digis01JEnriquezProgramacionNCapas.JPA.UsuarioDireccion;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DireccionDAOImplementation implements IDireccionDAO{
    
    
    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetById(int IdDireccion) {
        Result result = new Result();
        
        try {
            Direccion direccion = new Direccion();
            direccion = entityManager.find(Direccion.class, IdDireccion);
            result.object = direccion;
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.object = null;
        }
        return result;
    }
    
    @Transactional
    @Override
    public Result Add(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();
        
        try {
            usuarioDireccion.Direccion.Usuario = usuarioDireccion.Usuario;
            entityManager.persist(usuarioDireccion.Direccion);
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }
    
    @Transactional
    @Override
    public Result Update(Direccion direccion) {
        Result result  = new Result ();
        
        try {
            direccion.Usuario = new Usuario();
            Direccion direccionFind = new Direccion();
            direccionFind = entityManager.find(Direccion.class, direccion.getIdDireccion());
            direccion.Usuario.setIdUsuario(direccionFind.Usuario.getIdUsuario());
            entityManager.merge(direccion);
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }
    
    @Transactional
    @Override
    public Result Delete(int IdDireccion) {
        Result result = new Result();
        
        try {
            Direccion direccionJPA = new Direccion();
            direccionJPA = entityManager.find(Direccion.class, IdDireccion);
            entityManager.remove(direccionJPA);
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }
    
}
