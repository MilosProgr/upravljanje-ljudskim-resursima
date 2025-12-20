import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZaduzenjeComponent } from './zaduzenje.component';

describe('ZaduzenjeComponent', () => {
  let component: ZaduzenjeComponent;
  let fixture: ComponentFixture<ZaduzenjeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ZaduzenjeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ZaduzenjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
