package br.com.publicacao.springboot.api.service;

import br.com.publicacao.springboot.api.dto.PublicacaoDTO;
import br.com.publicacao.springboot.api.model.Publicacao;
import br.com.publicacao.springboot.api.respository.IPublicacaoRepository;
import br.com.publicacao.springboot.api.service.exceptions.RecursoNaoEncontradoException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublicacaoService implements IPublicacaoService{

	@Autowired
	private IPublicacaoRepository _publicacaoRepository;

	@Autowired
	private ModelMapper modelMapper;

	public PublicacaoService(IPublicacaoRepository _publicacaoRepository, ModelMapper modelMapper) {
		this._publicacaoRepository = _publicacaoRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ResponseEntity<List<PublicacaoDTO>> listar() {
		try {
			List<PublicacaoDTO> lista = _publicacaoRepository.findAll(new Sort(Sort.Direction.DESC, "dataPublicacao")).stream()
					.map(this::convertToDto)
					.collect(Collectors.toList());
			return new ResponseEntity<>(lista, HttpStatus.OK);
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
	public ResponseEntity<Publicacao> adicionarPostagem(PublicacaoDTO publicacao) {
		try{
			Publicacao pub = modelMapper.map(publicacao, Publicacao.class);
			pub.setDataPublicacao(LocalDateTime.now());
			pub.setTotalLikes(0);
			_publicacaoRepository.save(pub);
			return new ResponseEntity<>(pub, HttpStatus.OK);
		}
		catch (Exception e){
			throw new RecursoNaoEncontradoException(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Publicacao> deletarPostagem(PublicacaoDTO publicacao) {
		try {
			Publicacao pub = modelMapper.map(publicacao, Publicacao.class);
			_publicacaoRepository.delete(pub);
			return new ResponseEntity<>(pub, HttpStatus.OK);
		}
		catch (Exception e){
			throw new RecursoNaoEncontradoException(e.getMessage());
		}
	}

	public PublicacaoDTO convertToDto(Publicacao publicacao) {
		PublicacaoDTO publicacaoDTO = modelMapper.map(publicacao, PublicacaoDTO.class);
		publicacaoDTO.setSubmissionDate(publicacao.getDataPublicacao());
		return publicacaoDTO;
	}
}
