package com.affero.categoria;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.affero.produto.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Af_Categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "nome")
	private String nomeCategoria;

	@Column(name = "descricao")
	private String descricao;

	@JsonIgnore
	@OneToMany
	@JoinColumn(name = "categoria_id",  updatable = false, insertable = false)
	private List<Produto> produtos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public CategoriaDto obterDto() {
		CategoriaDto dto =  new CategoriaDto();
		dto.setDescricao(this.getDescricao());
		dto.setId(this.getId());
		dto.setNomeCategoria(this.getNomeCategoria());
		
		return dto;
	}
	
	public static Categoria novo(CategoriaDto dto) {
		Categoria categoria  = new Categoria();
		categoria.setDescricao(dto.getDescricao());
		categoria.setId(dto.getId());
		categoria.setNomeCategoria(dto.getNomeCategoria());
		
		return categoria;
	}

}
