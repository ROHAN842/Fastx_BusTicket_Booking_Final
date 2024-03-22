import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OperatoraddbusComponent } from './operatoraddbus.component';

describe('OperatoraddbusComponent', () => {
  let component: OperatoraddbusComponent;
  let fixture: ComponentFixture<OperatoraddbusComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OperatoraddbusComponent]
    });
    fixture = TestBed.createComponent(OperatoraddbusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
