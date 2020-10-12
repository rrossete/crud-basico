import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Categoria } from 'src/app/categoria/model/categoria';
import { CategoriaService } from 'src/app/categoria/service/categoria.service';
import { Produto } from '../../model/produto';
import { ProdutoService } from '../../service/produto.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute,
              private produtoService: ProdutoService,
              private categoriaService: CategoriaService,
              private router: Router) {  }

    produto = {} as Produto;
    categorias: Categoria[];

    errorMessage = '' as string;
    modalErro = false as boolean;

  ngOnInit(): void {
    this.obterProduto();
    this.obterCategorias();
  }


  private obterProduto() {
    const idProduto: number = +this.activatedRoute.snapshot.paramMap.get('param');
    this.produtoService.getProduto(idProduto).subscribe((produto: Produto) => {
      this.produto = produto;
    });
  }

  obterCategorias() {
    this.categoriaService.getCategorias().subscribe((categorias: Categoria[]) => {
      this.categorias = categorias;
    });
  }

  changeCategoria(e) {
    this.produto.categoria = (e.target.value);
  }

  cleanForm(form: NgForm) {
    form.resetForm();
    this.produto = {} as Produto;
  }


  cancelar(form: NgForm) {
    form.resetForm();
    this.produto = {} as Produto;
    this.router.navigateByUrl('');

  }

  atualizarProduto(form: NgForm) {
    this.produtoService.updateProduto(this.produto).subscribe(() => {
      this.router.navigateByUrl('');
      this.cleanForm(form);
    },
    (err) => {
      this.errorMessage = err;
      this.modalErro = true;

    });

  }

  closeModal(){
    this.modalErro = false;
}

}
