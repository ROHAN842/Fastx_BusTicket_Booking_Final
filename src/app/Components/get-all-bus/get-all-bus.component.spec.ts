import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllBusComponent } from './get-all-bus.component';

describe('GetAllBusComponent', () => {
  let component: GetAllBusComponent;
  let fixture: ComponentFixture<GetAllBusComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetAllBusComponent]
    });
    fixture = TestBed.createComponent(GetAllBusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
