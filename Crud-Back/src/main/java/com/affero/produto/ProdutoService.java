package com.affero.produto;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.affero.categoria.Categoria;
import com.affero.categoria.CategoriaService;
import com.affero.exception.BusinessException;

@Service
public class ProdutoService {

	private static final int ZERO = 0;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaService categoriaService;

	public List<ProdutoDto> getProdutos() {
		return this.produtoRepository.findAll().stream().map(Produto::obterDto).collect(Collectors.toList());
	}

	public ProdutoDto getById(Integer id) {
		this.validarId(id);
		Produto produto = obterProdutoPorId(id);

		return produto.obterDto();
	}

	public void delete(Integer id) {
		this.validarId(id);
		this.validarExistencia(id);
		this.produtoRepository.delete(this.obterProdutoPorId(id));
	}

	public void atualizar(ProdutoDto dto, Integer id) {
		dto.setId(id);
		this.validarExistencia(id);
		this.validarCategoria(dto.getCategoria());
		this.validarParametros(dto);
		this.produtoRepository.save(Produto.novo(dto, obterCategoria(dto.getCategoria())));
	}

	public Produto novo(ProdutoDto produtoDto) {
		this.validarParametros(produtoDto);
		return this.produtoRepository.save(Produto.novo(produtoDto, obterCategoria(produtoDto.getCategoria())));
	}

	private Categoria obterCategoria(Integer idCategoria) {
		return categoriaService.obterCategoriaPorId(idCategoria);
	}

	private void validarParametros(ProdutoDto produtoDto) {
		this.validarCategoria(produtoDto.getCategoria());

		if (Objects.isNull(produtoDto.getCodigoBarra())  || produtoDto.getCodigoBarra().isEmpty()) {
			throw new BusinessException("O código de barras é obrigatório.");
		}
		if (Objects.isNull(produtoDto.getValor()) ) {
			throw new BusinessException("O valor do produto é obrigatório.");
		}
		if (Objects.isNull(produtoDto.getNomeProduto())  || produtoDto.getNomeProduto().isEmpty()) {
			throw new BusinessException("O nome do produto é obreigatório.");
		}
		if (Objects.isNull(produtoDto.getDescricao())  || produtoDto.getDescricao().isEmpty()) {
			throw new BusinessException("A descrição do produto é obrigatória.");
		}
		if (produtoDto.getQuantidade() <= 0) {
			throw new BusinessException("A quantidade do produto é obrigatório");
		}
		

	}

	private void validarExistencia(Integer id) {
		if (Objects.isNull(this.obterProdutoPorId(id))) {
			throw new BusinessException("Produto não existente.");
		}

	}

	private void validarCategoria(Integer categoria) {
		if(Objects.isNull(this.obterCategoria(categoria)) || categoria <= 0) {
			throw new BusinessException("A categoria não existe.");
		}

	}

	private void validarId(Integer id) {
		if (id <= ZERO) {
			throw new BusinessException("Id do produto inválido.");
		}

	}

	private Produto obterProdutoPorId(Integer id) {
		return this.produtoRepository.findById(id).orElseThrow(
				() -> new BusinessException(MessageFormat.format("Produto com id: {0} não encontrado", id)));
	}

}
