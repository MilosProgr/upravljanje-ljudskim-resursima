import { Component, OnInit } from '@angular/core';
import { OdsustvoService } from '../../services/odsustvo.service';
import { Odsustvo } from '../../model/odsustvo';
import { NgFor, NgIf } from '@angular/common';
import { LoginService } from '../../services/login/login.service';
import { ZaposleniService } from '../../services/zaposleni.service';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-odsustva',
  standalone: true,
  imports: [NgFor, NgIf, MatButtonModule],
  templateUrl: './odsustva.component.html',
  styleUrl: './odsustva.component.css'
})
export class OdsustvaComponent implements OnInit {


  constructor(
    public odsustvoService: OdsustvoService,
    public loginService: LoginService,
    public zaposleniService: ZaposleniService) { }

  odsustva: Odsustvo[] = []
  direktorId: any;

  ngOnInit(): void {
    this.getOdsustva();

    this.loginService.getUserProfile().subscribe(user => {
      this.loginService.user = user;
      this.direktorId = this.odsustva.find((d => { d.id }))
      console.log(this.direktorId)
    });
  }

  getOdsustva() {
    this.odsustvoService.getAll().subscribe(data => {
      this.odsustva = data;
      console.log(this.odsustva)
    })
  }

  odbi(id: number | undefined) {
    this.odsustvoService.delete(id as number).subscribe(() => {
      this.getOdsustva();
    })
  }
  odobri(odsustvo: Odsustvo) {
    const direktorId = this.loginService.user?.id;

    if (!direktorId) {
      alert("Greška: Niste ulogovani kao direktor.");
      return;
    }

    const payload = {
      id: odsustvo.id,
      zaposleni: { id: odsustvo.zaposleni.id },
      tip: odsustvo.tip,
      datumPocetka: odsustvo.datumPocetka,
      datumKraja: odsustvo.datumKraja,
      odobrio: { id: direktorId }
    };




    console.log("s", direktorId)

    this.odsustvoService.update(odsustvo.id as number, payload).subscribe({
      next: () => {
        alert('Uspešno odobreno odsustvo.');
        odsustvo.odobrio = { id: direktorId };

        this.getOdsustva();
      },
      error: () => alert('Greška pri odobravanju odsustva.')
    });


  }


}
