import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/Model/user.model';
import { AdminJwtClientService } from 'src/app/Service/admin-jwt-client.service';
import { BookingService } from 'src/app/Service/booking/booking.service';
import { TokenServiceService } from 'src/app/Service/token/token-service.service';
import { UserJwtClientService } from 'src/app/Service/user-jwt/user-jwt-client.service';

@Component({
  selector: 'app-user-ui',
  templateUrl: './user-ui.component.html',
  styleUrls: ['./user-ui.component.css']
})
export class UserUiComponent {
  user!:User;
  loggedInUsername: string = '';
  token:any;
  showBookingComponent: boolean = false; 
 
   
   
 constructor(private bookservice: BookingService,private jwtService: UserJwtClientService,private router:Router,private tokenservice:TokenServiceService,private userService:AdminJwtClientService) {

}
ngOnInit(): void {
  let id = Number(sessionStorage.getItem("id"));
  this.token=this.tokenservice.getToken();
  this.jwtService.getUserById(id,this.token).subscribe((Response) => {
    this.user = Response;
    this.loggedInUsername = this.user.firstName;
    console.log(this.user.firstName);
  });
}
 onSearch(eventData: any) {
  
   this.showBookingComponent = true;
 }
 
 onSubmit(sourceCity: string, destinationCity: string, date: string) {
   const formattedDate = this.formatDate(date);
 
   this.router.navigate(['/book-bus'], { queryParams: { sourceCity, destinationCity, date: formattedDate } });
 }
 
 formatDate(dateString: string): string {
   const date = new Date(dateString);
   const year = date.getFullYear();
   const month = (date.getMonth() + 1).toString().padStart(2, '0'); 
   const day = date.getDate().toString().padStart(2, '0'); 
 
   return `${year}-${month}-${day}`;
 }
 logout() {
  
  this.tokenservice.clearToken();
  this.router.navigate(['/user-login']);
}
onUpdateUser() {
  
  this.router.navigate(['/user-update']);
}

disablePastDates(date: string): boolean {
  const currentDate = new Date();
  const selectedDate = new Date(date);
  return selectedDate < currentDate;
}
minDate(): string {
  const currentDate = new Date();
  const year = currentDate.getFullYear();
  const month = (currentDate.getMonth() + 1).toString().padStart(2, '0');
  const day = currentDate.getDate().toString().padStart(2, '0');
  return `${year}-${month}-${day}`;
}
seebookings(){

  this.router.navigate(['/user-boo']);
}

  
}
