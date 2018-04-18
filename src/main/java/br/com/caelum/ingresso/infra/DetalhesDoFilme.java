package br.com.caelum.ingresso.infra;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DetalhesDoFilme {
	@JsonProperty("Poster")
	private String imagem;
	@JsonProperty("Year")
	private String ano;
	@JsonProperty("Director")
	private String diretores;
	@JsonProperty("Plot")
	private String descricao;
	@JsonProperty("imdbRating")
	private String avaliacao;
	
	@JsonProperty("Title")
	private String titulo;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getDiretores() {
		return diretores;
	}
	public void setDiretores(String diretores) {
		this.diretores = diretores;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}
}
