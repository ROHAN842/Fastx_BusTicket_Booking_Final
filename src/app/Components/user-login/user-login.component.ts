import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthRequest } from 'src/app/Model/AuthRequest';
import { TokenServiceService } from 'src/app/Service/token/token-service.service';
import { UserJwtClientService } from 'src/app/Service/user-jwt/user-jwt-client.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {
  
  authRequest: AuthRequest = new AuthRequest();

  constructor(
    private jwtService: UserJwtClientService,
    private router: Router,
    private tokenService: TokenServiceService
  ) {}

  ngOnInit(): void {}

  readFormData(formData: any) {
    if (formData.valid) {
      this.authRequest.username = formData.value.username;
      this.authRequest.password = formData.value.password;

      this.getAccessToken(this.authRequest);
    } else {
      alert('Please fill in the required fields.');
    }
  }
  goBack() {
    this.router.navigate(['/']); // Replace '/' with the route you want to navigate back to
  }
  public getAccessToken(authRequest: any) {
    this.jwtService.getGeneratedToken(authRequest).subscribe(
      (genToken: any) => {
        // Store the generated token
        this.tokenService.setToken(genToken);
  
        // Fetch the user's role
        this.jwtService.getRole(authRequest.username).subscribe(
          (role: any) => {
            console.log('Role:', role);
            
            if (role === 'ROLE_USER' || role === 'ROLE_ADMIN' || role === 'ROLE_BUSOPERATOR') {
              // Store the user's role in sessionStorage
              sessionStorage.setItem('role', role);
              
              // Fetch the user ID and store it in sessionStorage
              this.jwtService.getId(authRequest.username).subscribe(
                (id: any) => {
                  if (id) {
                    sessionStorage.setItem('id', id.toString());
                    console.log('ID:', id);
                  } else {
                    console.error('ID not found in the response.');
                  }
                  
                  // Navigate based on user role
                  switch (role) {
                    case 'ROLE_USER':
                      this.router.navigate(['/userUI']);
                      break;
                    case 'ROLE_ADMIN':
                      this.router.navigate(['/adminUI']);
                      break;
                    case 'ROLE_BUSOPERATOR':
                      this.router.navigate(['/operator-ui']);
                      break;
                    default:
                      console.error('Invalid role:', role);
                      // Handle unknown roles appropriately
                      break;
                  }
                },
                (error) => {
                  console.error('Error fetching ID:', error);
                  // Handle errors appropriately
                }
              );
            } else {
              console.error('Unauthorized role:', role);
              // Handle unauthorized roles appropriately
            }
          },
          (error) => {
            console.error('Error fetching user role:', error);
            // Handle errors appropriately
          }
        );
      },
      (error) => {
        console.error('Error generating token:', error);
        // Handle errors appropriately
      }
    );
  }
}
