import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoriaUpdateComponent } from './categoria/components/categoria-update/categoria-update.component';
import { PrincipalComponent } from './principal/components/principal/principal.component';
import { UpdateComponent } from './produto/components/update/update.component';


const routes: Routes = [
  {path: 'produto/update/:param', component: UpdateComponent},
  {path: 'categoria/update/:param', component: CategoriaUpdateComponent},
  {path: '', component: PrincipalComponent}
];
RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' });

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
