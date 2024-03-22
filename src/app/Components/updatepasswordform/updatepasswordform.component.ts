import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Password } from 'src/app/Model/password';
import { UserService } from 'src/app/Service/user.service';
import { TokenServiceService } from 'src/app/Service/token/token-service.service'; // Import TokenServiceService

@Component({
  selector: 'app-updatepasswordform',
  templateUrl: './updatepasswordform.component.html',
  styleUrls: ['./updatepasswordform.component.css'],
})
export class UpdatepasswordformComponent {
  constructor(
    private userservice: UserService,
    private router: Router,
    private tokenService: TokenServiceService // Inject TokenServiceService
  ) {}

  goBack() {
    this.router.navigate(['/']); // Replace '/' with the route you want to navigate back to
  }
  resetpassword(data: Password) {
    this.userservice.newPassword(data).subscribe(() => {
      console.log(' password  updated');
      alert('Password Updated Successfully!!!!');
    });

    this.router.navigate(['/signin']);
  }

  setToken(token: string) {
    this.tokenService.setToken(token);
  }
  // resetpassword(data: Password) {
  //   const token = this.tokenService.getToken();

  //   if (token) {
  //     // Pass the token as an argument to the newPassword method
  //     this.userservice.newPassword(data, token).subscribe(
  //       () => {
  //         console.log('Password updated successfully');
  //         // Clear token after use, if needed
  //         this.tokenService.clearToken();
  //       },
  //       (error) => {
  //         console.error('Error updating password:', error);
  //         // Handle error appropriately
  //       }
  //     );
  //   } else {
  //     console.error('Token not found');
  //   }

  //   this.router.navigate(['/signin']);
  // }
}
