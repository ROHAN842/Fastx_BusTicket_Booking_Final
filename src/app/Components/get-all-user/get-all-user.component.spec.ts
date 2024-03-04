import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllUserComponent } from './get-all-user.component';

describe('GetAllUserComponent', () => {
  let component: GetAllUserComponent;
  let fixture: ComponentFixture<GetAllUserComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetAllUserComponent]
    });
    fixture = TestBed.createComponent(GetAllUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
