import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { OdsustvoService } from '../../../services/odsustvo.service';
import { LoginService } from '../../../services/login/login.service';
import { CommonModule } from '@angular/common';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
@Component({
  selector: 'app-dodaj-odsustvo',
  standalone: true,
  imports: [ReactiveFormsModule, MatButtonModule, FormsModule, CommonModule, MatDatepickerModule, MatInputModule, MatSelectModule, MatOptionModule, MatFormFieldModule],
  templateUrl: './dodaj-odsustvo.component.html',
  styleUrl: './dodaj-odsustvo.component.css',
  providers: [provideNativeDateAdapter()]
})
export class DodajOdsustvoComponent implements OnInit {
  odsustvoForm!: FormGroup;
  tipoviOdsustva = ["GODISNJI_ODMOR", "BOLOVANJE", "PORODILJSKO", "NEPLACENO_ODSUSTVO", "LICNI_RAZLOZI"];

  constructor(
    private fb: FormBuilder,
    private odsustvoService: OdsustvoService,
    private loginService: LoginService
  ) { }

  ngOnInit(): void {
    this.odsustvoForm = this.fb.group({
      zaposleni: ['', Validators.required],
      tip: ['', Validators.required],
      datumPocetka: ['', Validators.required],
      datumKraja: ['', Validators.required]
    });

    // Automatski postavi ID trenutno ulogovanog zaposlenog
    this.loginService.getUserProfile().subscribe({
      next: (user: any) => {
        if (user && user.id) {
          this.odsustvoForm.patchValue({ zaposleni: user.id });
        } else {
          alert('Greška: nije moguće učitati podatke o zaposlenom.');
        }
      },
      error: (err: any) => {
        console.error('Greška pri učitavanju profila korisnika:', err);
        alert('Došlo je do greške prilikom učitavanja korisnika.');
      }
    });

  }

  onSubmit() {
    if (this.odsustvoForm.valid) {
      const formValue = this.odsustvoForm.value;

      const payload = {
        zaposleni: { id: formValue.zaposleni },
        tip: formValue.tip,
        datumPocetka: formValue.datumPocetka,
        datumKraja: formValue.datumKraja
      };

      this.odsustvoService.create(payload).subscribe({
        next: () => alert('Uspešno dodato odsustvo.'),
        error: () => alert('Greška pri dodavanju odsustva.')
      });
    }
  }
}
