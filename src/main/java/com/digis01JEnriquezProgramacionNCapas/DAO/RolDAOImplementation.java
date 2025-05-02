package com.digis01JEnriquezProgramacionNCapas.DAO;

import com.digis01JEnriquezProgramacionNCapas.JPA.Result;
import com.digis01JEnriquezProgramacionNCapas.JPA.Rol;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RolDAOImplementation implements IRolDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();
        try {
            TypedQuery<Rol> queryRol = entityManager.createQuery("FROM Rol", Rol.class);
            List<Rol> listaroles = queryRol.getResultList();
            result.objects = new ArrayList<>();

            for (Rol rolJPA : listaroles) {
                Rol rol = new Rol();
                rol = rolJPA;
                result.objects.add(rol);
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
