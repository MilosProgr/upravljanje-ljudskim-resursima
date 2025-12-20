import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PozicijeComponent } from './pozicije.component';

describe('PozicijeComponent', () => {
  let component: PozicijeComponent;
  let fixture: ComponentFixture<PozicijeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PozicijeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PozicijeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
