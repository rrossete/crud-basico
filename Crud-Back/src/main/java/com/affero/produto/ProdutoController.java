package com.affero.produto;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping()
	public ResponseEntity<List<ProdutoDto>> getProdutos() {
		return ResponseEntity.ok().body(this.produtoService.getProdutos());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<ProdutoDto> getProdutoById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(this.produtoService.getById(id));

	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Void> atualizarProduto(@RequestBody ProdutoDto dto, @PathVariable Integer id) {
		this.produtoService.atualizar(dto, id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> atualizarProduto(@PathVariable Integer id) {
		this.produtoService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<Void> novo(@RequestBody ProdutoDto produtoDto) {

		Produto produto = this.produtoService.novo(produtoDto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

}
