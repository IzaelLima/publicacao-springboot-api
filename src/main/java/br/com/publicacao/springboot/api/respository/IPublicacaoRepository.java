package br.com.publicacao.springboot.api.respository;
import br.com.publicacao.springboot.api.model.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPublicacaoRepository extends JpaRepository<Publicacao, Long>{
}
