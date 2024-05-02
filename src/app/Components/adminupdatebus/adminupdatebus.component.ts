import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Bus } from 'src/app/Model/bus.model';
import { BusService } from 'src/app/Service/bus/bus.service';
import { OperatorJwtClientService } from 'src/app/Service/operator-jwt/operator-jwt-client.service';
import { TokenServiceService } from 'src/app/Service/token/token-service.service';
@Component({
  selector: 'app-adminupdatebus',
  templateUrl: './adminupdatebus.component.html',
  styleUrls: ['./adminupdatebus.component.css'],
})
export class AdminupdatebusComponent {
  bus: Bus = new Bus(0, '', 0, '', '', 0, 0, '', 0, new Date(), 0);

  constructor(
    private busService: OperatorJwtClientService,
    private route: ActivatedRoute,
    private router: Router,
    private tokenService: TokenServiceService
  ) {}

  updateBusDetails() {
    const token = this.tokenService.getToken();
    console.log(token);
    this.busService.updateBus(this.bus, token).subscribe(
      () => {
        alert('Bus details updated');
      },
      (error) => {
        console.error(error);
      }
    );
  }
  goBack() {
    this.router.navigate(['adminUI']); // Replace '/' with the route you want to navigate back to
  }
  logout() {
    // Clear the token when logging out
    this.tokenService.clearToken();
    this.router.navigate(['/dashboard']);
  }
}
