package com.aluracursos.forohub.domain.topico;

public record RRptaTopico(
   Long id,
   String titulo,
   String mensaje,
   String fecha,
   Long autor,
   String curso) {

  public RRptaTopico(CTopico topico) {
    this(topico.getId(),
       topico.getTitulo(),
       topico.getMensaje(),
       topico.getFecha(),
       topico.getAutor(),
       topico.getCurso().toString());
  }
}