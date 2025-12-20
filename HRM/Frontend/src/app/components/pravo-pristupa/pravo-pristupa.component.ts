import { Component, OnInit } from '@angular/core';
import { PravoPristupaService } from '../../services/pravo-pristupa.service';
import { PravoPristupa } from '../../model/pravoPristupa';
import { Zaposleni } from '../../model/zaposlen';
import { ActivatedRoute } from '@angular/router';
import { PozicijaService } from '../../services/pozicija.service';
import { Pozicija } from '../../model/pozicija';
import { FormsModule, NgModel, ReactiveFormsModule } from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';


@Component({
  selector: 'app-pravo-pristupa',
  standalone: true,
  imports: [ReactiveFormsModule, NgFor, NgIf, FormsModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatOptionModule, MatSelectModule, MatButtonModule],
  templateUrl: './pravo-pristupa.component.html',
  styleUrl: './pravo-pristupa.component.css'
})
export class PravoPristupaComponent implements OnInit {

  ngOnInit(): void {
    this.loadPravoPristupa();
    this.loadPozicije();
  }

  trenutniZaposlen: Zaposleni | undefined;

  constructor(
    private pravoPristupaService: PravoPristupaService,
    private route: ActivatedRoute,
    private pozicijaService: PozicijaService) {

  }

  pravo: PravoPristupa[] = [];
  pozicije: Pozicija[] = [];
  izabranaPozicijaId: number | null = null;
  pravoP: PravoPristupa | undefined;


  loadPozicije() {
    this.pozicijaService.getAll().subscribe(pozicija => {
      this.pozicije = pozicija;
      console.log("Pozicije", this.pozicije)
    })
  }

  loadPravoPristupa() {
    this.pravoPristupaService.getAll().subscribe(pristup => {
      this.pravo = pristup;
      console.log("Pravo", this.pravo)

      this.route.queryParams.subscribe(params => {
        if (params['zaposlen']) {
          this.trenutniZaposlen = JSON.parse(params['zaposlen']);

          // 🔍 Find the matching PravoPristupa record
          const pravo = this.pravo.find(p => p.zaposlen.id === this.trenutniZaposlen!.id);

          if (pravo) {
            this.pravoP = pravo;
            console.log("Nađen PravoPristupa:", this.pravoP);
          } else {
            console.warn("Nije pronađeno pravo pristupa za zaposlenog!");
          }

        } else {
          console.log("Greska!");
        }
      });
    });
  }


  handleFormSubmit(): void {
    if (!this.trenutniZaposlen || !this.izabranaPozicijaId) {
      console.error("Missing zaposlenik or pozicija");
      return;
    }

    const updatedZaposleni: PravoPristupa = {
      id: this.pravoP?.id,
      zaposlen: {
        id: this.trenutniZaposlen.id
      },
      pozicija: { id: this.izabranaPozicijaId }
    };
    console.log("", this.trenutniZaposlen.id, "Pozicija: ", this.izabranaPozicijaId)

    this.pravoPristupaService.update(this.pravoP?.id as number, updatedZaposleni).subscribe(res => {
      console.log("Zaposleni unapređen:", res);
    });
  }


}
