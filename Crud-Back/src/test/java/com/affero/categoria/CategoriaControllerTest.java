package com.affero.categoria;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.affero.produto.Produto;
import com.affero.produto.ProdutoRepository;
import com.affero.produto.ProdutoService;


@SpringBootTest
public class CategoriaControllerTest {
	
	private CategoriaService categoriaService;
	
	@Mock
	private CategoriaRepository categoriaRepository;
	
	@Mock
	private ProdutoRepository produtoRepository;
	
	private Optional<Categoria> categoria ;
	
	@Before
	public void antes() {
		
		MockitoAnnotations.initMocks(this);
		categoriaService =  new CategoriaService();
		ReflectionTestUtils.setField(this.categoriaService, "categoriaRepository", this.categoriaRepository);
		ReflectionTestUtils.setField(this.categoriaService, "produtoRepository", this.produtoRepository);
		
		this.categoria = Optional.ofNullable(new Categoria());
		this.categoria.get().setDescricao("Descrição teste");
		this.categoria.get().setId(1);
		this.categoria.get().setNomeCategoria("Teste");
		
	}
	
	@Test
	public void testGetCategoria() {
		
		when(this.categoriaRepository.findById(1)).thenReturn(this.categoria);
		
		CategoriaDto categoria = this.categoriaService.getById(1);
		
		Assert.assertEquals(categoria.getDescricao(), "Descrição teste");
		Assert.assertEquals(categoria.getNomeCategoria(), "Teste");
		
	}
	@Test
	public void testAtualizarCategoria() {
		
		when(this.categoriaRepository.findById(1)).thenReturn(this.categoria);
		
		CategoriaDto dto = new CategoriaDto();
		dto.setDescricao("Descrição");
		dto.setId(1);
		dto.setNomeCategoria("Categoria Teste");
		
		this.categoriaService.atualizar(dto, 1);
		
		Assert.assertEquals(dto.getDescricao(), "Descrição");
		Assert.assertEquals(dto.getNomeCategoria(), "Categoria Teste");
		
	}
	@Test
	public void testAtualizarCategoriaSemId() {
		
		when(this.categoriaRepository.findById(1)).thenReturn(this.categoria);
		
		CategoriaDto dto = new CategoriaDto();
		dto.setDescricao("Descrição");
		dto.setNomeCategoria("Categoria Teste");
		
		try {
			
			this.categoriaService.atualizar(dto, 0);
			
			Assert.assertNotEquals(dto.getDescricao(), "Descrição");
			Assert.assertNotEquals(dto.getNomeCategoria(), "Categoria Teste");
		} catch (Exception e) {
			Assert.assertEquals("Categoria inválida.", e.getMessage());
		}
		
	}
	@Test
	public void testAtualizarCategoriaSemNome() {
		
		when(this.categoriaRepository.findById(1)).thenReturn(this.categoria);
		
		CategoriaDto dto = new CategoriaDto();
		dto.setDescricao("Descrição");
		
		try {
			
			this.categoriaService.atualizar(dto, 1);
			
			Assert.assertNotEquals(dto.getDescricao(), "Descrição");
			Assert.assertNotEquals(dto.getNomeCategoria(), "Categoria Teste");
		} catch (Exception e) {
			Assert.assertEquals("O nome da Categoria é obrigatório.", e.getMessage());
		}
		
	}
	@Test
	public void testAtualizarCategoriaSemDescricao() {
		
		when(this.categoriaRepository.findById(1)).thenReturn(this.categoria);
		
		CategoriaDto dto = new CategoriaDto();
		dto.setNomeCategoria("Categoria Teste");
		
		try {
			
			this.categoriaService.atualizar(dto, 1);
			
			Assert.assertNotEquals(dto.getDescricao(), "Descrição");
			Assert.assertNotEquals(dto.getNomeCategoria(), "Categoria Teste");
		} catch (Exception e) {
			Assert.assertEquals("A descrição da Categoria é obrigatória.", e.getMessage());
		}
		
	}
	@Test
	public void testDeleteCategoria() {
		when(this.categoriaRepository.findById(1)).thenReturn(this.categoria);
		when(this.produtoRepository.findByCategoriaId(1)).thenReturn(new ArrayList<Produto>());
		this.categoriaService.delete(1);
		
		
	}
	@Test
	public void testDeleteCategoriaComProdutosCadastrados() {
		List<Produto> produtos = new ArrayList<Produto>();
		produtos.add(new Produto());
		
		when(this.categoriaRepository.findById(1)).thenReturn(this.categoria);
		when(this.produtoRepository.findByCategoriaId(1)).thenReturn(new ArrayList<Produto>());
		try {
			this.categoriaService.delete(1);
		} catch (Exception e) {
			Assert.assertEquals("Categoria não poder ser excluida. Ela está associada a um ou mais produto.", e.getMessage());
		}
		
		
	}
	
	
}
