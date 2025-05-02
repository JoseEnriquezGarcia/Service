package com.digis01JEnriquezProgramacionNCapas.DAO;

import com.digis01JEnriquezProgramacionNCapas.JPA.Colonia;
import com.digis01JEnriquezProgramacionNCapas.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ColoniaDAOImplementation implements IColoniaDAO {

    @Autowired
    private EntityManager entityManager;
    
    @Override
    public Result ColoniaByIdMunicipio(int IdMunicipio) {
        Result result = new Result();

        try {
            TypedQuery<Colonia> queryColonias = entityManager.createQuery("FROM Colonia WHERE Municipio.IdMunicipio = :idmunicipio", Colonia.class);
            queryColonias.setParameter("idmunicipio", IdMunicipio);
            List<Colonia> listaColonias = queryColonias.getResultList();

            result.objects = new ArrayList<>();

            for (Colonia coloniaJPA : listaColonias) {
                Colonia colonia = new Colonia();
                colonia = coloniaJPA;
                result.objects.add(colonia);
            }

            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result ColoniaByCP(String CodigoPostal) {
        Result result = new Result();
        try {
            TypedQuery<Colonia> queryColonias = entityManager.createQuery("FROM Colonia WHERE CodigoPostal = :codigopostal", Colonia.class);
            queryColonias.setParameter("codigopostal", CodigoPostal);
            List<Colonia> listaColonias = queryColonias.getResultList();
            result.objects = new ArrayList<>();
            
            for (Colonia coloniaJPA : listaColonias) {
                Colonia colonia = new Colonia();
                colonia = coloniaJPA;
                result.objects.add(colonia);
            }

            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.objects = null;
        }

        return result;
    }
}
