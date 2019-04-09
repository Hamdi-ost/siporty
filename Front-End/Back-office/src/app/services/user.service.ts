import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '../models';

@Injectable({ providedIn: 'root' })
export class UserService {

   apiUrl = 'http://localhost:8080';
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<User[]>(`${this.apiUrl}/users`);
    }

    getById(id: number) {
        return this.http.get(`${this.apiUrl}/user/${id}`);
    }

    register(user: User) {
        return this.http.post(`${this.apiUrl}/auth/users/register`, user);
    }

    update(user: User) {
        return this.http.put(`${this.apiUrl}/users/${user.id}`, user);
    }

    ban(id: number) {
        return this.http.delete(`${this.apiUrl}/user/${id}`);
    }
}
