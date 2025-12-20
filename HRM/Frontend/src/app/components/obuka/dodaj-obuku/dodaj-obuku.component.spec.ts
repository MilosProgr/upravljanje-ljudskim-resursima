import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajObukuComponent } from './dodaj-obuku.component';

describe('DodajObukuComponent', () => {
  let component: DodajObukuComponent;
  let fixture: ComponentFixture<DodajObukuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DodajObukuComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DodajObukuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
