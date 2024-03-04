import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserallbookingComponent } from './userallbooking.component';

describe('UserallbookingComponent', () => {
  let component: UserallbookingComponent;
  let fixture: ComponentFixture<UserallbookingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserallbookingComponent]
    });
    fixture = TestBed.createComponent(UserallbookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
