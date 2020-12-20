package br.com.publicacao.springboot.api.respository;
import br.com.publicacao.springboot.api.model.Publicacao;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public abstract class PublicacaoRepository implements IPublicacaoRepository {
}
