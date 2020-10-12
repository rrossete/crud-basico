import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Categoria } from '../../model/categoria';
import { CategoriaService } from '../../service/categoria.service';

@Component({
  selector: 'app-categoria-update',
  templateUrl: './categoria-update.component.html',
  styleUrls: ['./categoria-update.component.css']
})
export class CategoriaUpdateComponent implements OnInit {

  categoria = {} as Categoria;
  errorMessage = '' as string;
  modalErro = false as boolean;

  constructor(private activatedRoute: ActivatedRoute,
              private categoriaService: CategoriaService,
              private router: Router) { }

  ngOnInit(): void {
    this.obterCategoria();
  }

  private obterCategoria() {
    const idCategoria: number = +this.activatedRoute.snapshot.paramMap.get('param');
    this.categoriaService.getCategoria(idCategoria).subscribe((categoria: Categoria) => {
      this.categoria = categoria;
    });
  }

  atualizarCategoria(form: NgForm) {
    this.categoriaService.updateCategoria(this.categoria).subscribe(() => {
      this.cleanForm(form);
      this.router.navigateByUrl('');
    },
    (err) => {
      this.errorMessage = err;
      this.modalErro = true;

    });

  }

  cancelar(form: NgForm) {
    form.resetForm();
    this.categoria = {} as Categoria;
    this.router.navigateByUrl('');

  }

  cleanForm(form: NgForm) {
    form.resetForm();
    this.categoria = {} as Categoria;
  }

  closeModal(){
    this.modalErro = false;
}


}
