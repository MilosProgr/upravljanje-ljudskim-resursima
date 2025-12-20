import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ZaposleniService } from '../../../services/zaposleni.service';
import { AdresaService } from '../../../services/adresa.service';
import { CommonModule } from '@angular/common';
import { OdsekService } from '../../../services/odsek.service';
import { LoginService } from '../../../services/login/login.service';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { NgModule } from '@angular/core';
import { MatOptionModule } from '@angular/material/core';
import { Router } from '@angular/router';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { provideNativeDateAdapter } from '@angular/material/core';
@Component({
  selector: 'app-dodaj-zaposlenog',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, MatInputModule, MatFormFieldModule, MatSelectModule, MatButtonModule, MatCheckboxModule, FormsModule, MatOptionModule, MatDatepickerModule],
  templateUrl: './dodaj-zaposlenog.component.html',
  styleUrl: './dodaj-zaposlenog.component.css',
  providers: [provideNativeDateAdapter()]
})
export class DodajZaposlenogComponent implements OnInit {
  forma!: FormGroup;
  kreirajNovuAdresu: boolean = false;
  adrese: any[] = [];
  odseci: any[] = [];

  constructor(
    private fb: FormBuilder,
    private zaposleniService: ZaposleniService,
    private adresaService: AdresaService,
    private odsekService: OdsekService,
    private loginService: LoginService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.forma = this.fb.group({
      ime: ['', Validators.required],
      prezime: ['', Validators.required],
      email: ['', Validators.required],
      telefon: ['', Validators.required],
      korisnickoIme: ['', Validators.required],
      lozinka: ['', Validators.required],
      pol: ['', Validators.required],
      statusZaposlenja: ['', Validators.required],
      datumRodjenja: ['', Validators.required],
      datumZaposlenja: ['', Validators.required],

      // Nova adresa
      ulica: [''],
      broj: [''],
      grad: [''],
      postanskiBroj: [''],
      drzava: [''],

      // Postojeća adresa
      selectedAdresaId: [null],

      selectedOdsek: [null],

    });

    this.adresaService.getAll().subscribe((res: any[]) => {
      this.adrese = res;
    });

    this.odsekService.getAll().subscribe((res: any[]) => {
      this.odseci = res;
    })
  }

  statusiZaposlenja: { value: string, viewValue: string }[] = [
    { value: 'AKTIVAN', viewValue: 'Aktivan' },
    { value: 'NA_ODMORU', viewValue: 'Na odmoru' },
    { value: 'UGOVOR_ISTEKAO', viewValue: 'Ugovor istekao' }
  ];


  handleSubmit(): void {
    if (this.kreirajNovuAdresu) {
      const novaAdresa = {
        ulica: this.forma.value.ulica,
        broj: this.forma.value.broj,
        grad: this.forma.value.grad,
        postanskiBroj: this.forma.value.postanskiBroj,
        drzava: this.forma.value.drzava
      };

      this.adresaService.create(novaAdresa).subscribe((adresa) => {
        this.saveZaposleni(adresa);
      });

    } else {
      const adresa = { id: this.forma.value.selectedAdresaId };
      console.log("odsek test,", this.forma.value.selectedOdsek)
      this.saveZaposleni(adresa);
    }
  }

  saveZaposleni(adresa: any): void {
    const zaposleni = {
      ime: this.forma.value.ime,
      prezime: this.forma.value.prezime,
      email: this.forma.value.email,
      telefon: this.forma.value.telefon,
      korisnickoIme: this.forma.value.korisnickoIme,
      lozinka: this.forma.value.lozinka,
      pol: this.forma.value.pol,
      statusZaposlenja: this.forma.value.statusZaposlenja,
      datumRodjenja: this.forma.value.datumRodjenja,
      datumZaposlenja: this.forma.value.datumZaposlenja,
      adresa: adresa,
      odsek: this.forma.value.selectedOdsek
    };

    this.zaposleniService.create(zaposleni).subscribe({
      next: () => {
        this.forma.reset();
        this.kreirajNovuAdresu = false;
        this.router.navigate(['/zaposleni']);
        console.log("Zaposlen dodat!");
      },
      error: (err) => {
        console.error("Greška pri dodavanju zaposlenog:", err);
        alert("Došlo je do greške pri dodavanju zaposlenog. Pokušajte ponovo.");
      }
    });

  }
}
