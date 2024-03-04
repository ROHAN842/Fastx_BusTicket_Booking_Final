import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectSeatComponent } from './select-seat.component';

describe('SelectSeatComponent', () => {
  let component: SelectSeatComponent;
  let fixture: ComponentFixture<SelectSeatComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SelectSeatComponent]
    });
    fixture = TestBed.createComponent(SelectSeatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
