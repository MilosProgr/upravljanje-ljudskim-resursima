import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Zaposleni } from '../../../model/zaposlen';
import { ZaposleniService } from '../../../services/zaposleni.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdresaService } from '../../../services/adresa.service';
import { OdsekService } from '../../../services/odsek.service';
import { Adresa } from '../../../model/adresa';
import { Odsek } from '../../../model/odsek';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-edit-profile',
  standalone: true,
  imports: [CommonModule, FormsModule, MatInputModule, MatButtonModule],
  templateUrl: './edit-profile.component.html',
  styleUrl: './edit-profile.component.css'
})
export class EditProfileComponent implements OnInit {

  zaposlen: Partial<Zaposleni> = {};
  adresa: Adresa[] = [];
  odseci: Odsek[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private zaposlenService: ZaposleniService,
    private adresaService: AdresaService,
    private odsekService: OdsekService

  ) { }

  ngOnInit(): void {
    const nav = this.router.getCurrentNavigation();
    // const profil = nav?.extras?.state?.['profil'];
    const profil = this.zaposlenService.getProfilZaIzmenu(); // ili loginService, ako si to tamo stavio

    if (profil) {
      this.zaposlen = profil;
      console.log("Zaposlen: ", profil)
    } else {
      console.error("Profil nije prosleđen preko state-a.");
    }

    this.getAdresa();
    this.getOdsek();
  }

  getAdresa() {
    this.adresaService.getAll().subscribe(data => {
      this.adresa = data;
    })
  }

  getOdsek() {
    this.odsekService.getAll().subscribe(data => {
      this.odseci = data;
    })
  }

  sacuvajIzmene() {
    if (this.zaposlen && this.zaposlen.id != null) {
      const payload: Zaposleni = {
        id: this.zaposlen.id,
        ime: this.zaposlen.ime ?? '',
        prezime: this.zaposlen.prezime ?? '',
        lozinka: this.zaposlen.lozinka ?? '',
        email: this.zaposlen.email ?? '', // obavezna polja popuni privremeno praznim vrednostima ako mora
        telefon: this.zaposlen.telefon ?? '',
        datumRodjenja: new Date(),
        datumZaposlenja: new Date(),
        korisnickoIme: this.zaposlen.korisnickoIme ?? '',
        pol: this.zaposlen.pol ?? undefined,
        statusZaposlenja: this.zaposlen.statusZaposlenja ?? undefined,
        adresa: this.zaposlen.adresa ? { id: this.zaposlen.adresa.id! } : undefined,
        odsek: this.zaposlen.odsek ? { id: this.zaposlen.odsek.id! } : undefined
      };

      this.zaposlenService.update(this.zaposlen.id, payload).subscribe({
        next: () => {
          alert('Uspešno sačuvano!');
          this.router.navigate(['/profil']);
        },
        error: err => {
          console.error('Greška:', err);
        }
      });
    }
  }

}
