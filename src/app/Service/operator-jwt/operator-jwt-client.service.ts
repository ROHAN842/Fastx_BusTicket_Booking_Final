import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Bus } from 'src/app/Model/bus.model';
import { Operator } from 'src/app/Model/busoperator.model';

@Injectable({
  providedIn: 'root',
})
export class OperatorJwtClientService {
  apiUrl: string = 'http://localhost:8186/api/v1/busoperators/';
  baseUrl: string = 'http://localhost:8186/api/v1/buses/';
  constructor(private http: HttpClient) {}

  getGeneratedToken(requestBody: any) {
    return this.http.post(this.apiUrl + 'authenticate', requestBody, {
      responseType: 'text' as 'json',
    });
  }
  getAllBuses(token: any): Observable<any[]> {
    let tokenString = 'Bearer ' + token;
    console.log(tokenString);
    const headers = new HttpHeaders().set('Authorization', tokenString);
    console.log(headers);
    return this.http.get<any[]>(this.baseUrl + 'getAll', { headers });
  }

  updateBus(bus: Bus, token: string): Observable<any> {
    let tokenString = 'Bearer ' + token;
    console.log(tokenString);
    const headers = new HttpHeaders().set('Authorization', tokenString);
    console.log(headers);
    return this.http.put(`${this.baseUrl}update/${bus.busId}`, bus, {
      headers,
    });
  }
  deleteBus(busId: number, token: string): Observable<any> {
    let tokenString = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenString);

    return this.http.delete<any>(`${this.baseUrl}delete/${busId}`, { headers });
  }
  updateBusOperator(busoperator: Operator, token: string): Observable<any> {
    let tokenString = 'Bearer ' + token;
    console.log(tokenString);
    const headers = new HttpHeaders().set('Authorization', tokenString);
    console.log(headers);
    return this.http.put(
      `${this.apiUrl}update/${busoperator.operatorId}`,
      busoperator,
      { headers }
    );
  }
  getoperatorById(operatorId: number): Observable<Operator> {
    return this.http.get<Operator>(`${this.apiUrl}getById/${operatorId}`);
  }
}
