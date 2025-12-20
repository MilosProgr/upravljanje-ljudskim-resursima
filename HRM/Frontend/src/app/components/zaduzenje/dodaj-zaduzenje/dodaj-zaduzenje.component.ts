import { Component, OnInit } from '@angular/core';
import { ZaduzenjeService } from '../../../services/zaduzenje.service';
import { Zaduzenje } from '../../../model/zaduzenje';
import { ZaposleniService } from '../../../services/zaposleni.service';
import { ProjekatService } from '../../../services/projekat.service';
import { Zaposleni } from '../../../model/zaposlen';
import { Projekat } from '../../../model/projekat';
import { FormsModule, NgModel } from '@angular/forms';
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
  selector: 'app-dodaj-zaduzenje',
  standalone: true,
  imports: [NgFor, FormsModule, MatInputModule, MatButtonModule, MatFormFieldModule, MatSelectModule, MatOptionModule, MatDatepickerModule],
  templateUrl: './dodaj-zaduzenje.component.html',
  styleUrl: './dodaj-zaduzenje.component.css',
  providers: [provideNativeDateAdapter()]
})
export class DodajZaduzenjeComponent implements OnInit {

  zaposleni: Zaposleni[] = [];
  projekti: Projekat[] = [];
  zaduzenja: Zaduzenje[] = [];

  izabranZaposlenId: number | null = null;
  izabranProjekatId: number | null = null;
  uloga: string = '';

  constructor(
    public zaduzenjeService: ZaduzenjeService,
    public zaposleniService: ZaposleniService,
    public projekatService: ProjekatService,
    public router: Router
  ) { }

  ngOnInit(): void {
    this.loadProjekti();
    this.loadZaposleni();
    this.loadZaduzenja();
  }

  loadZaduzenja() {
    this.zaduzenjeService.getAll().subscribe(zaduzenje => {
      this.zaduzenja = zaduzenje;
    });
  }

  loadZaposleni() {
    this.zaposleniService.getAll().subscribe(zaposlen => {
      this.zaposleni = zaposlen;
    });
  }

  loadProjekti() {
    this.projekatService.getAll().subscribe(projekat => {
      this.projekti = projekat;
    });
  }

  handleFormSubmit(): void {
    if (this.izabranZaposlenId && this.izabranProjekatId && this.uloga.trim()) {
      const novoZaduzenje: Zaduzenje = {
        id: undefined,
        zaposleni: { id: this.izabranZaposlenId } as Zaposleni,
        projekat: { id: this.izabranProjekatId } as Projekat,
        uloga: this.uloga.trim()
      };

      this.zaduzenjeService.create(novoZaduzenje).subscribe(() => {
        this.loadZaduzenja(); // Refresh list
        this.izabranZaposlenId = null;
        this.izabranProjekatId = null;
        this.uloga = '';
        this.router.navigate(['/zaduzenja'])
      });
    } else {
      alert('Sva polja su obavezna!');
    }
  }

}
