package com.aluracursos.forohub.domain.topico;

public record RActualizTopico(
   Long id,
   String titulo,
   String mensaje,
   String estatus,
   String curso) {
}