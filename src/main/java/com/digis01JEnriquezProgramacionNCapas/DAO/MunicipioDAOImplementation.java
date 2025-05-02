package com.digis01JEnriquezProgramacionNCapas.DAO;

import com.digis01JEnriquezProgramacionNCapas.JPA.Municipio;
import com.digis01JEnriquezProgramacionNCapas.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MunicipioDAOImplementation implements IMunicipioDAO{
    @Autowired
    private EntityManager entityManager;

    @Override
    public Result MunicipioByIdEstado(int IdEstado) {
        Result result = new Result();
        
        try {
            TypedQuery<Municipio> queryMunicipio = entityManager.createQuery("FROM Municipio WHERE Estado.IdEstado = :IdEstado", Municipio.class);
            queryMunicipio.setParameter("IdEstado", IdEstado);
            List<Municipio> listaMunicipios = queryMunicipio.getResultList();
            result.objects = new ArrayList<>();
            
            for (Municipio municipioJPA : listaMunicipios) {
                Municipio municipio = new Municipio();
                municipio = municipioJPA;
                result.objects.add(municipio);
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
