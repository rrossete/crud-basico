package com.affero.produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.affero.categoria.Categoria;

@Entity
@Table(name = "Af_Produto")
@CrossOrigin(origins = "http://localhost:4200/api")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "nome")
	private String nomeProduto;

	@Column(name = "codigo_barras")
	private String codigoBarras;

	@Column(name = "valor")
	private Double valor;

	@Column(name = "descricao")
	private String descricao;

	private int quantidade;

	@ManyToOne
	@JoinColumn(name = "categoria_id", updatable = true, insertable = true)
	private Categoria categoria;

	public ProdutoDto obterDto() {
		ProdutoDto dto = new ProdutoDto();
		dto.setCodigoBarra(codigoBarras);
		dto.setDescricao(descricao);
		dto.setId(id);
		dto.setNomeProduto(nomeProduto);
		dto.setValor(valor);
		dto.setQuantidade(quantidade);
		dto.setCategoria(categoria.getId());
		dto.setNomeCategoria(categoria.getNomeCategoria());
		return dto;
	}

	public static Produto novo(ProdutoDto dto, Categoria categoria) {
		Produto produto = new Produto();
		produto.setId(dto.getId());
		produto.setCodigoBarra(dto.getCodigoBarra());
		produto.setDescricao(dto.getDescricao());
		produto.setId(dto.getId());
		produto.setNomeProduto(dto.getNomeProduto());
		produto.setQuantidade(dto.getQuantidade());
		produto.setValor(dto.getValor());
		produto.setCategoria(categoria);

		return produto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getCodigoBarra() {
		return codigoBarras;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarras = codigoBarra;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
