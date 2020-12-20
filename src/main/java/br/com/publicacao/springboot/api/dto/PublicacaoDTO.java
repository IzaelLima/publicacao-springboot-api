package br.com.publicacao.springboot.api.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PublicacaoDTO {

    private Long id;
    private String texto;
    private String dataPublicacao;
    private Integer totalLikes;

    public void setSubmissionDate(LocalDateTime date) {
        this.dataPublicacao = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }


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

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Integer getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(Integer totalLikes) {
        this.totalLikes = totalLikes;
    }
}
