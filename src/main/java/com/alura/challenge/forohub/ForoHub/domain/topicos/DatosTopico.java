package com.alura.challenge.forohub.ForoHub.domain.topicos;

import java.time.LocalDateTime;

public record DatosTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime data,
        Boolean status,
        String autor,
        String curso
) {
    public DatosTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getData(),
                topico.getStatus(), topico.getAutor(),topico.getCurso());
    }
}
