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
    console.log(this.todaysDate());
    this.token = JSON.parse(localStorage.getItem('currentUser')).token;
    this.headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.token);
    return this.http.post(`${this.apiUrl}`, { date: this.todaysDate() }, { headers: this.headers });

  }

  todaysDate() {
    const today = new Date();
    const dd = String(today.getDate()).padStart(2, '0');
    const mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    const yyyy = today.getFullYear();
      return dd + '/' + mm + '/' + yyyy;
  }
}
