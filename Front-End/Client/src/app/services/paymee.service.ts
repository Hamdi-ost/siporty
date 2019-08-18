import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymeeService {

  apiUrl = 'http://sandbox.paymee.tn';
  headers;



  constructor(private http: HttpClient) { }



  initiate(input) {
    const requestHeaders = new HttpHeaders().set('Authorization', 'Token eca19723415deb2806c333eb34b2b082768874e3');
    return this.http.post( 'http://sandbox.paymee.tn/api/OPRequest/', input, { headers: requestHeaders } );

  }

  startPayment(value) {
    console.log(value);
    this.headers = new HttpHeaders().set('Authorization', 'Token eca19723415deb2806c333eb34b2b082768874e3');
    return this.http.post(this.apiUrl + '/gateway/', value, { headers: this.headers });
  }

  verifyPayment(token) {
    this.headers = new HttpHeaders().set('Authorization', 'Token eca19723415deb2806c333eb34b2b082768874e3');
    return this.http.post(this.apiUrl + '/api/OPCheck/', token, { headers: this.headers });
  }




}

