import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminUIComponent } from './admin-ui.component';

describe('AdminUIComponent', () => {
  let component: AdminUIComponent;
  let fixture: ComponentFixture<AdminUIComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminUIComponent]
    });
    fixture = TestBed.createComponent(AdminUIComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
