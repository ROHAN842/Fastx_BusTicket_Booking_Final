import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Bus } from '../Model/bus.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  apibusUrl:string='http://localhost:8186/api/v1/buses/';
  apicustomerUrl:string='http://localhost:8186/api/v1/customer/';

  constructor(private http: HttpClient) { }

  getAllBusesByCitiesAndDate(sourceCity: string, destinationCity: string, date: Date): Observable<Bus[]> {
    const params = new HttpParams()
      .set('sourceCity', sourceCity)
      .set('destinationCity', destinationCity)
      .set('date', date.toString());

    return this.http.get<Bus[]>(`${this.apibusUrl}details`, { params });
  }
 


}
