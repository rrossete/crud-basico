import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.css']
})
export class PrincipalComponent implements OnInit {

  constructor() { }
  categoriaCurrent = false as boolean;
  produtoCurrent = true as boolean;

  ngOnInit(): void {}
  

  mudarCurrentProduto(){
    this.categoriaCurrent = false;
    this.produtoCurrent = true;
  }
  mudarCurrentCategoria(){
    this.categoriaCurrent = true;
    this.produtoCurrent = false;
  }

}
