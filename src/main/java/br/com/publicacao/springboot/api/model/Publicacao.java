package br.com.publicacao.springboot.api.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "publicacao")
public class Publicacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String texto;
	private LocalDateTime dataPublicacao;
	private Integer totalLikes;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Column(name = "data_publicacao")
	public LocalDateTime getDataPublicacao() {
		return dataPublicacao;
	}

	public Integer getTotalLikes() {
		return totalLikes;
	}

	public void setTotalLikes(Integer totalLikes) {
		this.totalLikes = totalLikes;
	}

	public void setDataPublicacao(LocalDateTime dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	/**
	 * Gets a builder which is used to create Person objects.
	 * @param texto The texto created.
	 * @param totalLikes  The totalLikes.
	 * @return  A new Builder instance.
	 */
	public static Builder getBuilder(String texto, Integer totalLikes) {
		return new Builder(texto, totalLikes);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Publicacao other = (Publicacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public static class Builder {
		Publicacao built;

		/**
		 * Creates a new Builder instance.
		 * @param texto The texto created.
		 * @param totalLikes  The totalLikes.
		 */
		Builder(String texto, Integer totalLikes) {
			built = new Publicacao();
			built.texto = texto;
			built.totalLikes = totalLikes;
		}

		/**
		 * Builds the new Person object.
		 * @return  The created Person object.
		 */
		public Publicacao build() {
			return built;
		}
	}
}
