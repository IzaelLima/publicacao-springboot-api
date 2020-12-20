package br.com.publicacao.springboot.api.resources;

import br.com.publicacao.springboot.api.dto.PublicacaoDTO;
import br.com.publicacao.springboot.api.model.Publicacao;
import br.com.publicacao.springboot.api.service.PublicacaoService;
import br.com.publicacao.springboot.api.utils.ReturnObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/Publicacao")
public class PublicacaoController {

	@Autowired
	private PublicacaoService publicacaoService;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	@ResponseBody
	@RequestMapping(value = "/GetAll", method = RequestMethod.GET)
	public ResponseEntity<?> GetAll() {
		List<PublicacaoDTO> lista = publicacaoService.listar().getBody().stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());;
		return ResponseEntity.ok().body(new ReturnObject(HttpStatus.OK.value(), lista, null));
	};

	@GetMapping
	@ResponseBody
	@RequestMapping(value = "/AdicionarLike", method = RequestMethod.POST)
	public ResponseEntity<?> AdicionarLike(@Valid @RequestBody PublicacaoDTO publicacao) {
		return ResponseEntity.ok().body(new ReturnObject(HttpStatus.OK.value(), publicacaoService.adicionarLike(publicacao.getId()), null));
	}

	@GetMapping
	@ResponseBody
	@RequestMapping(value = "/AdicionarPublicacao", method = RequestMethod.POST)
	public ResponseEntity<?> AdicionarPublicacao(@Valid @RequestBody PublicacaoDTO publicacao) {
		Publicacao pub = modelMapper.map(publicacao, Publicacao.class);
		return ResponseEntity.ok().body(new ReturnObject(HttpStatus.OK.value(), publicacaoService.adicionarPostagem(pub), null));
	}

	@GetMapping
	@ResponseBody
	@RequestMapping(value = "/DeletarPublicacao", method = RequestMethod.POST)
	public ResponseEntity<?> DeletarPublicacao(@Valid @RequestBody PublicacaoDTO publicacao) {
		Publicacao pub = modelMapper.map(publicacao, Publicacao.class);
		return ResponseEntity.ok().body(new ReturnObject(HttpStatus.OK.value(), publicacaoService.deletarPostagem(pub), null));
	}

	private PublicacaoDTO convertToDto(Publicacao publicacao) {
		PublicacaoDTO publicacaoDTO = modelMapper.map(publicacao, PublicacaoDTO.class);
		publicacaoDTO.setSubmissionDate(publicacao.getDataPublicacao());
		return publicacaoDTO;
	}
}
