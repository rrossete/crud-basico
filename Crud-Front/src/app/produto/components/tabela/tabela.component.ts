import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Categoria } from 'src/app/categoria/model/categoria';
import { CategoriaService } from 'src/app/categoria/service/categoria.service';
import { Produto } from '../../model/produto';
import { ProdutoService } from '../../service/produto.service';

@Component({
  selector: 'app-produto-tabela',
  templateUrl: './tabela.component.html',
  styleUrls: ['./tabela.component.css']
})
export class TabelaComponent implements OnInit {
  produtos: Produto[];
  categorias: Categoria[];
  produto = {} as Produto;
  

  constructor(private router: Router, private produtoService: ProdutoService, private categoriaService: CategoriaService) { }
  ngOnInit(): void {
    this.getProdutos();
    this.obterCategorias();
  }


  getProdutos() {
    this.produtoService.getProdutos().subscribe((produtos: Produto[]) => {
      this.produtos = produtos;
    });
  }

  editarProduto(produto: Produto) {
    this.router.navigateByUrl('/produto/update/' + produto.id);
  }

  deletarProduto(produto: Produto) {
    this.produtoService.deleteProduto(produto).subscribe(() => {
      this.getProdutos();
    });
  }

  salvarProduto(form: NgForm) {
    this.produtoService.saveProduto(this.produto).subscribe(() => {
      this.cleanForm(form);
    });
  }

  atualizarProduto(form: NgForm) {
    this.produtoService.updateProduto(this.produto).subscribe(() => {
      this.cleanForm(form);
    });
  }


  cleanForm(form: NgForm) {
    this.getProdutos();
    form.resetForm();
    this.produto = {} as Produto;
  }

  obterCategorias() {
    this.categoriaService.getCategorias().subscribe((categorias: Categoria[]) => {
      this.categorias = categorias;
    });
  }

  changeCategoria(e) {
    this.produto.categoria = (e.target.value);
  }

}
