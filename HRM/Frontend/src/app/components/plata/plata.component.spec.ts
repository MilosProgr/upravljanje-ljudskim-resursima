import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlataComponent } from './plata.component';

describe('PlataComponent', () => {
  let component: PlataComponent;
  let fixture: ComponentFixture<PlataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PlataComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PlataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
