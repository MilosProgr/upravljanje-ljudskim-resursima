import { Component, OnInit } from '@angular/core';
import { Zaduzenje } from '../../../model/zaduzenje';
import { ZaduzenjeService } from '../../../services/zaduzenje.service';
import { ProjekatService } from '../../../services/projekat.service';
import { Projekat } from '../../../model/projekat';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-moja-zaduzenja',
  standalone: true,
  imports: [NgFor],
  templateUrl: './moja-zaduzenja.component.html',
  styleUrl: './moja-zaduzenja.component.css'
})
export class MojaZaduzenjaComponent implements OnInit {

  constructor(
    public zaduzenjeService: ZaduzenjeService,
    public projekatService: ProjekatService) { }

  ngOnInit(): void {
    this.loadZaduzenja();
    this.loadProjekti();
  }

  mojaZaduzenja: Zaduzenje[] = [];
  projekti: Projekat[] = [];

  loadZaduzenja() {
    this.zaduzenjeService.dohvatiModaZaduzenja().subscribe(data => {
      this.mojaZaduzenja = data;
    })
  }

  loadProjekti() {
    this.projekatService.getAll().subscribe(data => {
      this.projekti = data;
    })
  }

}
