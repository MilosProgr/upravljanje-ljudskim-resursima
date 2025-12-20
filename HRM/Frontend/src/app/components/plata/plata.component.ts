import { NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Zaposleni } from '../../model/zaposlen';
import { PlataService } from '../../services/plata.service';
import { Plata } from '../../model/plata';
import { ZaposleniService } from '../../services/zaposleni.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-plata',
  standalone: true,
  imports: [NgFor],
  templateUrl: './plata.component.html',
  styleUrl: './plata.component.css'
})
export class PlataComponent implements OnInit {


  zaposleni: Zaposleni[] = [];
  plate: Plata[] = [];

  constructor(
    private plataService: PlataService,
    private zaposleniService: ZaposleniService,
    private router: Router) {

  }

  ngOnInit(): void {
    this.loadZaposleni();
    this.loadPlate();
  }

  loadPlate() {
    this.plataService.getAll().subscribe(plata => {
      this.plate = plata;
    })
  }

  loadZaposleni() {
    this.zaposleniService.getAll().subscribe(zaposlen => {
      this.zaposleni = zaposlen;
    })
  }

  dodajPlatu(event: Event) {
    this.router.navigate(['/plate/dodaj'])
  }

}
