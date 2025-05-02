package com.digis01JEnriquezProgramacionNCapas.DAO;

import com.digis01JEnriquezProgramacionNCapas.JPA.Direccion;
import com.digis01JEnriquezProgramacionNCapas.JPA.Result;
import com.digis01JEnriquezProgramacionNCapas.JPA.UsuarioDireccion;

public interface IDireccionDAO {
    Result GetDireccionByIdDireccion(int IdDireccion);
    Result DireccionAdd(UsuarioDireccion usuarioDireccion);
    Result DireccionUpdate(Direccion direccion);
    Result DireccionDelete(int IdDireccion);
}
