import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RadnoVremeComponent } from './radno-vreme.component';

describe('RadnoVremeComponent', () => {
  let component: RadnoVremeComponent;
  let fixture: ComponentFixture<RadnoVremeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RadnoVremeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RadnoVremeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
