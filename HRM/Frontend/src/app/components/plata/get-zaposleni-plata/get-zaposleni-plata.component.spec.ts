import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetZaposleniPlataComponent } from './get-zaposleni-plata.component';

describe('GetZaposleniPlataComponent', () => {
  let component: GetZaposleniPlataComponent;
  let fixture: ComponentFixture<GetZaposleniPlataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetZaposleniPlataComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GetZaposleniPlataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
