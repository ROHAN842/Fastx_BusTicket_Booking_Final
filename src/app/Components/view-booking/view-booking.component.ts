import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Booking } from 'src/app/Model/booking.model';
import { Bus } from 'src/app/Model/bus.model';
import { BookingService } from 'src/app/Service/booking/booking.service';
import { UserJwtClientService } from 'src/app/Service/user-jwt/user-jwt-client.service';
import { User } from 'src/app/Model/user.model';
// import { jsPDF } from 'jspdf';
import { TokenServiceService } from 'src/app/Service/token/token-service.service';

@Component({
  selector: 'app-view-booking',
  templateUrl: './view-booking.component.html',
  styleUrls: ['./view-booking.component.css'],
})
export class ViewBookingComponent implements OnInit {
  @ViewChild('content', { static: false }) el!: ElementRef;
  booking: Booking = new Booking();
  bus: Bus = new Bus(0, '', 0, '', '', 0, 0, '', 0, new Date(), 0);
  user: User = new User();

  token: any;
  amount!: number; // Declare amount variable
  bookingId!: number;
  busId!: number;

  constructor(
    private route: ActivatedRoute,
    private bookingService: BookingService,
    private userJwtClientService: UserJwtClientService,
    private router: Router,
    private tokenservice: TokenServiceService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((data) => {
      this.bookingId = data['bookingId'];
      this.busId = data['busId'];
      this.amount = data['amount'];

      this.token = this.tokenservice.getToken();
      this.bookingService
        .getBookingById(this.bookingId, this.token)
        .subscribe((response1) => {
          this.booking = response1;
          console.log(this.booking);

          this.bookingService.getBusById(this.busId).subscribe((response) => {
            this.bus = response;
            console.log(this.bus);

            let storedId = sessionStorage.getItem('id');
            if (storedId) {
              this.token = this.tokenservice.getToken();
              this.userJwtClientService
                .getUserById(+storedId, this.token)
                .subscribe(
                  (userData) => {
                    this.user = userData;
                    console.log('User Details:', this.user);
                    // Use this.user in your template to display user information
                  },
                  (error) => {
                    console.error('Error fetching user details:', error);
                    // Handle errors accordingly
                  }
                );
            } else {
              console.error('User ID not found in sessionStorage');
              // Handle case where user ID is not available in sessionStorage
            }
          });
        });
    });
  }

  goBack() {
    this.router.navigate(['/userUI']); // Replace '/' with the route you want to navigate back to
  }
}
