import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { User } from '../models';

@Injectable({ providedIn: 'root' })
export class UserService implements OnInit {

  apiUrl = 'http://localhost:8080';
  token;
  headers;


  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  getById(id: number) {
    return this.http.get(`${this.apiUrl}/user/${id}`);
  }

  register(user: User) {
    return this.http.post(`${this.apiUrl}/auth/users/register`, user);
  }

  update(user: any) {
    this.token = JSON.parse(localStorage.getItem('currentUser')).token;
    this.headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.token);
    return this.http.put(`${this.apiUrl}/users/`, user, { headers: this.headers });
  }

  changePassword(newPassword) {
    this.token = JSON.parse(localStorage.getItem('currentUser')).token;
    this.headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.token);
    return this.http.put(`${this.apiUrl}/users/change-password`, newPassword, { headers: this.headers });
  }

  getUserByUsername(username) {
    return this.http.get(`${this.apiUrl}/users/auth/username/` + username);
  }

  OnUpload(img)
  {


  }

}
