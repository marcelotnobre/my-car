import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BASE_URL } from '../../constants/url';
import User from '../models/users';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private readonly URL = `${BASE_URL}/users`;

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  constructor(private httpClient: HttpClient) {
  }

  save(user: User) {
    return this.httpClient.post(this.URL, JSON.stringify(user), this.httpOptions);
  }

  findAll(): Observable<User[]> {
    return this.httpClient.get(this.URL, this.httpOptions) as Observable<User[]>;
  }

  delete(id: number) {
    return this.httpClient.delete(`${this.URL}/${id}`)
  }
}
