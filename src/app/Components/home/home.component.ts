// home.component.ts
import { Component } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

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

  onSubmit(sourceCity: string, destinationCity: string, date: string) {
    const formattedDate = this.formatDate(date);
    this.router.navigate(['/searchbus'], { queryParams: { sourceCity, destinationCity, date: formattedDate } });
  }

  goBack(): void {
    this.location.back();
  }

  formatDate(dateString: string): string {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');

    return `${year}-${month}-${day}`;
  }

  isHomePage(): boolean {
    return this.router.url === '/home';
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
