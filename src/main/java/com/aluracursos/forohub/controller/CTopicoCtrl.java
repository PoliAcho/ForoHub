package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.hateoas.EntityModel;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class CTopicoCtrl {

  @Autowired
  private ITopicoRepo topicoRepository;
  @Autowired
  private PagedResourcesAssembler<RListaTopico> pagedResourcesAssembler;

  @PostMapping
  public ResponseEntity<RRptaTopico> registrarTopico
     (@RequestBody @Valid RRegiTopico datosRegistroTopico,
      UriComponentsBuilder uriComponentsBuilder) {
    CTopico topico = topicoRepository.save(new CTopico(datosRegistroTopico));
    RRptaTopico datosRespuestaTopico = new RRptaTopico(topico);
    URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
    return ResponseEntity.created(url).body(datosRespuestaTopico);
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity actualizarTopico(
     @PathVariable Long id,
     @RequestBody @Valid RActualizTopico datosActualizarTopico) {
    CTopico topico = topicoRepository.getReferenceById(id);
    topico.actualizarDatos(datosActualizarTopico);
    return ResponseEntity.ok(new RRptaTopico(topico));
  }

  @GetMapping("/{id}")
  public ResponseEntity<RRptaTopico> retornaDatosTopico(@PathVariable Long id) {
    CTopico topico = topicoRepository.getReferenceById(id);
    var datosTopico = new RRptaTopico(topico);
    return ResponseEntity.ok(datosTopico);
  }

  @GetMapping
  public PagedModel<EntityModel<RListaTopico>> listadoTopicos(
     @PageableDefault(size = 10, sort = "fecha",
        direction = Sort.Direction.DESC) Pageable paginacion) {
    Page<RListaTopico> page = topicoRepository
       .findByEstadoTrue(paginacion).map(RListaTopico::new);
    return pagedResourcesAssembler.toModel(page);
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity eliminarTopico(@PathVariable Long id) {
    CTopico topico = topicoRepository.getReferenceById(id);
    topico.eliminarTopico();
    return ResponseEntity.noContent().build();
  }
}