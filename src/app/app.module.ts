import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { DashboardComponent } from './Components/dashboard/dashboard.component';

import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './Components/home/home.component';
import { AdminCreateComponent } from './Components/admin-create/admin-create.component';
import { AdminUIComponent } from './Components/admin-ui/admin-ui.component';
import { UserSignupComponent } from './Components/user-signup/user-signup.component';
import { UserLoginComponent } from './Components/user-login/user-login.component';
import { UserUiComponent } from './Components/user-ui/user-ui.component';
import { UserUpdateComponent } from './Components/user-update/user-update.component';
import { DisplayAllAdminComponent } from './Components/display-all-admin/display-all-admin.component';
import { BusOperatorSignupComponent } from './Components/bus-operator-signup/bus-operator-signup.component';

import { OperatorUiComponent } from './Components/operator-ui/operator-ui/operator-ui.component';
import { UpdateAdminComponent } from './Components/update-admin/update-admin.component';
import { AddBusComponent } from './Components/add-bus/add-bus.component';
import { SearchbusComponent } from './Components/searchbus/searchbus.component';
import { UpdateBusComponent } from './Components/update-bus/update-bus.component';
import { BookBusComponent } from './Components/book-bus/book-bus.component';
import { BookingComponent } from './Components/booking/booking.component';
import { GetAllBusComponent } from './Components/get-all-bus/get-all-bus.component';
import { GetAllUserComponent } from './Components/get-all-user/get-all-user.component';
import { GetAllOperatorComponent } from './Components/get-all-operator/get-all-operator.component';
import { UpdateBusOperatorComponent } from './Components/update-bus-operator/update-bus-operator.component';
import { SelectSeatComponent } from './Components/select-seat/select-seat.component';
import { ViewBookingComponent } from './Components/view-booking/view-booking.component';
import { AllBusesComponent } from './Components/all-buses/all-buses.component';
import { UserallbookingComponent } from './Components/userallbooking/userallbooking.component';
@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    
    HomeComponent,
    AdminCreateComponent,
    AdminUIComponent,
    UserSignupComponent,
    UserLoginComponent,
    UserUiComponent,
    UserUpdateComponent,
    DisplayAllAdminComponent,
    BusOperatorSignupComponent,
   
    OperatorUiComponent,
    UpdateAdminComponent,
    AddBusComponent,
    SearchbusComponent,
    UpdateBusComponent,
    BookBusComponent,
    BookingComponent,
    GetAllBusComponent,
    GetAllUserComponent,
    GetAllOperatorComponent,
    UpdateBusOperatorComponent,
    SelectSeatComponent,
    ViewBookingComponent,
    AllBusesComponent,
    UserallbookingComponent,
   

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
