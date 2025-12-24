import { Component, OnInit } from '@angular/core';
import { GenericCrudComponent } from '../../generics/generic-component';
import { GenericTableComponent } from '../../generics/generic_reusable-table/generic-table.component';
import { Obuka } from '../../model/obuka';
import { ObukaService } from '../../services/obuka.service';
import { NgFor } from '@angular/common';
import { Zaposleni } from '../../model/zaposlen';
import { ZaposleniService } from '../../services/zaposleni.service';
import { Router, RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-obuka',
  standalone: true,
  imports: [GenericTableComponent, NgFor, RouterLink, MatButtonModule],
  templateUrl: './obuka.component.html',
  styleUrl: './obuka.component.css'
})
export class ObukaComponent extends GenericCrudComponent<Obuka> implements OnInit {

  headArray = [
    { 'Head': 'Zaposleni', 'FieldName': 'zaposleni.korisnickoIme' },
    { 'Head': 'Naziv', 'FieldName': 'naziv' },
    { 'Head': 'Opis', 'FieldName': 'opis' },
    { 'Head': 'Datum Odrzavanja', 'FieldName': 'datumOdrzavanja' },
  ]

  constructor(private obukaService: ObukaService,
    private zaposlenService: ZaposleniService,
    private router: Router
  ) {
    super(obukaService);
  }

  // obuke: Obuka[] = [];
  zaposleni: Zaposleni[] = [];

  override ngOnInit(): void {
    // this.loadObuke();
    this.loadPagedEntities();
    this.loadZaposleni();
  }


  // loadObuke() {
  //   this.obukaService.getAll().subscribe((obuka => {
  //     this.obuke = obuka;
  //   }))
  // }

  loadZaposleni() {
    this.zaposlenService.getAll().subscribe((zaposlen => {
      this.zaposleni = zaposlen;
    }))
  }

}
