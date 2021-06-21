import { RegistracijaComponent } from './registracija/registracija.component';
import { LoginComponent } from './login/login.component';
import { DriveComponent } from './drive/drive.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path:'', component: RegistracijaComponent, pathMatch:'full'},
  {path:'drive', component: DriveComponent, pathMatch:'full'},
  {path:'login', component: LoginComponent, pathMatch:'full'},
  { path: '**', redirectTo: '' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
