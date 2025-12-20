import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MojeObukeComponent } from './moje-obuke.component';

describe('MojeObukeComponent', () => {
  let component: MojeObukeComponent;
  let fixture: ComponentFixture<MojeObukeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MojeObukeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MojeObukeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
