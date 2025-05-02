package com.digis01JEnriquezProgramacionNCapas.DAO;

import com.digis01JEnriquezProgramacionNCapas.JPA.Estado;
import com.digis01JEnriquezProgramacionNCapas.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoDAOImplementation implements IEstadoDAO{
    
    @Autowired
    private EntityManager entityManager;

    @Override
    public Result EstadoByIdPais(int IdPais) {
        Result result = new Result();
        
        try {
            TypedQuery<Estado> queryEstado = entityManager.createQuery("FROM Estado WHERE Pais.IdPais = :idpais", Estado.class);
            queryEstado.setParameter("idpais", IdPais);
            List<Estado> listaEstados = queryEstado.getResultList();
            result.objects = new ArrayList<>();
            
            for (Estado estadoJPA : listaEstados) {
                Estado estado = new Estado();
                estado = estadoJPA;
                result.objects.add(estado);
            }
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
    
}
