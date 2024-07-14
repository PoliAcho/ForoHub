package com.aluracursos.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RRegiTopico(
   @NotNull
   Long autor,
   @NotBlank
   String titulo,
   @NotBlank
   String mensaje,
   @NotNull
   ECurso curso) {
}