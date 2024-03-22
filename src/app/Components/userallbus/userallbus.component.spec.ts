import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserallbusComponent } from './userallbus.component';

describe('UserallbusComponent', () => {
  let component: UserallbusComponent;
  let fixture: ComponentFixture<UserallbusComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserallbusComponent]
    });
    fixture = TestBed.createComponent(UserallbusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
