import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PaymeeService {

  apiUrl = 'http://sandbox.paymee.tn';
  token;
  headers;

  constructor(private http: HttpClient) { }

  initiate(input) {
    this.token = JSON.parse(localStorage.getItem('currentUser')).token;
    this.headers = new HttpHeaders().set('Authorization', 'eca19723415deb2806c333eb34b2b082768874e3');
    return this.http.post(this.apiUrl + '/api/OPRequest/', input, { headers: this.headers })
  }

  startPayment(value) {
    this.token = JSON.parse(localStorage.getItem('currentUser')).token;
    this.headers = new HttpHeaders().set('Authorization', 'eca19723415deb2806c333eb34b2b082768874e3');
    return this.http.post(this.apiUrl + '/gateway/', value, { headers: this.headers })
  }

  verifyPayment(token) {
    this.token = JSON.parse(localStorage.getItem('currentUser')).token;
    this.headers = new HttpHeaders().set('Authorization', 'eca19723415deb2806c333eb34b2b082768874e3');
    return this.http.post(this.apiUrl + '/api/OPCheck/', token, { headers: this.headers });
  }
}

