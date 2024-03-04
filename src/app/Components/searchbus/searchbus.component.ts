import { HttpClient, HttpParams } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Bus } from 'src/app/Model/bus.model';
import { SearchService } from 'src/app/Service/search.service';

@Component({
  selector: 'app-searchbus',
  templateUrl: './searchbus.component.html',
  styleUrls: ['./searchbus.component.css']
})
export class SearchbusComponent {
  buses: Bus[] = [];
  
  constructor(private searchService: SearchService, private route: ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const sourceCity = params['sourceCity'];
      const destinationCity = params['destinationCity'];
      const date = params['date'];

      this.fetchBuses(sourceCity, destinationCity, date);
    });
  }

  fetchBuses(sourceCity: string, destinationCity: string, date: Date): void {
    this.searchService.getAllBusesByCitiesAndDate(sourceCity, destinationCity, date)
      .subscribe((data: Bus[]) => {
        this.buses = data;
        
       
      });
  }
  goBack() {
    this.router.navigate(['/']); // Replace '/' with the route you want to navigate back to
  }
  bookBus() {
  
    this.router.navigate(['/user-login']);
  }
  
}
