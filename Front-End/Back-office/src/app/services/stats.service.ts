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
    let httpParams = new HttpParams()
      .set('date', "01/05/2019");
    console.log(httpParams.toString());
    this.token = JSON.parse(localStorage.getItem('currentUser')).token;
    this.headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.token);
    return this.http.get(`${this.apiUrl}`, { headers: this.headers, params: httpParams });
  }
}
