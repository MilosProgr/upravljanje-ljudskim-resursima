import { Component, OnInit } from '@angular/core';
import { PayrollRequest, PayrollResult } from '../../model/payroll';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PayrollService } from '../../services/payroll.service';
import { LoginService } from '../../services/login/login.service';
import { PlataService } from '../../services/plata.service';
import { Plata } from '../../model/plata';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-payroll',
  standalone: true,
  templateUrl: './payroll.component.html',
  imports: [NgIf, ReactiveFormsModule],
  styleUrls: ['./payroll.component.css']
})
export class PayrollComponent implements OnInit {

  calculateForm: FormGroup;
  plata?: Plata;
  status?: string;
  result?: PayrollResult;

  constructor(
    private payrollService: PayrollService,
    private fb: FormBuilder,
    private loginService: LoginService,
    private plataService: PlataService
  ) {
    // inicijalizuj formu odmah da NG01052 ne bi izbacio grešku
    this.calculateForm = this.fb.group({
      period: ['', Validators.required],
      brojRadnihSati: [0, Validators.required],
      brojDanaOdsustva: [0, Validators.required],
      brojPrekovremenihSati: [0, Validators.required],
    });
  }

  ngOnInit(): void {
    const userId = this.loginService.getLoggedInUserId();
    if (!userId) return;

    this.ucitajPlatu(userId);
  }

  private ucitajPlatu(userId: number) {
    this.plataService.plataZaposlenog(userId).subscribe({
      next: (data) => (this.plata = data),
      error: (err) => console.error('Greška pri dohvatanju plate:', err)
    });
  }

  onSubmit(): void {
    if (!this.calculateForm.valid || !this.plata) return;

    const userId = this.loginService.getLoggedInUserId();
    if (!userId) return;

    const formValue = this.calculateForm.value;

    const payload: PayrollRequest = {
      requestId: crypto.randomUUID(),
      zaposleniId: userId,
      period: formValue.period,  // string "YYYY-MM"
      osnovnaPlata: this.plata.iznos,
      brojRadnihSati: formValue.brojRadnihSati,
      brojDanaOdsustva: formValue.brojDanaOdsustva,
      brojPrekovremenihSati: formValue.brojPrekovremenihSati
    };

    this.payrollService.requestPayroll(payload).subscribe({
      next: (ack) => {
        this.status = ack.status;
        this.result = ack;
        // kasnije: subscribe na WebSocket po requestId
      },
      error: (err) => console.error('Greška pri slanju zahteva:', err)
    });
  }
}
