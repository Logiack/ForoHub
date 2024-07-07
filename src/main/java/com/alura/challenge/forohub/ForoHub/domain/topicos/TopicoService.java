package com.alura.challenge.forohub.ForoHub.domain.topicos;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository repository;


    public DatosTopico registroTopico(DatosRegistroTopico datos) {

        var titulo = datos.titulo();
        var mensaje = datos.mensaje();
        var autor = datos.autor();
        var curso = datos.curso();

        var topico = new Topico(titulo, mensaje, autor, curso);

        repository.save(topico);
        return new DatosTopico(topico);
    }

    public Page<DatosTopico> listarTopicos(Pageable paginacion) {
        return repository.findAll(paginacion).map(DatosTopico::new);
    }

    public DatosTopico actualizarTopico(DatosActualizarTopico datos) {
        var topico = repository.getReferenceById(datos.id());
        topico.actualizarDatos(datos);
        return new DatosTopico(topico);
    }

    public String eliminarTopico(Long id) {
        var topico = repository.findById(id);
        if (!topico.isPresent() || id == null) {
            throw new ValidationException("Este topico con el id " + id + " no existe en la base de datos");
        }
        repository.deleteById(id);
        return "Topico eliminado";
    }

    public DatosTopico topicoPorId(Long id) {
        var topico = repository.findById(id)
                .orElseThrow(() -> new ValidationException("No existe el topico deseado"));

        return new DatosTopico(topico);
    }
}
