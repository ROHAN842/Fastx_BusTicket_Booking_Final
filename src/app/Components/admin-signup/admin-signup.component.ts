import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from 'src/app/Model/admin.model';
import { AdminService } from 'src/app/Service/admin.service';

@Component({
  selector: 'app-admin-signup',
  templateUrl: './admin-signup.component.html',
  styleUrls: ['./admin-signup.component.css'],
})
export class AdminSignupComponent {
  admin: Admin = new Admin();

  constructor(private adminService: AdminService, private router: Router) {}

  createAdmin(data: Admin) {
    this.adminService.createAdmin(data).subscribe(
      (response) => {
        console.log('Admin created successfully!', response);
        this.router.navigateByUrl('/signin');
        this.showSuccessAlert('Signup Successful');
      },
      (error) => {
        console.error('Error creating admin:', error);
        // Handle error here
      }
    );
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
    this.router.navigate(['/dashboard']); // Replace '/' with the route you want to navigate back to
  }
}
