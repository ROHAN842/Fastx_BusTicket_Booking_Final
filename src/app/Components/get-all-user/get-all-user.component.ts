import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/Model/user.model';
import { AdminJwtClientService } from 'src/app/Service/admin-jwt-client.service';
import { TokenServiceService } from 'src/app/Service/token/token-service.service';

@Component({
  selector: 'app-get-all-user',
  templateUrl: './get-all-user.component.html',
  styleUrls: ['./get-all-user.component.css'],
})
export class GetAllUserComponent {
  userList: User[] = [];

  constructor(
    private adminService: AdminJwtClientService,
    private tokenService: TokenServiceService,
    private router: Router
  ) {}

  ngOnInit() {
    this.fetchUsers();
  }
  fetchUsers() {
    const token = this.tokenService.getToken();
    this.adminService.allUsers(token).subscribe((users: User[]) => {
      this.userList = users;
    });
  }
  deleteUser(userId: number) {
    const token = this.tokenService.getToken();
    this.adminService.deleteUser(userId, token).subscribe(
      (response) => {
        console.log('User deleted:', response);
        this.fetchUsers();
        alert('User deleted successfully');
      },
      (error) => {
        console.error('Error deleting User:', error);
      }
    );
  }
  goBack() {
    this.router.navigate(['adminUI']); // Replace '/' with the route you want to navigate back to
  }
}
