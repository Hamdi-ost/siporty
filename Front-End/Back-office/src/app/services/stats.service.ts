import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class StatsService {

  apiUrl = 'http://localhost:8080/donation-details/stats/';
  token;
  headers;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {

  }

  getAllStats() {
    this.token = JSON.parse(localStorage.getItem('currentUser')).token;
    this.headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.token);
    return this.http.post(`${this.apiUrl}`, { date: '02/05/2018' }, { headers: this.headers });
  }
}
