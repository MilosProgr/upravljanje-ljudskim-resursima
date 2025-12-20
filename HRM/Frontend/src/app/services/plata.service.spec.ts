import { TestBed } from '@angular/core/testing';

import { PlataService } from './plata.service';

describe('PlataService', () => {
  let service: PlataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PlataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
