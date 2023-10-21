import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CarsComponent } from './modules/cars/cars.component';
import { UserComponent } from './modules/users/user.component';

const routes: Routes = [{ path: 'usuarios', component: UserComponent }, { path: 'cars', component: CarsComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

