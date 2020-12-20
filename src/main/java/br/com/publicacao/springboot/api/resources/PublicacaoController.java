package br.com.publicacao.springboot.api.resources;

import br.com.publicacao.springboot.api.dto.PublicacaoDTO;
import br.com.publicacao.springboot.api.service.IPublicacaoService;
import br.com.publicacao.springboot.api.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/Publicacao")
public class PublicacaoController {

	@Autowired
	private IPublicacaoService _publicacaoService;

	@GetMapping
	@ResponseBody
	@RequestMapping(value = "/GetAll", method = RequestMethod.GET)
	public ResponseEntity<?> GetAll() {
		return ResponseEntity.ok().body(new ReturnObject(HttpStatus.OK.value(), _publicacaoService.listar().getBody(), null));
	};

	@GetMapping
	@ResponseBody
	@RequestMapping(value = "/AdicionarLike", method = RequestMethod.POST)
	public ResponseEntity<?> AdicionarLike(@Valid @RequestBody PublicacaoDTO publicacao) {
		return ResponseEntity.ok().body(new ReturnObject(HttpStatus.OK.value(), _publicacaoService.adicionarLike(publicacao.getId()), null));
	}

	@GetMapping
	@ResponseBody
	@RequestMapping(value = "/AdicionarPublicacao", method = RequestMethod.POST)
	public ResponseEntity<?> AdicionarPublicacao(@Valid @RequestBody PublicacaoDTO publicacao) {
		return ResponseEntity.ok().body(new ReturnObject(HttpStatus.OK.value(), _publicacaoService.adicionarPostagem(publicacao), null));
	}

	@GetMapping
	@ResponseBody
	@RequestMapping(value = "/DeletarPublicacao", method = RequestMethod.POST)
	public ResponseEntity<?> DeletarPublicacao(@Valid @RequestBody PublicacaoDTO publicacao) {
		return ResponseEntity.ok().body(new ReturnObject(HttpStatus.OK.value(), _publicacaoService.deletarPostagem(publicacao), null));
	}
}
