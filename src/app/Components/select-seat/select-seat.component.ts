import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Bus } from 'src/app/Model/bus.model';
import { BusService } from 'src/app/Service/bus/bus.service';
import { BookingService } from 'src/app/Service/booking/booking.service';

@Component({
  selector: 'app-select-seat',
  templateUrl: './select-seat.component.html',
  styleUrls: ['./select-seat.component.css'],
  providers: [DatePipe]
})
export class SelectSeatComponent {

  bookedSeats: string[] = [];
  selectedSeatCount: number = 0;
  selectedSeatsList: Set<string> = new Set();
  selectedBus!: Bus;
  busId!: number;
  bookingDate!: any;
  userId!: number;
  numSeatsPerRow: number = 8;
  seats!: string[][];
  numRows!: number;

  constructor(
    private userService: BusService,
    public datepipe: DatePipe,
    private route: ActivatedRoute,
    private book: BookingService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.busId = params["busId"];
      this.bookingDate = params["date"];
      this.userId = params["userId"];

      this.bookingDate = this.datepipe.transform(this.bookingDate, 'yyyy-MM-dd');

      this.userService.getBusById(this.busId).subscribe((response) => {
        this.selectedBus = response;
        this.numRows = this.calculateNumRows(this.selectedBus.capacity);
        this.seats = this.generateSeats(this.selectedBus.capacity);
      });

      this.book.occupiedSeats(this.bookingDate, this.busId).subscribe((response1) => {
        this.bookedSeats = response1;
      });
    });
  }

  selectSeat(seat: string) {
    if (!this.bookedSeats.includes(seat)) {
      this.selectedSeatCount += 1;
      this.selectedSeatsList.add(seat);
    } else {
      // Seat is already booked, handle accordingly
      console.log('Seat is already booked.');
      // You can show a message to the user or handle it based on your app's logic
    }
  }

  sendDataOfSeats() {
    this.router.navigate(['booking'], {
      queryParams: {
        date: this.bookingDate,
        busId: this.busId,
        selectedSeatCount: this.selectedSeatCount,
        seats: JSON.stringify(Array.from(this.selectedSeatsList))
      }
    });
  }

  calculateNumRows(capacity: number): number {
    return Math.ceil(capacity / this.numSeatsPerRow);
  }

  generateSeats(capacity: any) {
    const seatsArray: string[][] = [];

    for (let i = 0; i < this.numRows; i++) {
      const row: string[] = [];
      for (let j = 0; j < this.numSeatsPerRow; j++) {
        const seatNumber = i * this.numSeatsPerRow + j + 1;
        if (seatNumber <= capacity) {
          row.push(String.fromCharCode(97 + i) + seatNumber);
        }
      }
      seatsArray.push(row);
    }

    return seatsArray;
  }
  goBack() {
    this.router.navigate(['/user-ui']); // Replace '/' with the route you want to navigate back to
  }
}
