import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CarsComponent } from './modules/cars/cars.component';
import { UserComponent } from './modules/users/user.component';
import { UsersListComponent } from './modules/users/users-list/users-list.component';

const routes: Routes = [{ path: 'usuarios', component: UserComponent },
{ path: 'cars', component: CarsComponent }, {
  path: 'usuarios/listar', component: UsersListComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

