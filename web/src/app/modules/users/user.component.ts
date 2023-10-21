import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { catchError, of, retry } from 'rxjs';
import { UsersService } from 'src/app/services/users.service';
import User from '../../models/users';
@Component({
  selector: 'user-form',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {

  user: User = {
    id: undefined,
    firstName: '',
    lastName: '',
    email: '',
    birthday: undefined,
    login: '',
    password: '',
    phone: '',
  }

  constructor(private service: UsersService) {
  }

  submit(e: SubmitEvent) {
    e.preventDefault()
    this.service.save(this.user)
      .pipe(retry(0), catchError((error: HttpErrorResponse) => { console.log(error); return of('Teste') }))
      .subscribe(e => { console.log(e) })
  }

}
