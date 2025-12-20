import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajProjekatComponent } from './dodaj-projekat.component';

describe('DodajProjekatComponent', () => {
  let component: DodajProjekatComponent;
  let fixture: ComponentFixture<DodajProjekatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DodajProjekatComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DodajProjekatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
