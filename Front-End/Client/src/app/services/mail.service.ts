import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class MailService implements OnInit {

  apiUrl = 'https://siporty.tn:8080';



  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }


  registerValidate(id: Number) {
     this.http.get(`${this.apiUrl}/register/validate-email/${id}`);
  }

  accountActivation(id: Number) {
      this.http.get(`${this.apiUrl}/auth/users/register/validate/${id}`);
  }

  identification(email: String) {
    this.http.get(`${this.apiUrl}/auth/users/reset-password/${email}`);
}


}
