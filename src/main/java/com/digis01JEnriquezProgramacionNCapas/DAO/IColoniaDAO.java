package com.digis01JEnriquezProgramacionNCapas.DAO;

import com.digis01JEnriquezProgramacionNCapas.JPA.Result;

public interface IColoniaDAO {
    Result ColoniaByIdMunicipio(int IdMunicipio);
    Result ColoniaByCP(String CodigoPostal);
}
