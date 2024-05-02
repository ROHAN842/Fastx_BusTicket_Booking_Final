import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Operator } from 'src/app/Model/busoperator.model';
import { AdminJwtClientService } from 'src/app/Service/admin-jwt-client.service';
import { TokenServiceService } from 'src/app/Service/token/token-service.service';

@Component({
  selector: 'app-get-all-operator',
  templateUrl: './get-all-operator.component.html',
  styleUrls: ['./get-all-operator.component.css'],
})
export class GetAllOperatorComponent {
  operatorList: Operator[] = [];

  constructor(
    private adminService: AdminJwtClientService,
    private tokenService: TokenServiceService,
    private router: Router
  ) {}

  ngOnInit() {
    this.fetchOperators();
  }
  fetchOperators() {
    const token = this.tokenService.getToken();
    this.adminService
      .allBusOperators(token)
      .subscribe((operators: Operator[]) => {
        this.operatorList = operators;
      });
  }
  deleteOperator(operatorId: number) {
    const token = this.tokenService.getToken();
    this.adminService.deleteOperator(operatorId, token).subscribe(
      (response) => {
        console.log('Operator deleted:', response);
        this.fetchOperators();
        alert('Operator deleted successfully');
      },
      (error) => {
        console.error('Error deleting operator:', error);
      }
    );
  }
  goBack() {
    this.router.navigate(['adminUI']); // Replace '/' with the route you want to navigate back to
  }
}
