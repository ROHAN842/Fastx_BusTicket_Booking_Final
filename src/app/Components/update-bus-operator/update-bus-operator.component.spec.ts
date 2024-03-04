import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateBusOperatorComponent } from './update-bus-operator.component';

describe('UpdateBusOperatorComponent', () => {
  let component: UpdateBusOperatorComponent;
  let fixture: ComponentFixture<UpdateBusOperatorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateBusOperatorComponent]
    });
    fixture = TestBed.createComponent(UpdateBusOperatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
