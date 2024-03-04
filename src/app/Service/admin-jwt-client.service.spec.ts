import { TestBed } from '@angular/core/testing';

import { AdminJwtClientService } from '../../app/Service/admin-jwt-client.service';

describe('AdminJwtClientService', () => {
  let service: AdminJwtClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminJwtClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
