package br.com.publicacao.springboot.api.service;

import br.com.publicacao.springboot.api.dto.PublicacaoDTO;
import br.com.publicacao.springboot.api.model.Publicacao;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPublicacaoService {
    ResponseEntity<List<PublicacaoDTO>>  listar();
    ResponseEntity<Publicacao> adicionarLike(Long id);
    ResponseEntity<Publicacao> adicionarPostagem(PublicacaoDTO publicacao);
    ResponseEntity<Publicacao> deletarPostagem(PublicacaoDTO publicacao);
}
