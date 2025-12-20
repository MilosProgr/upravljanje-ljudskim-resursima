import { TestBed } from '@angular/core/testing';

import { OdsekService } from './odsek.service';

describe('OdsekService', () => {
  let service: OdsekService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OdsekService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
