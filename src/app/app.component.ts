import { Component, HostListener } from '@angular/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'BusTicketReservation';

  //to disable backward browsing in  browser

  constructor(private location: Location) {}

  @HostListener('window:popstate')
  onPopState() {
    this.location.forward();
  }
}
