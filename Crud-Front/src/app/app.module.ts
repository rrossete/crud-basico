import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { TabelaComponent } from './produto/components/tabela/tabela.component';
import { CategoriaTabelaComponent } from './categoria/components/tabela/tabela.component';
import { FormsModule } from '@angular/forms';
import { UpdateComponent } from './produto/components/update/update.component';
import { PrincipalComponent } from './principal/components/principal/principal.component';
import { CategoriaUpdateComponent } from './categoria/components/categoria-update/categoria-update.component';

@NgModule({
  declarations: [
    AppComponent,
    TabelaComponent,
    UpdateComponent,
    PrincipalComponent,
    CategoriaTabelaComponent,
    CategoriaUpdateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
