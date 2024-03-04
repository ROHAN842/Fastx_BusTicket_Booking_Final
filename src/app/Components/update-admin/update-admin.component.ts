import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from 'src/app/Model/admin.model';
import { AdminJwtClientService } from 'src/app/Service/admin-jwt-client.service';
import { TokenServiceService } from 'src/app/Service/token/token-service.service';

@Component({
  selector: 'app-update-admin',
  templateUrl: './update-admin.component.html',
  styleUrls: ['./update-admin.component.css']
})
export class UpdateAdminComponent {

  admin: Admin=new Admin();
  adminId!:number;token:any;

  constructor(private tokenservice :TokenServiceService,private adminService: AdminJwtClientService, private tokenService: TokenServiceService,private router:Router) {}

  ngOnInit(): void {
    this.adminId=Number(sessionStorage.getItem("id"));
    this.token=this.tokenservice.getToken();
    this.adminService.getAdminById(this.adminId,this.token).subscribe((data)=>{
    this.admin=data;
  });
  }
 
  updateAdmin() {
   
    const token = this.tokenService.getToken(); 

    this.adminService.updateAdmin(this.admin, token).subscribe(
      (response) => {
        console.log('Admin updated:', response);
        this.router.navigate(['/adminUI']);
    
      },
      (error) => {
        console.error('Error updating admin:', error);
       
      }
    );
  }
goBack() {
  this.router.navigate(['adminUI']); // Replace '/' with the route you want to navigate back to
}


}
