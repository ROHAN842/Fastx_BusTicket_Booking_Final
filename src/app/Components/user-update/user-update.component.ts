import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/Model/user.model';
import { TokenServiceService } from 'src/app/Service/token/token-service.service';
import { UserJwtClientService } from 'src/app/Service/user-jwt/user-jwt-client.service';
import { UserService } from 'src/app/Service/user.service';
//import { UserJwtClientService } from 'src/app/Service/user-jwt/user-jwt-client.service';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css'],
})
export class UserUpdateComponent {
  user: User = new User();
  userId!: number;
  token: any;

  constructor(
    private userService: UserJwtClientService,
    private tokenService: TokenServiceService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.userId = Number(sessionStorage.getItem('id'));
    this.token = this.tokenService.getToken();
    this.userService.getUserById(this.userId, this.token).subscribe((data) => {
      this.user = data;
    });
  }

  updateUserDetails() {
    const tokens = this.tokenService.getToken();
    console.log(tokens);
    this.userService.updateUser(this.user, tokens).subscribe(
      () => {
        console.log('User updated');
        alert('User details updated successfully');
      },
      (error) => {
        console.error(error);
      }
    );
  }
  goBack() {
    this.router.navigate(['userUI']); // Replace '/' with the route you want to navigate back to
  }
}
