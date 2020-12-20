package br.com.publicacao.springboot.api.service;

import br.com.publicacao.springboot.api.dto.PublicacaoDTO;
import br.com.publicacao.springboot.api.model.Publicacao;
import io.swagger.models.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IPublicacaoService {
    ResponseEntity<List<Publicacao>>  listar();
    ResponseEntity<Publicacao> adicionarLike(Long id);
    ResponseEntity<Publicacao> adicionarPostagem(Publicacao publicacao);
    ResponseEntity<Publicacao> deletarPostagem(Publicacao publicacao);
}
