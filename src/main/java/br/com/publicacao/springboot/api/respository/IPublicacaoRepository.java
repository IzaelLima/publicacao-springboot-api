package br.com.publicacao.springboot.api.respository;
import br.com.publicacao.springboot.api.model.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPublicacaoRepository extends JpaRepository<Publicacao, Long>{
}
