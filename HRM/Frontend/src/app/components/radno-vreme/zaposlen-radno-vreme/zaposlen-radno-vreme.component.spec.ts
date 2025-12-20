import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZaposlenRadnoVremeComponent } from './zaposlen-radno-vreme.component';

describe('ZaposlenRadnoVremeComponent', () => {
  let component: ZaposlenRadnoVremeComponent;
  let fixture: ComponentFixture<ZaposlenRadnoVremeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ZaposlenRadnoVremeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ZaposlenRadnoVremeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
