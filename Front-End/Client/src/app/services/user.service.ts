import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models';

@Injectable({ providedIn: 'root' })
export class UserService implements OnInit {

  apiUrl = 'https://siporty.tn:8080';
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

  changePassword(updatedUser) {
    const postDate = new FormData();
    postDate.append('password', updatedUser.password);
    postDate.append('socialLink', updatedUser.socialLink);
    postDate.append('image', updatedUser.image);

    this.token = JSON.parse(localStorage.getItem('currentUser')).token;
    this.headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.token);
    return this.http.put(`${this.apiUrl}/users/change-password`, postDate, { headers: this.headers });
  }

  getUserByUsername(username) {
    return this.http.get(`${this.apiUrl}/users/auth/username/` + username);
  }





}
