import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BASE_URL } from '../../constants/url';
import User from '../models/users';


@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private readonly URL = `${BASE_URL}`;

  constructor(private httpClient: HttpClient) {
  }

  save(user: User) {
    return this.httpClient.post(this.URL, JSON.stringify(user));
  }
}
