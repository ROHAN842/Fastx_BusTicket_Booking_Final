import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/Model/user.model';
import { UserService } from 'src/app/Service/user.service';

@Component({
  selector: 'app-user-signup',
  templateUrl: './user-signup.component.html',
  styleUrls: ['./user-signup.component.css']
})
export class UserSignupComponent {

  user:User=new User();
  constructor(private userService:UserService,private router: Router){}

  createUser(data:User){
    this.userService.createUser(data).subscribe(
      (response) => {
        console.log('User created successfully!', response);
        this.router.navigateByUrl('/signin');
        this.showSuccessAlert('Signup Successful');

       
      })
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

