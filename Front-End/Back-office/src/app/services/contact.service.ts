import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { User } from '../models';

@Injectable({ providedIn: 'root' })
export class ContactService {

  apiUrl = 'http://localhost:8080';
  token = JSON.parse(localStorage.getItem('currentUser')).token;
  headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.token);

  constructor(private http: HttpClient) { }

  getAllMessages() {
    return this.http.get<User[]>(`${this.apiUrl}/messages/`, { headers: this.headers });
  }

  getMessageById(id: number) {
    return this.http.get(`${this.apiUrl}/messages/${id}`, { headers: this.headers });
  }

  deleteMessage(id: number) {
    return this.http.delete(`${this.apiUrl}/messages/${id}`, { headers: this.headers });
  }
}
