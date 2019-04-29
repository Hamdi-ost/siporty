import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class StatsService {

  apiUrl = 'http://localhost:8080/stats/';
  token;
  headers;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {

  }

  getAllStats() {
    this.token = JSON.parse(localStorage.getItem('currentUser')).token;
    this.headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.token);
    return this.http.get(`${this.apiUrl}`, { headers: this.headers });
  }
}
