
package com.digis01JEnriquezProgramacionNCapas.DAO;

import com.digis01JEnriquezProgramacionNCapas.JPA.Result;
import com.digis01JEnriquezProgramacionNCapas.JPA.Usuario;
import com.digis01JEnriquezProgramacionNCapas.JPA.UsuarioDireccion;

public interface IUsuarioDAO { 
    Result GetAll();
    Result GetUsuarioDireccionById(int IdUsuario);
    Result GetUsuarioById(int IdUsuario);
    Result AddUsuario(UsuarioDireccion usuarioDireccion);
    Result UsuarioUpdate(Usuario usuario);
    Result DeleteUsuarioDireccion(int IdUsuario);
    Result UpdateStatus(int IdUsuario, int Status);
    Result GetAllDinamico(Usuario usuario);
}
