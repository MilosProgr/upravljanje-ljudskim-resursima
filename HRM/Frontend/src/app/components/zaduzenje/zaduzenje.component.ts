import { Component, OnInit } from '@angular/core';
import { ZaduzenjeService } from '../../services/zaduzenje.service';
import { Zaduzenje } from '../../model/zaduzenje';
import { NgFor } from '@angular/common';
import { Router } from '@angular/router';
import { Zaposleni } from '../../model/zaposlen';
import { ZaposleniService } from '../../services/zaposleni.service';
import { ProjekatService } from '../../services/projekat.service';
import { Projekat } from '../../model/projekat';
import { MatButtonModule } from '@angular/material/button';
import { GenericCrudComponent } from '../../generics/generic-component';

@Component({
  selector: 'app-zaduzenje',
  standalone: true,
  imports: [NgFor, MatButtonModule],
  templateUrl: './zaduzenje.component.html',
  styleUrl: './zaduzenje.component.css'
})
export class ZaduzenjeComponent extends GenericCrudComponent<Zaduzenje> implements OnInit {

  override ngOnInit(): void {
    this.loadZaduzenja();
    this.loadZaposleni();
    this.loadProjekti();
  }

  constructor(
    public zaduzenjeService: ZaduzenjeService,
    public zaposleniService: ZaposleniService,
    public projekatService: ProjekatService,
    public router: Router
  ) {
    super(zaduzenjeService)
  }

  zaduzenja: Zaduzenje[] = [];
  zaposleni: Zaposleni[] = [];
  projekti: Projekat[] = [];

  loadZaduzenja() {
    this.zaduzenjeService.getAll().subscribe(zaduzenje => {
      this.zaduzenja = zaduzenje.map(z => {
        const fullZaposleni = this.zaposleni.find(zap => zap.id === z.zaposleni?.id);
        const fullProjekat = this.projekti.find(proj => proj.id === z.projekat?.id);
        return {
          ...z,
          zaposleni: fullZaposleni ?? z.zaposleni,
          projekat: fullProjekat ?? z.projekat
        };
      });
    });
  }


  loadZaposleni() {
    this.zaposleniService.getAll().subscribe(zaposlen => {
      this.zaposleni = zaposlen;
    })
  }
  loadProjekti() {
    this.projekatService.getAll().subscribe(projekat => {
      this.projekti = projekat;
    })
  }

  dodajZaduzenje(event: Event) {
    this.router.navigate(['/zaduzenja/dodaj'])
  }

  override delete(id: number) {
    this.zaduzenjeService.delete(id).subscribe(() => {
      this.loadZaduzenja();
    });
  }

}
