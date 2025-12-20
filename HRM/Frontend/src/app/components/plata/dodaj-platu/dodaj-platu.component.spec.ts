import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajPlatuComponent } from './dodaj-platu.component';

describe('DodajPlatuComponent', () => {
  let component: DodajPlatuComponent;
  let fixture: ComponentFixture<DodajPlatuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DodajPlatuComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DodajPlatuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
