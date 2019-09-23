import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymeeService {

  apiUrl = 'https://app.paymee.tn';
  headers;



  constructor(private http: HttpClient) { }



  initiate(input) {
    const requestHeaders = new HttpHeaders().set('Authorization', 'Token 7fdc06e669739f1e4f10322863eafff60a5e1e1c');
    return this.http.post( 'https://app.paymee.tn/api/OPRequest/', input, { headers: requestHeaders } );

  }

  startPayment(value) {
    console.log(value);
    this.headers = new HttpHeaders().set('Authorization', 'Token 7fdc06e669739f1e4f10322863eafff60a5e1e1c');
    return this.http.post(this.apiUrl + '/gateway/', value, { headers: this.headers });
  }

  verifyPayment(token) {
    this.headers = new HttpHeaders().set('Authorization', 'Token 7fdc06e669739f1e4f10322863eafff60a5e1e1c');
    return this.http.post(this.apiUrl + '/api/OPCheck/', token, { headers: this.headers });
  }




}

