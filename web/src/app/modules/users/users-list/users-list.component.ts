import { Component, OnInit } from '@angular/core';
import { UsersService } from 'src/app/services/users.service';
import User from '../../../models/users';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css'],
})
export class UsersListComponent implements OnInit {
  displayedColumns: string[] = ['firstName', 'lastName', 'email', 'phone', 'delete'];
  dataSource: User[] = [];

  constructor(private userService: UsersService) {
  }

  ngOnInit(): void {
    this.userService.findAll()
      .subscribe(data => {
        console.log(data)
        this.dataSource = data
      })
  }

  delete(id: number) {
    this.userService.delete(id)
      .subscribe(() => { this.dataSource = this.dataSource.filter(user => user.id !== id) });
  }

}
