
package com.digis01JEnriquezProgramacionNCapas.DAO;

import com.digis01JEnriquezProgramacionNCapas.JPA.Result;
import com.digis01JEnriquezProgramacionNCapas.JPA.Usuario;
import com.digis01JEnriquezProgramacionNCapas.JPA.UsuarioDireccion;

public interface IUsuarioDAO { 
    Result GetAll();
    Result GetAllById(int IdUsuario);
    Result GetById(int IdUsuario);
    Result Add(UsuarioDireccion usuarioDireccion);
    Result Update(Usuario usuario);
    Result Delete(int IdUsuario);
    Result UpdateStatus(int IdUsuario, int Status);
    Result GetAllDinamico(Usuario usuario);
}
