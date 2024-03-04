import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { Admin } from '../Model/admin.model';
import { User } from '../Model/user.model';
import { Booking } from '../Model/booking.model';
import { Bus } from '../Model/bus.model';
import { Operator } from '../Model/busoperator.model';

@Injectable({
  providedIn: 'root'
})
export class AdminJwtClientService {

 


  baseURL: string = 'http://localhost:8186/api/v1/admins/';
  apibusUrl:string='http://localhost:8186/api/v1/buses/';
  apiUserUrl:string='http://localhost:8186/api/v1/usercustomers/';
  apiOperatorUrl:string='http://localhost:8186/api/v1/busoperators/';

 
  constructor(private http: HttpClient) { }

  getGeneratedToken(requestBody: any) {

    return this.http.post(this.baseURL + "authenticate", requestBody, { responseType: 'text' as 'json' });

  }

  alladmins(token: any): Observable<Admin[]> {
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders().set("Authorization", tokenString);
  
    return this.http.get<Admin[]>(this.baseURL + "getall", { headers });
  }
  getAdminById(adminId: number, token: string): Observable<Admin> {
    // Construct the Authorization header
    const tokenString = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenString);

    // Make the HTTP GET request to fetch the admin by ID
    return this.http.get<Admin>(`${this.baseURL}getById/${adminId}`, { headers });
  }
 
  
  authenticate(credentials: any): Observable<any> {
    return this.http.post(this.baseURL + 'authenticate', credentials, {
      responseType: 'text' as 'json'
    });
  }

  delete(adminId: number, token: string): Observable<any> {
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders().set("Authorization", tokenString);

    return this.http.delete<any>(`${this.baseURL}delete/${adminId}`, { headers });
  }

  updateAdmin(admins: Admin, token: string): Observable<any> {
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders().set("Authorization", tokenString);
   return this.http.put<any>(`${this.baseURL}update/${admins.adminId}`,admins,{headers});
  }
  createBus(bus: Bus,token:string):Observable<Bus>{
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders().set("Authorization", tokenString);
    return this.http.post<Bus>(this.apibusUrl+"create",bus,{headers});
  }
  allUsers(token: any): Observable<User[]> {
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders().set("Authorization", tokenString);
  
    return this.http.get<User[]>(this.apiUserUrl + "getall", { headers });
  }
  deleteUser(userId: number, token: string): Observable<any> {
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders().set("Authorization", tokenString);

    return this.http.delete<any>(`${this.apiUserUrl}delete/${userId}`, { headers });
  }
  allBusOperators(token: any): Observable<Operator[]> {
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders().set("Authorization", tokenString);
  
    return this.http.get<Operator[]>(this.apiOperatorUrl + "getAll", { headers });
  }
  deleteOperator(operatorId: number, token: string): Observable<any> {
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders().set("Authorization", tokenString);

    return this.http.delete<any>(`${this.apiOperatorUrl}delete/${operatorId}`, { headers });
  }

  allBus(token: any): Observable<Bus[]> {
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders().set("Authorization", tokenString);
  
    return this.http.get<Bus[]>(this.apibusUrl + "getAll", { headers });
  }

  deleteBus(busId: number, token: string): Observable<any> {
    let tokenString = "Bearer " + token;
    const headers = new HttpHeaders().set("Authorization", tokenString);

    return this.http.delete<any>(`${this.apibusUrl}delete/${busId}`, { headers });
  }


 
  


}
