package com.aluracursos.forohub.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Table(name = "topicos")
@Entity(name = "CTopico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CTopico {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String titulo;
  private String mensaje;
  private String fecha;
  private Boolean estado;
  private Long autor;
  @Enumerated(EnumType.STRING)
  private ECurso curso;

  public CTopico(RRegiTopico datosRegistroTopico) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    this.titulo = datosRegistroTopico.titulo();
    this.mensaje = datosRegistroTopico.mensaje();
    this.fecha = LocalDateTime.now().format(formatter);
    this.estado = Boolean.TRUE;
    this.autor = datosRegistroTopico.autor();
    this.curso = datosRegistroTopico.curso();
  }

  public void actualizarDatos(RActualizTopico datosActualizarTopico) {
    if (datosActualizarTopico.titulo() != null) {
      this.titulo = datosActualizarTopico.titulo();
    }
    if (datosActualizarTopico.mensaje() != null) {
      this.mensaje = datosActualizarTopico.mensaje();
    }
    if (datosActualizarTopico.estatus() != null) {
      this.estado = Boolean.valueOf(datosActualizarTopico.estatus());
    }
    if (datosActualizarTopico.curso() != null) {
      this.curso = ECurso.valueOf(datosActualizarTopico.curso());
    }
  }

  public void eliminarTopico() {
    this.estado = Boolean.FALSE;
  }
}