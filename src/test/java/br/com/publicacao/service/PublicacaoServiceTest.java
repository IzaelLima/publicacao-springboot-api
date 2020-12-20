package br.com.publicacao.service;

import br.com.publicacao.model.CreatePublicacaoUtils;
import br.com.publicacao.springboot.api.dto.PublicacaoDTO;
import br.com.publicacao.springboot.api.model.Publicacao;
import br.com.publicacao.springboot.api.respository.IPublicacaoRepository;
import br.com.publicacao.springboot.api.respository.PublicacaoRepository;
import br.com.publicacao.springboot.api.service.IPublicacaoService;
import br.com.publicacao.springboot.api.service.PublicacaoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
@SpringJUnitWebConfig
public class PublicacaoServiceTest {

    private static final Long PUBLICACAO_ID = Long.valueOf(5);
    private static final String TEXTO = "Foo";
    private static final Integer TOTALLIKES = 10;

    private IPublicacaoService _publicacaoService;

    @Mock
    private IPublicacaoRepository _publicacaoRepository;

    @Mock
    private ModelMapper modelMapper;

    @Before
    public void setUp() {
        _publicacaoRepository = mock(PublicacaoRepository.class);
        _publicacaoService = new PublicacaoService(_publicacaoRepository, new ModelMapper());
    }

    @Test
    public void listarTest(){
        List<Publicacao> publicacoes = new ArrayList<Publicacao>();

        when(_publicacaoRepository.findAll(any(Sort.class))).thenReturn(publicacoes);
        ResponseEntity<List<PublicacaoDTO>> returned = _publicacaoService.listar();

        ArgumentCaptor<Sort> sortArgument = ArgumentCaptor.forClass(Sort.class);
        verify(_publicacaoRepository, times(1)).findAll(sortArgument.capture());

        verifyNoMoreInteractions(_publicacaoRepository);
        Sort actualSort = sortArgument.getValue();
        assertEquals(Sort.Direction.DESC, actualSort.getOrderFor("dataPublicacao").getDirection());

        assertEquals(publicacoes, returned.getBody());
    }

    @Test
    public void adicionarPostagemTest() {
        PublicacaoDTO created = CreatePublicacaoUtils.createDTO(5L, TEXTO, TOTALLIKES);
        Publicacao persisted = CreatePublicacaoUtils.createModelObject(PUBLICACAO_ID, TEXTO, TOTALLIKES);

        when(_publicacaoRepository.save(any(Publicacao.class))).thenReturn(persisted);

        ResponseEntity<Publicacao> returned = _publicacaoService.adicionarPostagem(created);

        ArgumentCaptor<Publicacao> publicacaoArgument = ArgumentCaptor.forClass(Publicacao.class);
        verify(_publicacaoRepository, times(1)).save(publicacaoArgument.capture());
        verifyNoMoreInteractions(_publicacaoRepository);

        assertPublicacao(created, publicacaoArgument.getValue());
        assertEquals(persisted, returned.getBody());
    }

    @Test
    public void deletarPostagemTest(){
        Publicacao exist = CreatePublicacaoUtils.createModelObject(PUBLICACAO_ID, TEXTO, TOTALLIKES);
        PublicacaoDTO persisted = CreatePublicacaoUtils.createDTO(PUBLICACAO_ID, TEXTO, TOTALLIKES);

        ResponseEntity<Publicacao> returned = _publicacaoService.deletarPostagem(persisted);

        ArgumentCaptor<Publicacao> publicacaoArgument = ArgumentCaptor.forClass(Publicacao.class);
        verify(_publicacaoRepository, times(1)).delete(publicacaoArgument.capture());
        verifyNoMoreInteractions(_publicacaoRepository);

        assertEquals(exist, returned.getBody());
    }

    @Test
    public void adicionarLikeTest(){
        Publicacao created = CreatePublicacaoUtils.createModelObject(PUBLICACAO_ID, TEXTO, TOTALLIKES);
        PublicacaoDTO persisted = CreatePublicacaoUtils.createDTO(PUBLICACAO_ID, TEXTO, TOTALLIKES);

        when(_publicacaoRepository.findById(PUBLICACAO_ID)).thenReturn(java.util.Optional.of(created));
        ResponseEntity<Publicacao> returned = _publicacaoService.adicionarLike(PUBLICACAO_ID);

        verify(_publicacaoRepository, times(1)).findById(PUBLICACAO_ID);
        assertEquals(created, returned.getBody());
    }

    private void assertPublicacao(PublicacaoDTO expected, Publicacao actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTexto(), actual.getTexto());
        assertEquals(expected.getTotalLikes(), expected.getTotalLikes());
    }
}
