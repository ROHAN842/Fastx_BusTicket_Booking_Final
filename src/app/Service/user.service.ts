import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../Model/user.model';
import { Observable } from 'rxjs';
import { Password } from '../Model/password';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}
  apiUrl: string = 'http://localhost:8186/api/v1/usercustomers/';

  createUser(user: User): Observable<User> {
    return this.http.post<User>(this.apiUrl + 'create', user);
  }
  getUserIdByFirstName(firstName: string): Observable<User> {
    const url = `${this.apiUrl}getid/${firstName}`;
    return this.http.get<User>(url);
  }

  newPassword(requestBody: Password): Observable<string> {
    console.log(requestBody);

    return this.http.put<string>(
      'http://localhost:8186/api/v1/password/updatePassword',
      requestBody,
      { responseType: 'text' as 'json' }
    );
  }
}
