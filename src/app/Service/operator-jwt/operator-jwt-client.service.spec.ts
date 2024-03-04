import { TestBed } from '@angular/core/testing';

import { OperatorJwtClientService } from './operator-jwt-client.service';

describe('OperatorJwtClientService', () => {
  let service: OperatorJwtClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OperatorJwtClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
