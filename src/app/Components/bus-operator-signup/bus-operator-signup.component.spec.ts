import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusOperatorSignupComponent } from './bus-operator-signup.component';

describe('BusOperatorSignupComponent', () => {
  let component: BusOperatorSignupComponent;
  let fixture: ComponentFixture<BusOperatorSignupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BusOperatorSignupComponent]
    });
    fixture = TestBed.createComponent(BusOperatorSignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
