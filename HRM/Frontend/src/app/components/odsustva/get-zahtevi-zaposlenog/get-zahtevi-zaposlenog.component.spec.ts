import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetZahteviZaposlenogComponent } from './get-zahtevi-zaposlenog.component';

describe('GetZahteviZaposlenogComponent', () => {
  let component: GetZahteviZaposlenogComponent;
  let fixture: ComponentFixture<GetZahteviZaposlenogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetZahteviZaposlenogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GetZahteviZaposlenogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
