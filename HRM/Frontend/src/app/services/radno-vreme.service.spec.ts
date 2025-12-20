import { TestBed } from '@angular/core/testing';

import { RadnoVremeService } from './radno-vreme.service';

describe('RadnoVremeService', () => {
  let service: RadnoVremeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RadnoVremeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
