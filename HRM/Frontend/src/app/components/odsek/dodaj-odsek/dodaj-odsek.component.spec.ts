import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajOdsekComponent } from './dodaj-odsek.component';

describe('DodajOdsekComponent', () => {
  let component: DodajOdsekComponent;
  let fixture: ComponentFixture<DodajOdsekComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DodajOdsekComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DodajOdsekComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
