import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchbusComponent } from './searchbus.component';

describe('SearchbusComponent', () => {
  let component: SearchbusComponent;
  let fixture: ComponentFixture<SearchbusComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SearchbusComponent]
    });
    fixture = TestBed.createComponent(SearchbusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
