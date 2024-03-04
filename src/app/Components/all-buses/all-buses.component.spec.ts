import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllBusesComponent } from './all-buses.component';

describe('AllBusesComponent', () => {
  let component: AllBusesComponent;
  let fixture: ComponentFixture<AllBusesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AllBusesComponent]
    });
    fixture = TestBed.createComponent(AllBusesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
