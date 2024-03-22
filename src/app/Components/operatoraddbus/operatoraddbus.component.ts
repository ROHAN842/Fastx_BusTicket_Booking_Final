import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Bus } from 'src/app/Model/bus.model';
import { AdminJwtClientService } from 'src/app/Service/admin-jwt-client.service';
import { AdminService } from 'src/app/Service/admin.service';
import { TokenServiceService } from 'src/app/Service/token/token-service.service';

@Component({
  selector: 'app-operatoraddbus',
  templateUrl: './operatoraddbus.component.html',
  styleUrls: ['./operatoraddbus.component.css'],
})
export class OperatorAddBusComponent {
  specificDate: string;
  bus: Bus = new Bus(0, '', 0, '', '', 0, 0, '', 0, new Date(), 0);

  constructor(
    private adminService: AdminJwtClientService,
    private router: Router,
    private tokenService: TokenServiceService
  ) {
    const currentDate = new Date();
    this.specificDate = this.formatDate(currentDate);

    this.bus = new Bus(0, '', 0, '', '', 0, 0, '', 0, currentDate, 0);
  }

  formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const day = ('0' + date.getDate()).slice(-2);
    return `${year}-${month}-${day}`;
  }

  createBus() {
    const token = this.tokenService.getToken();
    this.adminService.createBus(this.bus, token).subscribe(
      (response) => {
        console.log('Bus added successfully!', response);
        // Call function to show alert
        this.showAlert('Bus added successfully!');
      },
      (error) => {
        console.error('Error adding bus:', error);
        // Call function to show error alert if needed
      }
    );
  }

  showAlert(message: string) {
    alert(message); // You can replace this with a more sophisticated alert/notification system
  }

  goBack() {
    this.router.navigate(['operator-ui']); // Replace '/' with the route you want to navigate back to
  }
  disablePastDates(date: string): boolean {
    const currentDate = new Date();
    const selectedDate = new Date(date);
    return selectedDate < currentDate;
  }
  minDate(): string {
    const currentDate = new Date();
    const year = currentDate.getFullYear();
    const month = (currentDate.getMonth() + 1).toString().padStart(2, '0');
    const day = currentDate.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
  }
}
