import { TestBed } from '@angular/core/testing';

import { UserJwtClientService } from './user-jwt-client.service';

describe('UserJwtClientService', () => {
  let service: UserJwtClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserJwtClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
