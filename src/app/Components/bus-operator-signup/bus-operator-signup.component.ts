import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Operator } from 'src/app/Model/busoperator.model';
import { OperatorJwtClientService } from 'src/app/Service/operator-jwt/operator-jwt-client.service';
import { OperatorService } from 'src/app/Service/operator-jwt/operator.service';
import { UserService } from 'src/app/Service/user.service';

@Component({
  selector: 'app-bus-operator-signup',
  templateUrl: './bus-operator-signup.component.html',
  styleUrls: ['./bus-operator-signup.component.css'],
})
export class BusOperatorSignupComponent {
  operator: Operator = new Operator();

  constructor(
    private operatorService: OperatorService,
    private router: Router
  ) {}

  createOperator(data: Operator) {
    this.operatorService.createOperator(data).subscribe((response) => {
      console.log('Operator created successfully!', response);
      this.router.navigateByUrl('/signin');
      this.showSuccessAlert('Operator Signup Successful');
    });
  }

  showSuccessAlert(message: string) {
    alert(message); // Display the message in an alert dialog
    setTimeout(() => {
      // Clear the alert after 2 seconds
      let alertContainer = document.querySelector('.alert');
      if (alertContainer) {
        alertContainer.remove();
      }
    }, 2000);
  }

  goBack() {
    this.router.navigate(['dashboard']); // Replace '/' with the route you want to navigate back to
  }
}
