package com.affero.produto;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import com.affero.categoria.Categoria;
import com.affero.categoria.CategoriaService;


@SpringBootTest
public class ProdutoControllerTest {
	
	private ProdutoService produtoService;
	
	@Mock
	private ProdutoRepository produtoRepository;
	@Mock
	private CategoriaService categoriaService;
	
	private Optional<Produto> produto;
	private Categoria categoria ;
	
	@Before
	public void antes() {
		
		MockitoAnnotations.initMocks(this);
		produtoService =  new ProdutoService();
		ReflectionTestUtils.setField(this.produtoService, "produtoRepository", this.produtoRepository);
		ReflectionTestUtils.setField(this.produtoService, "categoriaService", this.categoriaService);
		
		this.categoria = new Categoria();
		this.categoria.setDescricao("Descrição teste");
		this.categoria.setId(1);
		this.categoria.setNomeCategoria("Teste");
		
		this.produto = Optional.ofNullable(new Produto());
		this.produto.get().setCategoria(categoria);
		this.produto.get().setCodigoBarra("11231546872");
		this.produto.get().setDescricao("Descrição produto teste");
		this.produto.get().setId(1);
		this.produto.get().setNomeProduto("produto");
		this.produto.get().setQuantidade(10);
		this.produto.get().setValor(10.90);
	}
	
	@Test
	public void testNovoProduto(){
			
		when(this.categoriaService.obterCategoriaPorId(1)).thenReturn(this.categoria);
		when(this.produtoRepository.save(Mockito.any())).thenReturn(this.produto.get());
		
		ProdutoDto dto = new ProdutoDto();
		dto.setCategoria(1);
		dto.setCodigoBarra("11231546872");
		dto.setDescricao("Descrição produto teste");
		dto.setId(1);
		dto.setNomeProduto("produto");
		dto.setQuantidade(10);
		dto.setValor(10.90);
		
		Produto retorno = this.produtoService.novo(dto);
		
		Assert.assertEquals(retorno.getDescricao(), "Descrição produto teste");
		Assert.assertEquals(retorno.getNomeProduto() ,"produto");
		
	}
	@Test
	public void testNovoProdutoSemDescricao(){
		
		when(this.categoriaService.obterCategoriaPorId(1)).thenReturn(this.categoria);
		when(this.produtoRepository.save(Mockito.any())).thenReturn(this.produto.get());
		
		ProdutoDto dto = new ProdutoDto();
		dto.setCategoria(1);
		dto.setCodigoBarra("11231546872");
		dto.setId(1);
		dto.setNomeProduto("produto");
		dto.setQuantidade(10);
		dto.setValor(10.90);
		
		try {
			Produto retorno = this.produtoService.novo(dto);
			Assert.assertNotEquals(retorno.getDescricao(), "Descrição produto teste");
			Assert.assertNotEquals(retorno.getNomeProduto() ,"produto");
		} catch (Exception e) {
			Assert.assertEquals("A descrição do produto é obrigatória.", e.getMessage());
		}
	
	}
	@Test
	public void testNovoProdutoSemCodigoBarras(){
		
		when(this.categoriaService.obterCategoriaPorId(1)).thenReturn(this.categoria);
		when(this.produtoRepository.save(Mockito.any())).thenReturn(this.produto.get());
		
		ProdutoDto dto = new ProdutoDto();
		dto.setCategoria(1);
		dto.setDescricao("Descrição produto teste");
		dto.setId(1);
		dto.setNomeProduto("produto");
		dto.setQuantidade(10);
		dto.setValor(10.90);
		
		
		try {
			Produto retorno = this.produtoService.novo(dto);
			Assert.assertNotEquals(retorno.getDescricao(), "Descrição produto teste");
			Assert.assertNotEquals(retorno.getNomeProduto() ,"produto");
		} catch (Exception e) {
			Assert.assertEquals("O código de barras é obrigatório.", e.getMessage());
		}
		
	}
	@Test
	public void testNovoProdutoSemValor(){
		
		when(this.categoriaService.obterCategoriaPorId(1)).thenReturn(this.categoria);
		when(this.produtoRepository.save(Mockito.any())).thenReturn(this.produto.get());
		
		ProdutoDto dto = new ProdutoDto();
		dto.setCategoria(1);
		dto.setCodigoBarra("11231546872");
		dto.setDescricao("Descrição produto teste");
		dto.setId(1);
		dto.setNomeProduto("produto");
		dto.setQuantidade(10);
		
		
		try {
			Produto retorno = this.produtoService.novo(dto);
			Assert.assertNotEquals(retorno.getDescricao(), "Descrição produto teste");
			Assert.assertNotEquals(retorno.getNomeProduto() ,"produto");
		} catch (Exception e) {
			Assert.assertEquals("O valor do produto é obrigatório.", e.getMessage());
		}
		
	}
	@Test
	public void testNovoProdutoSemNomeProduto(){
		
		when(this.categoriaService.obterCategoriaPorId(1)).thenReturn(this.categoria);
		when(this.produtoRepository.save(Mockito.any())).thenReturn(this.produto.get());
		
		ProdutoDto dto = new ProdutoDto();
		dto.setCategoria(1);
		dto.setCodigoBarra("11231546872");
		dto.setDescricao("Descrição produto teste");
		dto.setId(1);
		dto.setQuantidade(10);
		dto.setValor(10.90);
		
		
		try {
			Produto retorno = this.produtoService.novo(dto);
			Assert.assertNotEquals(retorno.getDescricao(), "Descrição produto teste");
			Assert.assertNotEquals(retorno.getNomeProduto() ,"produto");
		} catch (Exception e) {
			Assert.assertEquals("O nome do produto é obreigatório.", e.getMessage());
		}
		
	}
	@Test
	public void testNovoProdutoSemQuantidade(){
		
		when(this.categoriaService.obterCategoriaPorId(1)).thenReturn(this.categoria);
		when(this.produtoRepository.save(Mockito.any())).thenReturn(this.produto.get());
		
		ProdutoDto dto = new ProdutoDto();
		dto.setCategoria(1);
		dto.setCodigoBarra("11231546872");
		dto.setDescricao("Descrição produto teste");
		dto.setId(1);
		dto.setNomeProduto("produto");
		dto.setValor(10.90);
		
		
		try {
			Produto retorno = this.produtoService.novo(dto);
			Assert.assertNotEquals(retorno.getDescricao(), "Descrição produto teste");
			Assert.assertNotEquals(retorno.getNomeProduto() ,"produto");
		} catch (Exception e) {
			Assert.assertEquals("A quantidade do produto é obrigatório", e.getMessage());
		}
		
	}

	@Test
	public void testAtualizarProduto(){
		
		when(this.produtoRepository.findById(1)).thenReturn(this.produto);
		when(this.categoriaService.obterCategoriaPorId(1)).thenReturn(this.categoria);
		
		ProdutoDto dto = new ProdutoDto();
		dto.setCategoria(1);
		dto.setCodigoBarra("11231546872");
		dto.setDescricao("Descrição produto teste");
		dto.setId(1);
		dto.setNomeProduto("produto");
		dto.setQuantidade(10);
		dto.setValor(10.90);
		
		this.produtoService.atualizar(dto, 1);
		
	}
	
	@Test
	public void testObterProduto(){
		
		when(this.produtoRepository.findById(1)).thenReturn(this.produto);
		
		ProdutoDto produtoDto = this.produtoService.getById(1);
		
		Assert.assertEquals(produtoDto.getDescricao(), "Descrição produto teste");
		Assert.assertEquals(produtoDto.getNomeProduto() ,"produto");
		
	}

}
