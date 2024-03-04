import { Component } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  showBackButton = true;

  constructor(
    private location: Location,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe(() => {
        this.showBackButton = !this.isHomePage(); 
      });
  }

  goBack(): void {
    this.location.back();
  }

  isHomePage(): boolean {
    return this.router.url === '/home'; 
  }
  onSubmit(sourceCity: string, destinationCity: string, date: string) {
    const formattedDate = this.formatDate(date);
    this.router.navigate(['/searchbus'], { queryParams: { sourceCity, destinationCity, date: formattedDate } });
  }
  formatDate(dateString: string): string {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');

    return `${year}-${month}-${day}`;
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

}
