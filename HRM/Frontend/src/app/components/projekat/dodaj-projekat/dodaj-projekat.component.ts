import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProjekatService } from '../../../services/projekat.service';
import { Projekat } from '../../../model/projekat';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { provideNativeDateAdapter } from '@angular/material/core';

@Component({
  selector: 'app-dodaj-projekat',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule, MatInputModule, MatButtonModule, MatDatepickerModule],
  templateUrl: './dodaj-projekat.component.html',
  styleUrl: './dodaj-projekat.component.css',
  providers: [provideNativeDateAdapter()]
})
export class DodajProjekatComponent implements OnInit {

  forma!: FormGroup;

  constructor(private fb: FormBuilder, public projekatService: ProjekatService) { }
  ngOnInit(): void {
    this.forma = this.fb.group({
      naziv: ['', Validators.required],
      opis: ['', Validators.required],
      datumPocetka: ['', Validators.required],
      datumKraja: ['', Validators.required]
    })
  }

  handleSubmit() {
    const noviProjekat: Projekat = {
      naziv: this.forma?.value.naziv,
      opis: this.forma?.value.opis,
      datumPocetka: this.forma?.value.datumPocetka,
      datumKraja: this.forma?.value.datumKraja
    }

    this.projekatService.create(noviProjekat).subscribe(() => {
      console.log("Uspesno dodat projekat!")
    })
  }

}
