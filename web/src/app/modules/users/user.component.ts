import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { catchError, of, retry, throwError } from 'rxjs';
import { UsersService } from 'src/app/services/users.service';
import User from '../../models/users';
@Component({
  selector: 'user-list',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  firstNameFormControl = new FormControl();
  lastNameFormControl = new FormControl();
  emailFormControl = new FormControl();
  phoneFormControl = new FormControl();
  loginFormControl = new FormControl();
  passwordFormControl = new FormControl();

  user: User = {
    firstName: '',
    lastName: '',
    email: '',
    birthday: undefined,
    login: '',
    password: '',
    phone: '',
  }

  private formulario?: FormGroup;

  constructor(private service: UsersService) {
  }

  submit(e: any) {
    e.preventDefault();
    this.service.save(this.user)
      .pipe(retry(0), catchError((error: HttpErrorResponse) => { console.log(error); return of('Teste') }))
      .subscribe(e => { console.log(e) })
  }

}
