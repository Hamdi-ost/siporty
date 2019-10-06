import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { User } from '../models';

@Injectable({ providedIn: 'root' })
export class ContactService {

  apiUrl = 'https://siporty.tn:8080';
  token;
  headers;

  constructor(private http: HttpClient) { }

  getAllMessages() {
    this.token = JSON.parse(localStorage.getItem('currentUser')).token;
    this.headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.token);
    return this.http.get<User[]>(`${this.apiUrl}/messages/`, { headers: this.headers });
  }

  getMessageById(id: number) {
    this.token = JSON.parse(localStorage.getItem('currentUser')).token;
    this.headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.token);
    return this.http.get(`${this.apiUrl}/messages/${id}`, { headers: this.headers });
  }

  deleteMessage(id: number) {
    this.token = JSON.parse(localStorage.getItem('currentUser')).token;
    this.headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.token);
    return this.http.delete(`${this.apiUrl}/messages/${id}`, { headers: this.headers });
  }
}
