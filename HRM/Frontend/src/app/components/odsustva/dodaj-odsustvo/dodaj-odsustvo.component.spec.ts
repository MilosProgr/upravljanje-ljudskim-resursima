import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajOdsustvoComponent } from './dodaj-odsustvo.component';

describe('DodajOdsustvoComponent', () => {
  let component: DodajOdsustvoComponent;
  let fixture: ComponentFixture<DodajOdsustvoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DodajOdsustvoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DodajOdsustvoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
