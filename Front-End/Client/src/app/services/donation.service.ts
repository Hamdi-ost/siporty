import { Observable, of, throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError, tap, map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { Donation } from '../models/donation';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
const apiUrl = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class DonationService {
  token;
  headers;
  constructor(private http: HttpClient) { }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a Donation-facing error message
    return throwError('Something bad happened; please try again later.');
  }

  private extractData(res: Response) {
    const body = res;
    return Donation.dataToDisplay(body) || {};
  }

  postDonation(data): Observable<any> {
    console.log(data);
    return this.http.post(apiUrl + 'donations/auth/', data);
  }

  postDonationDetails(data) {
    return this.http.post(apiUrl + 'donation-details/auth/', data);
  }

  getDonationDetailsByUsername(username) {
    return this.http.get(apiUrl + 'donation-details/auth/' + username);
  }



}
