import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Bus } from 'src/app/Model/bus.model';
import { AdminJwtClientService } from 'src/app/Service/admin-jwt-client.service';
import { TokenServiceService } from 'src/app/Service/token/token-service.service';

@Component({
  selector: 'app-userallbus',
  templateUrl: './userallbus.component.html',
  styleUrls: ['./userallbus.component.css'],
})
export class UserallbusComponent {
  busList: Bus[] = [];

  constructor(
    private operatorService: AdminJwtClientService,
    private tokenService: TokenServiceService,
    private adminService: AdminJwtClientService,
    private router: Router
  ) {}

  ngOnInit() {
    this.fetchBuses();
  }
  fetchBuses() {
    const token = this.tokenService.getToken();
    this.operatorService.allBus(token).subscribe((buses: Bus[]) => {
      this.busList = buses;
    });
  }

  goBack() {
    this.router.navigate(['userUI']); // Replace '/' with the route you want to navigate back to
  }
}
