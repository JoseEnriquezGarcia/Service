package com.digis01JEnriquezProgramacionNCapas.JPA;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class Result {
    @Schema(name = "correct")
    public boolean correct;
    @Schema(name = "errorMessage")
    public String errorMessage;
    @Schema(name = "ex")
    public Exception ex;
    @Schema(name = "object", example = "null")
    public Object object;
    @Schema(name = "List<Object>", example = "null")
    public List<Object> objects;
}
