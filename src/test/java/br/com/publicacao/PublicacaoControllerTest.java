package br.com.publicacao;

import br.com.publicacao.springboot.api.dto.PublicacaoDTO;
import br.com.publicacao.springboot.api.model.Publicacao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringJUnitWebConfig
public class PublicacaoControllerTest {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void convertToDtoTest() {
        PublicacaoDTO publicacaoDTO = new PublicacaoDTO();
        publicacaoDTO.setId(1L);
        publicacaoDTO.setTexto("Teste");
        publicacaoDTO.setTotalLikes(2);

        Publicacao publicacao = modelMapper.map(publicacaoDTO, Publicacao.class);
        assertEquals(publicacaoDTO.getId(), publicacao.getId());
        assertEquals(publicacaoDTO.getTexto(), publicacao.getTexto());
        assertEquals(publicacaoDTO.getTotalLikes(), publicacao.getTotalLikes());
    }
}
