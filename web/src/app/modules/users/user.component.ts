import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'user-list',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  nomeFormControl = new FormControl();
}
