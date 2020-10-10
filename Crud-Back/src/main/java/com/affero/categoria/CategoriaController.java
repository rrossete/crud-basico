package com.affero.categoria;

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
@RequestMapping(value = "/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping()
	public ResponseEntity<List<CategoriaDto>> getCategorias() {
		return ResponseEntity.ok().body(this.categoriaService.getProdutos());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<CategoriaDto> getCategoriaById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(this.categoriaService.getById(id));

	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Void> atualizarCategoria(@RequestBody CategoriaDto dto, @PathVariable Integer id) {
		this.categoriaService.atualizar(dto, id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable Integer id) {
		this.categoriaService.delete(id);
	}

	@PostMapping
	public ResponseEntity<Void> novo(@RequestBody CategoriaDto categoriaDto) {

		Categoria categoria = this.categoriaService.novo(categoriaDto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

}
