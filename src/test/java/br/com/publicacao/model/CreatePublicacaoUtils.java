package br.com.publicacao.model;

import br.com.publicacao.springboot.api.dto.PublicacaoDTO;
import br.com.publicacao.springboot.api.model.Publicacao;

public class CreatePublicacaoUtils {
    public static PublicacaoDTO createDTO(Long id, String texto, Integer totalLike) {
        PublicacaoDTO dto = new PublicacaoDTO();

        dto.setId(id);
        dto.setTexto(texto);
        dto.setTotalLikes(totalLike);

        return dto;
    }

    public static Publicacao createModelObject(Long id, String texto, Integer totalLike) {
        Publicacao model = Publicacao.getBuilder(texto, totalLike).build();

        model.setId(id);

        return model;
    }
}
