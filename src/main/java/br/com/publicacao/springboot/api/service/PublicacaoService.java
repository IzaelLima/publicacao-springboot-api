package br.com.publicacao.springboot.api.service;

import br.com.publicacao.springboot.api.dto.PublicacaoDTO;
import br.com.publicacao.springboot.api.model.Publicacao;
import br.com.publicacao.springboot.api.service.exceptions.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.publicacao.springboot.api.respository.IPublicacaoRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class PublicacaoService implements IPublicacaoService{
	
	@Autowired
	private IPublicacaoRepository _publicacaoRepository;

	@Override
	public ResponseEntity<List<Publicacao>> listar() {
		try {
			return new ResponseEntity<List<Publicacao>>(_publicacaoRepository.findAll(new Sort(Sort.Direction.DESC, "dataPublicacao")), HttpStatus.OK);
		} catch (Exception e){
			throw new RecursoNaoEncontradoException(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Publicacao> adicionarLike(Long id) {
		try {
			Optional<Publicacao> oldPublicacao = _publicacaoRepository.findById(id);
			if (oldPublicacao.isPresent()) {
				Publicacao publicacao = oldPublicacao.get();
				publicacao.setTotalLikes(publicacao.getTotalLikes() + 1);
				_publicacaoRepository.save(publicacao);
				return new ResponseEntity<Publicacao>(publicacao, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e){
			throw new RecursoNaoEncontradoException(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Publicacao> adicionarPostagem(Publicacao publicacao) {
		try{
			publicacao.setDataPublicacao(LocalDateTime.now());
			publicacao.setTotalLikes(0);
			_publicacaoRepository.save(publicacao);
			return new ResponseEntity<Publicacao>(publicacao, HttpStatus.OK);
		}
		catch (Exception e){
			throw new RecursoNaoEncontradoException(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Publicacao> deletarPostagem(Publicacao publicacao) {
		try {
			_publicacaoRepository.delete(publicacao);
			return new ResponseEntity<Publicacao>(publicacao, HttpStatus.OK);
		}
		catch (Exception e){
			throw new RecursoNaoEncontradoException(e.getMessage());
		}
	}
}
