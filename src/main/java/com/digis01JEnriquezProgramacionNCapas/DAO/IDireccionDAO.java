package com.digis01JEnriquezProgramacionNCapas.DAO;

import com.digis01JEnriquezProgramacionNCapas.JPA.Direccion;
import com.digis01JEnriquezProgramacionNCapas.JPA.Result;
import com.digis01JEnriquezProgramacionNCapas.JPA.UsuarioDireccion;

public interface IDireccionDAO {
    Result GetById(int IdDireccion);
    Result Add(UsuarioDireccion usuarioDireccion);
    Result Update(Direccion direccion);
    Result Delete(int IdDireccion);
}
