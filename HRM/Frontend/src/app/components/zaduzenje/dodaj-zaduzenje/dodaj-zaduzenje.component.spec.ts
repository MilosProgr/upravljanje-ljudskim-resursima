import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajZaduzenjeComponent } from './dodaj-zaduzenje.component';

describe('DodajZaduzenjeComponent', () => {
  let component: DodajZaduzenjeComponent;
  let fixture: ComponentFixture<DodajZaduzenjeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DodajZaduzenjeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DodajZaduzenjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
