package com.digis01JEnriquezProgramacionNCapas.DAO;

import com.digis01JEnriquezProgramacionNCapas.JPA.Direccion;
import com.digis01JEnriquezProgramacionNCapas.JPA.Result;
import com.digis01JEnriquezProgramacionNCapas.JPA.Usuario;
import com.digis01JEnriquezProgramacionNCapas.JPA.UsuarioDireccion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImplementation implements IUsuarioDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();
        try {
            TypedQuery<Usuario> queryUsuarios = entityManager.createQuery("SELECT u FROM Usuario u ORDER BY u.IdUsuario ASC", Usuario.class);
            List<Usuario> listaUsuarios = queryUsuarios.getResultList();
            result.objects = new ArrayList<>();
            
            for (Usuario usuarioJPA : listaUsuarios) {
                UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
                usuarioDireccion.Usuario = new Usuario();    
                usuarioDireccion.Usuario = usuarioJPA;
                
                TypedQuery<Direccion> queryDireccion = entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario = :idusuario", Direccion.class);
                queryDireccion.setParameter("idusuario", usuarioJPA.getIdUsuario());
                List<Direccion> listaDirecciones = queryDireccion.getResultList();
                usuarioDireccion.Direcciones = new ArrayList<>();
                
                for (Direccion direccionJPA : listaDirecciones) {
                    Direccion direccion = new Direccion();
                    direccion = direccionJPA;
                    usuarioDireccion.Direcciones.add(direccion);
                }
                result.objects.add(usuarioDireccion);
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

    @Override
    public Result GetUsuarioDireccionById(int IdUsuario) {
        Result result = new Result();
        try {
            UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
            usuarioDireccion.Usuario = new Usuario();
            usuarioDireccion.Usuario = entityManager.find(Usuario.class, IdUsuario);

            TypedQuery<Direccion> queryDireccion = entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario = :idusuario", Direccion.class);
            queryDireccion.setParameter("idusuario", usuarioDireccion.Usuario.getIdUsuario());
            List<Direccion> direcciones = queryDireccion.getResultList();
            usuarioDireccion.Direcciones = new ArrayList<>();
            
            for (Direccion direccionJPA : direcciones) {
                Direccion direccion = new Direccion();
                direccion = direccionJPA;
                usuarioDireccion.Direcciones.add(direccion);
            }
            
            result.object = usuarioDireccion;
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.object = null;
        }
        return result;
    }

    @Override
    public Result GetUsuarioById(int IdUsuario) {
        Result result = new Result();
        try {
            UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
            usuarioDireccion.Usuario = new Usuario();
            usuarioDireccion.Usuario = entityManager.find(Usuario.class, IdUsuario);
            result.object = usuarioDireccion;
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
    public Result AddUsuario(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();
        try {
            entityManager.persist(usuarioDireccion.Usuario);
            Direccion direccion = new Direccion();
            direccion = usuarioDireccion.Direccion;
            direccion.Usuario = usuarioDireccion.Usuario;
            entityManager.persist(direccion);
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
    public Result UsuarioUpdate(Usuario usuario) {
        Result result = new Result();
        try {
            entityManager.merge(usuario);
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
    public Result DeleteUsuarioDireccion(int IdUsuario) {
        Result result = new Result();

        try {
            Usuario usuarioJPA = new Usuario();
            TypedQuery<Direccion> queryDirecciones = entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario = :idusuario", Direccion.class);
            queryDirecciones.setParameter("idusuario", IdUsuario);
            List<Direccion> listaDirecciones = queryDirecciones.getResultList();

            for (Direccion direccionJPA : listaDirecciones) {
                entityManager.remove(direccionJPA);
            }

            usuarioJPA = entityManager.find(Usuario.class, IdUsuario);
            entityManager.remove(usuarioJPA);
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
    public Result UpdateStatus(int IdUsuario, int Status) {
        Result result = new Result();

        try {
            Usuario usuarioJPA = new Usuario();
            usuarioJPA = entityManager.find(Usuario.class, IdUsuario);
            usuarioJPA.setStatus(Status);
            entityManager.merge(usuarioJPA);

            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result GetAllDinamico(Usuario usuario) {
        Result result = new Result();
        
        try {
            String queryDinamico = "FROM Usuario";
            queryDinamico = queryDinamico + " WHERE UPPER(Nombre) LIKE UPPER(CONCAT('%', :Nombre ,'%'))";
            queryDinamico = queryDinamico + " AND UPPER(ApellidoPaterno) LIKE UPPER(CONCAT('%', :Apaterno ,'%'))";
            queryDinamico = queryDinamico + " AND UPPER(ApellidoMaterno) LIKE UPPER(CONCAT('%', :Amaterno ,'%'))";

            queryDinamico = usuario.getStatus() != null ? queryDinamico + " AND CAST(Status AS STRING) LIKE CONCAT('%', :Status ,'%')" : queryDinamico;

            queryDinamico = usuario.Rol.getIdRol() != 0 ? queryDinamico + " AND CAST(Rol.IdRol AS String) LIKE CONCAT('%', :IdRol ,'%')" : queryDinamico;

            TypedQuery<Usuario> queryBusqueda = entityManager.createQuery(queryDinamico, Usuario.class);
            queryBusqueda.setParameter("Nombre", usuario.getNombre());
            queryBusqueda.setParameter("Apaterno", usuario.getApellidoPaterno());
            queryBusqueda.setParameter("Amaterno", usuario.getApellidoMaterno());

            if (usuario.getStatus() != null) {
                queryBusqueda.setParameter("Status", usuario.getStatus());
            }

            if (usuario.Rol.getIdRol() != 0) {
                queryBusqueda.setParameter("IdRol", usuario.Rol.getIdRol());
            }

            List<Usuario> listaUsuarios = queryBusqueda.getResultList();
            result.objects = new ArrayList<>();

            for (Usuario usuarioJPA : listaUsuarios) {
                UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
                usuarioDireccion.Usuario = new Usuario();
                usuarioDireccion.Usuario = usuarioJPA;

                TypedQuery<Direccion> queryDirecciones = entityManager.createQuery("FROM Direccion WHERE Usuario.IdUsuario = :IdUsuario", Direccion.class);
                queryDirecciones.setParameter("IdUsuario", usuarioJPA.getIdUsuario());
                List<Direccion> listaDirecciones = queryDirecciones.getResultList();
                usuarioDireccion.Direcciones = new ArrayList<>();

                for (Direccion direccionJPA : listaDirecciones) {
                    Direccion direccion = new Direccion();
                    direccion = direccionJPA;
                    usuarioDireccion.Direcciones.add(direccion);
                }
                result.objects.add(usuarioDireccion);
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
