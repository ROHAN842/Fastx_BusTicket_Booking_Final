import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserUiComponent } from './user-ui.component';

describe('UserUiComponent', () => {
  let component: UserUiComponent;
  let fixture: ComponentFixture<UserUiComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserUiComponent]
    });
    fixture = TestBed.createComponent(UserUiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
