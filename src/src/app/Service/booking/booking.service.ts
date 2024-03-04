import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { Booking } from 'src/app/Model/booking.model';
import { Customer } from 'src/app/Model/customer.model';
import { User } from 'src/app/Model/user.model';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  apiUrl: string = 'http://localhost:8186/api/v1/bookings/';
  baseUrl: string = 'http://localhost:8186/api/v1/buses/';
  cusUrl: string = 'http://localhost:8186/api/v1/usercustomers/';

  constructor(private http: HttpClient) { }

  createBooking( book:Booking , busId: number,userId: number): Observable<Booking> {
    return this.http.post<Booking>(`${this.apiUrl}add/${busId}/${userId}`, book);
  }
  getBusById(busId: number): Observable<any> {
    const url = `${this.baseUrl}getBusById/${busId}`;
    return this.http.get<any>(url)
     
  }
  getAllBookingsById(userId: number, token: string): Observable<any[]> {
    // Construct the Authorization header
    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + token);

    // Construct the URL to fetch bookings by user ID
    const url = `${this.apiUrl}getByUserId/${userId}`;

    // Make the HTTP GET request to fetch bookings by user ID
    return this.http.get<Booking[]>(url, { headers });
  }
  getBookingById(bookingId: number, token: string): Observable<Booking> {
    // Construct the Authorization header
    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + token);

    const url = `${this.apiUrl}getById/${bookingId}`;
    return this.http.get<Booking>(url, { headers });
  }
  occupiedSeats(date:Date,busId:number):Observable<string []>{
    
    return this.http.get<string []>(this.apiUrl+`fetchbookedseats/${date}/${busId}`);
}

 
}
