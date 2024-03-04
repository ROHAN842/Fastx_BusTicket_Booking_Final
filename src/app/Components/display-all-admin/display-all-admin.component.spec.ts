import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayAllAdminComponent } from './display-all-admin.component';

describe('DisplayAllAdminComponent', () => {
  let component: DisplayAllAdminComponent;
  let fixture: ComponentFixture<DisplayAllAdminComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DisplayAllAdminComponent]
    });
    fixture = TestBed.createComponent(DisplayAllAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
