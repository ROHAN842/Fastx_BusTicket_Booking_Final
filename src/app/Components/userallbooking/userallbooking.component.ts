// userallbooking.component.ts

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Booking } from 'src/app/Model/booking.model';
import { BookingService } from 'src/app/Service/booking/booking.service';
import { UserJwtClientService } from 'src/app/Service/user-jwt/user-jwt-client.service';
import { TokenServiceService } from 'src/app/Service/token/token-service.service';

@Component({
  selector: 'app-userallbooking',
  templateUrl: './userallbooking.component.html',
  styleUrls: ['./userallbooking.component.css'],
})
export class UserallbookingComponent implements OnInit {
  userBookings: Booking[] = [];

  constructor(
    private bookingService: BookingService,
    private tokenService: TokenServiceService,
    private userJwtClientService: UserJwtClientService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.fetchUserBookings();
  }

  fetchUserBookings() {
    const userId = Number(sessionStorage.getItem('id'));
    console.log(userId);
    const token = this.tokenService.getToken();

    if (userId && token) {
      this.bookingService.getAllBookingsById(userId, token).subscribe(
        (data: Booking[]) => {
          this.userBookings = data;
          console.log('User Bookings:', this.userBookings);
        },
        (error) => {
          console.error('Error fetching user bookings:', error);
        }
      );
    } else {
      console.error('User ID or token is invalid.');
    }
  }

  deleteBooking(bookingId: number) {
    const token = this.tokenService.getToken();
    this.userJwtClientService.deleteBooking(bookingId, token).subscribe(
      (response) => {
        console.log('Booking deleted successfully:', response);
        // After deleting, fetch the updated list of bookings
        this.fetchUserBookings();
        alert('Booking deleted successfully');
      },
      (error) => {
        console.error('Error deleting booking:', error);
      }
    );
  }

  goBack() {
    this.router.navigate(['/userUI']); // Replace '/' with the route you want to navigate back to
  }
}
