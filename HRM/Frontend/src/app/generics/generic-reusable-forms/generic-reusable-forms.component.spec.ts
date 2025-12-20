import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenericReusableFormsComponent } from './generic-reusable-forms.component';

describe('GenericReusableFormsComponent', () => {
  let component: GenericReusableFormsComponent;
  let fixture: ComponentFixture<GenericReusableFormsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GenericReusableFormsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GenericReusableFormsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
