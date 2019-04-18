import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { User } from '../models';

@Injectable({ providedIn: 'root' })
export class UserService {

    apiUrl = 'http://localhost:8080';
    token;
    headers;

    constructor(private http: HttpClient) { }

    ngOnInit(): void {

    }

    getAllUsers() {
        this.token = JSON.parse(localStorage.getItem('currentUser')).token;
        this.headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.token);
        return this.http.get<User[]>(`${this.apiUrl}/users/role/user`, { headers: this.headers });
    }

    getAllAdmins() {
        this.token = JSON.parse(localStorage.getItem('currentUser')).token;
        this.headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.token);
        return this.http.get<User[]>(`${this.apiUrl}/users/role/admin`, { headers: this.headers });
    }

    getById(id: number) {
        return this.http.get(`${this.apiUrl}/user/${id}`);
    }

    register(user: User) {
        this.token = JSON.parse(localStorage.getItem('currentUser')).token;
        this.headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.token);
        return this.http.post(`${this.apiUrl}/users/`, user, { headers: this.headers });
    }

    // update(user: User) {
    //     return this.http.put(`${this.apiUrl}/users/${user.id}`, user);
    // }

    ban(id: number) {
        this.token = JSON.parse(localStorage.getItem('currentUser')).token;
        this.headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.token);
        return this.http.delete(`${this.apiUrl}/users/${id}`, { headers: this.headers });
    }

    unban(id: number) {
        this.token = JSON.parse(localStorage.getItem('currentUser')).token;
        this.headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.token);
        return this.http.delete(`${this.apiUrl}/users/unban/${id}`, { headers: this.headers });
    }
}
