import { Component, OnInit } from '@angular/core';
import { ProjekatService } from '../../services/projekat.service';
import { Projekat } from '../../model/projekat';
import { NgFor } from '@angular/common';
import { Router } from '@angular/router';
import { GenericTableComponent } from '../../generics/generic_reusable-table/generic-table.component';
import { GenericReusableFormsComponent } from "../../generics/generic-reusable-forms/generic-reusable-forms.component";
import { GenericCrudComponent } from '../../generics/generic-component';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-projekat',
  standalone: true,
  imports: [NgFor, GenericTableComponent, MatButtonModule, GenericReusableFormsComponent],
  templateUrl: './projekat.component.html',
  styleUrl: './projekat.component.css'
})
export class ProjekatComponent extends GenericCrudComponent<Projekat> implements OnInit {

  // ngOnInit(): void {
  //   this.loadProjekti();
  // }

  projekti: Projekat[] = [];
  constructor(
    private projekatService: ProjekatService,
    private router: Router) {
    super(projekatService);
  }

  headArray = [
    { 'Head': 'Naziv', 'FieldName': 'naziv' },
    { 'Head': 'Opis', 'FieldName': 'opis' },
    { 'Head': 'Datum Pocetka', 'FieldName': 'datumPocetka' },
    { 'Head': 'Datum Kraja', 'FieldName': 'datumKraja' },
  ]


  loadProjekti() {
    this.projekatService.getAll().subscribe(projekat => {
      this.projekti = projekat;
    })
  }

  dodajProjekat(event: Event) {
    this.router.navigate(['/projekti/dodaj'])
  }


}
