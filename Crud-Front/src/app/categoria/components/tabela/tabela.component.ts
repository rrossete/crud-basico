import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { empty } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Categoria } from '../../model/categoria';
import { CategoriaService } from '../../service/categoria.service';

@Component({
  selector: 'app-categoria-tabela',
  templateUrl: './tabela.component.html',
  styleUrls: ['./tabela.component.css']
})
export class CategoriaTabelaComponent implements OnInit {

  categorias: Categoria[];
  categoria = {} as Categoria;
  errorMessage = '' as string;
  modalErro = false as boolean;
  

  constructor(private categoriaService: CategoriaService, 
              private router: Router) {
  }

  ngOnInit(): void {
    this.obterCategorias();
    this.errorMessage = '';
    this.modalErro = false
    ;
  }

  editarCategoria(categoria: Categoria) {
    this.router.navigateByUrl('/categoria/update/' + categoria.id);
  }

  deletarCategoria(categoria: Categoria) {
    this.categoriaService.deleteCategoria(categoria).subscribe(() => {
      this.obterCategorias();

    },
    (err) => {
      this.errorMessage = err;
      this.modalErro = true;

    }
    );
  }



  obterCategorias() {
    this.categoriaService.getCategorias().subscribe((categorias: Categoria[]) => {
      this.categorias = categorias;
    });
  }

  salvarCategoria(form: NgForm) {
    this.categoriaService.saveCategoria(this.categoria).subscribe(() => {
      this.obterCategorias();
      this.cleanForm(form);
    },
    (err) => {
      this.errorMessage = err;
      this.modalErro = true;

    });
  }

  cleanForm(form: NgForm) {
    form.resetForm();
    this.categoria = {} as Categoria;
  }

  closeModal(){
      this.modalErro = false;
  }


}
