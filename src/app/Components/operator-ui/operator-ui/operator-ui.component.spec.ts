import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OperatorUiComponent } from './operator-ui.component';

describe('OperatorUiComponent', () => {
  let component: OperatorUiComponent;
  let fixture: ComponentFixture<OperatorUiComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OperatorUiComponent]
    });
    fixture = TestBed.createComponent(OperatorUiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
