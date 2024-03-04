import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllOperatorComponent } from './get-all-operator.component';

describe('GetAllOperatorComponent', () => {
  let component: GetAllOperatorComponent;
  let fixture: ComponentFixture<GetAllOperatorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetAllOperatorComponent]
    });
    fixture = TestBed.createComponent(GetAllOperatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
