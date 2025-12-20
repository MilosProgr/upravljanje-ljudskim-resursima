import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ObukaService } from '../../../services/obuka.service';
import { ZaposleniService } from '../../../services/zaposleni.service';
import { Zaposleni } from '../../../model/zaposlen';
import { NgFor } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { provideNativeDateAdapter } from '@angular/material/core';
import { Router } from '@angular/router';



@Component({
  selector: 'app-dodaj-obuku',
  standalone: true,
  imports: [ReactiveFormsModule, NgFor, MatButtonModule, MatInputModule, MatSelectModule, MatFormFieldModule, MatOptionModule, MatDatepickerModule],
  templateUrl: './dodaj-obuku.component.html',
  styleUrl: './dodaj-obuku.component.css',
  providers: [provideNativeDateAdapter()]
})
export class DodajObukuComponent implements OnInit {

  obukaForm!: FormGroup;
  zaposleni: Zaposleni[] = [];

  constructor(
    private fb: FormBuilder,
    private obukaService: ObukaService,
    private zaposlenService: ZaposleniService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.obukaForm = this.fb.group({
      zaposleni: ['', Validators.required],
      naziv: ['', Validators.required],
      opis: ['', Validators.required],
      datumOdrzavanja: ['', Validators.required]
    });
    this.loadZaposleni();
  }

  loadZaposleni() {
    this.zaposlenService.getAll().subscribe((data => {
      this.zaposleni = data;
    }))
  }

  onSubmit() {
    if (this.obukaForm.valid) {
      const formValue = this.obukaForm.value;

      const payload = {
        zaposleni: { id: formValue.zaposleni },
        naziv: formValue.naziv,
        opis: formValue.opis,
        datumOdrzavanja: formValue.datumOdrzavanja
      };

      this.obukaService.create(payload).subscribe({
        next: () => {
          alert('Uspešno dodata obuka.');
          this.router.navigate(['/obuke']); // ovde stavi svoju rutu
        },
        error: () => alert('Greška pri dodavanju odsustva.')
      })


    }
  }


}
