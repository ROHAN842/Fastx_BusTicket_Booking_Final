import {
  HttpClient,
  HttpHeaders,
  HttpErrorResponse,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Password } from 'src/app/Model/password';
import { User } from 'src/app/Model/user.model';
import { Bus } from 'src/app/Model/bus.model';

@Injectable({
  providedIn: 'root',
})
export class UserJwtClientService {
  apiUrl: string = 'http://localhost:8186/api/v1/usercustomers/';
  apibUrl: string = 'http://localhost:8186/api/v1/bookings/';
  singleurl: string = 'http://localhost:8186/api/v1/allsinglelogin/';
  apibusUrl: string = 'http://localhost:8186/api/v1/buses/';

  constructor(private http: HttpClient) {}

  getGeneratedToken(requestBody: any) {
    return this.http.post(this.singleurl + 'authenticate', requestBody, {
      responseType: 'text' as 'json',
    });
  }
  getRole(firstName: String) {
    return this.http.get<string>(this.singleurl + `gettherole/${firstName}`, {
      responseType: 'text' as 'json',
    });
  }
  getId(firstName: String) {
    return this.http.get(this.singleurl + `getId/${firstName}`);
  }
  updateUser(user: User, token: string): Observable<any> {
    let tokenString = 'Bearer ' + token;
    console.log(tokenString);
    const headers = new HttpHeaders().set('Authorization', tokenString);
    console.log(headers);
    return this.http.put(`${this.apiUrl}update/${user.userId}`, user, {
      headers,
    });
  }
  getUserById(userId: number, token: string): Observable<User> {
    // Construct the Authorization header
    const tokenString = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenString);

    return this.http.get<User>(`${this.apiUrl}getById/${userId}`, { headers });
  }
  sendEmailOnBooking(bookingId: number): Observable<any> {
    return this.http.get(`${this.apibUrl}sendemailonbooking/${bookingId}`);
  }

  allBus(token: any): Observable<Bus[]> {
    let tokenString = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenString);

    return this.http.get<Bus[]>(this.apibusUrl + 'getAll', { headers });
  }
}
