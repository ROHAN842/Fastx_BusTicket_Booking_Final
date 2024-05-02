import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminupdatebusComponent } from './adminupdatebus.component';

describe('AdminupdatebusComponent', () => {
  let component: AdminupdatebusComponent;
  let fixture: ComponentFixture<AdminupdatebusComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminupdatebusComponent]
    });
    fixture = TestBed.createComponent(AdminupdatebusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
