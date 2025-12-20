import { TestBed } from '@angular/core/testing';

import { PozicijaService } from './pozicija.service';

describe('PozicijaService', () => {
  let service: PozicijaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PozicijaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
