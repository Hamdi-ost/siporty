import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DonationsService {
  token;
  headers;
  apiUrl = 'https://siporty.tn:8080/donation-details/';

  constructor(private http: HttpClient) { }

  getAllDonations() {
    this.token = JSON.parse(localStorage.getItem('currentUser')).token;
    this.headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.token);
    return this.http.get(`${this.apiUrl}`, { headers: this.headers });
  }

  resetSolde(id) {
    this.token = JSON.parse(localStorage.getItem('currentUser')).token;
    this.headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.token);
    return this.http.delete(`${this.apiUrl}` + 'check/' + id, { headers: this.headers });
  }
}
