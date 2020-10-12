package com.affero.categoria;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.affero.exception.BusinessException;
import com.affero.produto.Produto;
import com.affero.produto.ProdutoRepository;

@Service
public class CategoriaService {

	private static final int ZERO = 0;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public Categoria obterCategoriaPorId(Integer id) {
		this.validarId(id);
		return this.categoriaRepository.findById(id).orElseThrow(
				() -> new BusinessException(MessageFormat.format("Categoria com id: {0} não encontrado", id)));
	}

	public List<CategoriaDto> getProdutos() {
		return this.categoriaRepository.findAll().stream().map(Categoria::obterDto).collect(Collectors.toList());

	}

	public void atualizar(CategoriaDto dto, int id) {
		dto.setId(id);
		this.validarId(dto.getId());
		this.validarExistencia(id);
		this.validarParametros(dto);
		this.categoriaRepository.save(Categoria.novo(dto));

	}

	public void delete(Integer id) {
		this.validarProdutosComCategoria(id);
		this.validarId(id);
		this.validarExistencia(id);
		this.categoriaRepository.delete(this.obterCategoriaPorId(id));

	}
	
	private void validarProdutosComCategoria(Integer id) {
		List<Produto> produtos =this.produtoRepository.findByCategoriaId(id);
		if(!produtos.isEmpty()) {
			throw new BusinessException("Categoria não poder ser excluida. Ela está associada a um ou mais produto.");
		}
		
	}

	public Categoria novo(CategoriaDto categoriaDto) {
		this.validarParametros(categoriaDto);
		return this.categoriaRepository.save(Categoria.novo(categoriaDto));
	}

	private void validarParametros(CategoriaDto dto) {
		
		if (Objects.isNull(dto.getDescricao()) || dto.getDescricao().isEmpty()) {
			throw new BusinessException("A descrição da Categoria é obrigatória.");
		}

		if (Objects.isNull(dto.getNomeCategoria())  || dto.getNomeCategoria().isEmpty()) {
			throw new BusinessException("O nome da Categoria é obrigatório.");
		}

	}

	private void validarExistencia(Integer id) {
		if (Objects.isNull(this.obterCategoriaPorId(id))) {
			throw new BusinessException("Categoria não existente.");
		}

	}

	private void validarId(Integer id) {
		if (id <= ZERO) {
			throw new BusinessException("Categoria inválida.");
		}

	}

	public CategoriaDto getById(Integer id) {
		this.validarId(id);
		Categoria categoria = this.categoriaRepository.findById(id).orElseThrow(
				() -> new BusinessException(MessageFormat.format("Categoria com id: {0} não encontrado", id)));
		return categoria.obterDto();
	}

	

}
