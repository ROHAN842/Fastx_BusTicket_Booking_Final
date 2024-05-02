import { Component, OnInit } from '@angular/core';
import { Admin } from '../../Model/admin.model';
import { AdminService } from '../../Service/admin.service';
import { AdminJwtClientService } from 'src/app/Service/admin-jwt-client.service';
import { TokenServiceService } from 'src/app/Service/token/token-service.service';
import { Router } from '@angular/router';
import { Bus } from 'src/app/Model/bus.model';
import { OperatorJwtClientService } from 'src/app/Service/operator-jwt/operator-jwt-client.service';

import { OperatorService } from 'src/app/Service/operator-jwt/operator.service';

@Component({
  selector: 'app-admin-ui',
  templateUrl: './admin-ui.component.html',
  styleUrls: ['./admin-ui.component.css'],
})
export class AdminUIComponent {
  token: any; // Define the token property
  admin!: Admin;
  loggedInUsername: string = '';
  buses: Bus[] = [];
  bus: Bus = new Bus(0, '', 0, '', '', 0, 0, '', 0, new Date(), 0);

  constructor(
    private router: Router,
    private adminService: AdminJwtClientService,
    private tokenservice: TokenServiceService,
    private busService: OperatorService,
    private operatorService: OperatorJwtClientService
  ) {}
  ngOnInit(): void {
    // Retrieve the admin's name when the component initializes
    let id = Number(sessionStorage.getItem('id'));
    this.token = this.tokenservice.getToken();
    this.adminService.getAdminById(id, this.token).subscribe((response) => {
      this.admin = response;
      this.loggedInUsername = this.admin.firstName; // Store the admin's name
    });
  }

  getalladmins() {
    this.token = this.tokenservice.getToken();
    this.adminService.alladmins(this.token).subscribe((response) => {
      // Display the response in the console

      this.router.navigate(['/display-all-admin']);
    });
  }
  onUpdateUser() {
    this.router.navigate(['/update-admin']);
  }

  goBack() {
    this.router.navigate(['/']); // Replace '/' with the route you want to navigate back to
  }

  createBus() {
    this.router.navigate(['/add-bus']);
  }

  updateBusDetails(bus: Bus) {
    this.token = this.tokenservice.getToken();
    this.operatorService
      .updateBus(this.bus, this.token)
      .subscribe((response) => {
        console.log(response);

        this.router.navigate(['/admin-update-bus', bus.busId]);
      });
  }

  logout() {
    // Clear the token when logging out
    this.tokenservice.clearToken();
    this.router.navigate(['/dashboard']);
  }
  getallUsers() {
    this.token = this.tokenservice.getToken();
    this.adminService.allUsers(this.token).subscribe((response) => {
      // Display the response in the console

      this.router.navigate(['/getAllUsers']);
    });
  }
  getallBuses() {
    this.token = this.tokenservice.getToken();
    this.adminService.allBus(this.token).subscribe((response) => {
      // Display the response in the console

      this.router.navigate(['/all-buses']);
    });
  }
  getallOperator() {
    this.token = this.tokenservice.getToken();
    this.adminService.allBusOperators(this.token).subscribe((response) => {
      // Display the response in the console

      this.router.navigate(['/getAllOperator']);
    });
  }
}
