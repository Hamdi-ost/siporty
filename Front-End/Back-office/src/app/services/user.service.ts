import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { User } from '../models';

@Injectable({ providedIn: 'root' })
export class UserService {

    apiUrl = 'http://localhost:8080';
    token = JSON.parse(localStorage.getItem('currentUser')).token;
    headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.token);
    
    constructor(private http: HttpClient) { }

    getAll() {  
        return this.http.get<User[]>(`${this.apiUrl}/users/`, { headers: this.headers });
    }

    getById(id: number) {
        return this.http.get(`${this.apiUrl}/user/${id}`);
    }

    register(user: User) {
        console.log(this.token);
        return this.http.post(`${this.apiUrl}/users/`, user, { headers: this.headers } );
    }

    update(user: User) {
        return this.http.put(`${this.apiUrl}/users/${user.id}`, user);
    }

    ban(id: number) {
        return this.http.delete(`${this.apiUrl}/user/${id}`);
    }

    unban() {

    }
}
