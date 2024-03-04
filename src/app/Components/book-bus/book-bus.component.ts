import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Bus } from 'src/app/Model/bus.model';
import { SearchService } from 'src/app/Service/search.service';

@Component({
  selector: 'app-book-bus',
  templateUrl: './book-bus.component.html',
  styleUrls: ['./book-bus.component.css']
})
export class BookBusComponent {
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
  bookBus(busId:number,date:Date) {
   
    console.log(busId);
    this.router.navigate(['/seatBooking',busId,date]);

  }
   
  goBack() {
    this.router.navigate(['userUI']); // Replace '/' with the route you want to navigate back to
  }
}
