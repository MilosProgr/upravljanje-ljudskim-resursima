import { Component, OnInit } from '@angular/core';
import { OdsustvoService } from '../../services/odsustvo.service';
import { Odsustvo } from '../../model/odsustvo';
import { NgFor, NgIf } from '@angular/common';
import { LoginService } from '../../services/login/login.service';
import { ZaposleniService } from '../../services/zaposleni.service';
import { MatButtonModule } from '@angular/material/button';
import { GenericCrudComponent } from '../../generics/generic-component';
import { GenericTableComponent } from '../../generics/generic_reusable-table/generic-table.component';

@Component({
  selector: 'app-odsustva',
  standalone: true,
  imports: [NgFor, NgIf, MatButtonModule, GenericTableComponent],
  templateUrl: './odsustva.component.html',
  styleUrl: './odsustva.component.css'
})
export class OdsustvaComponent extends GenericCrudComponent<Odsustvo> {

  constructor(
    public odsustvoService: OdsustvoService,
    public loginService: LoginService,
    public zaposleniService: ZaposleniService
  ) {
    super(odsustvoService);
  }

  override ngOnInit(): void {
    this.loginService.getUserProfile().subscribe(user => {
      this.loginService.user = user;
      this.loadPagedEntities();
    });
  }

  headArray = [
    { 'Head': 'Zaposleni', 'FieldName': 'zaposleni.korisnickoIme' },
    { 'Head': 'Tip', 'FieldName': 'tip' },
    { 'Head': 'Datum Pocetka', 'FieldName': 'datumPocetka' },
    { 'Head': 'Datum Kraja', 'FieldName': 'datumKraja' },


  ]

  odbi(id: number) {
    this.odsustvoService.delete(id).subscribe(() => {
      // Refrešuj GridArray posle brisanja
      this.loadPagedEntities();
    });
  }

  odobri(odsustvo: Odsustvo) {
    const direktorId = this.loginService.user?.id;
    if (!direktorId) return;

    const payload = {
      id: odsustvo.id,
      zaposleni: { id: odsustvo.zaposleni.id },
      tip: odsustvo.tip,
      datumPocetka: odsustvo.datumPocetka,
      datumKraja: odsustvo.datumKraja,
      odobrio: { id: direktorId }
    };

    this.odsustvoService.update(odsustvo.id!, payload).subscribe(() => {
      this.loadPagedEntities();
    });
  }

  prethodnaStrana() {
    if (this.pageNumber > 0) {
      this.pageNumber--;
      this.loadPagedEntities();
    }
  }

  sledecaStrana() {
    if (this.pageNumber + 1 < this.totalPages) {
      this.pageNumber++;
      this.loadPagedEntities();
    }
  }
}

