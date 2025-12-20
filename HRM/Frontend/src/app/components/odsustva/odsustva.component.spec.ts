import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OdsustvaComponent } from './odsustva.component';

describe('OdsustvaComponent', () => {
  let component: OdsustvaComponent;
  let fixture: ComponentFixture<OdsustvaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OdsustvaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OdsustvaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
