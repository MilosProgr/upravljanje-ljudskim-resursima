import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajZaposlenogComponent } from './dodaj-zaposlenog.component';

describe('DodajZaposlenogComponent', () => {
  let component: DodajZaposlenogComponent;
  let fixture: ComponentFixture<DodajZaposlenogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DodajZaposlenogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DodajZaposlenogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
