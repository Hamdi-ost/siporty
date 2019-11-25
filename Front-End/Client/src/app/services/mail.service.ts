import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

// const httpOptions = {
//   headers: new HttpHeaders({ 'Content-Type': 'text' })
// };

@Injectable({ providedIn: 'root' })
export class MailService implements OnInit {

  apiUrl = 'https://siporty.tn:8080/auth/users';

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  registerValidate(id: Number) {
    return this.http.get(this.apiUrl + '/register/validate/' + id);
  }

  accountActivation(id: Number) {
    return this.http.get(this.apiUrl + '/register/validate-email/' + id);
  }

  identification(email: String) {
    const headers = new HttpHeaders().set('responseType', 'text as json');
    return this.http.get(this.apiUrl + '/reset-password/' + email);
  }

}
