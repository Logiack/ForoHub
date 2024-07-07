package com.alura.challenge.forohub.ForoHub.controller;

import com.alura.challenge.forohub.ForoHub.domain.topicos.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity crearTopico(@RequestBody @Valid DatosRegistroTopico datos){
        var respuesta = service.registroTopico(datos);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosTopico>> listarTopicos(@PageableDefault(size = 3) Pageable paginacion) {
        var respuesta = service.listarTopicos(paginacion);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datos) {
        var respuesta = service.actualizarTopico(datos);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        var respuesta = service.eliminarTopico(id);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity topicoPorId(@PathVariable Long id) {
        var respuesta = service.topicoPorId(id);
        return ResponseEntity.ok(respuesta);
    }
}
