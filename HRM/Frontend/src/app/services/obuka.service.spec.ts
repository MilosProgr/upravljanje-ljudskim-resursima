import { TestBed } from '@angular/core/testing';

import { ObukaService } from './obuka.service';

describe('ObukaService', () => {
  let service: ObukaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ObukaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
