import { Component, OnInit } from '@angular/core';
import { RadnoVremeService } from '../../services/radno-vreme.service';
import { RadnoVreme } from '../../model/radno_vreme';
import { Router } from '@angular/router';
import { NgFor } from '@angular/common';
import { GenericTableComponent } from '../../generics/generic_reusable-table/generic-table.component';
import { GenericCrudComponent } from '../../generics/generic-component';

@Component({
  selector: 'app-radno-vreme',
  standalone: true,
  imports: [NgFor, GenericTableComponent],
  templateUrl: './radno-vreme.component.html',
  styleUrl: './radno-vreme.component.css'
})
export class RadnoVremeComponent extends GenericCrudComponent<RadnoVreme> implements OnInit {


  headArray = [
    { 'Head': 'Zaposleni', 'FieldName': 'zaposleni.korisnickoIme' },
    { 'Head': 'Datum', 'FieldName': 'datum' },
    { 'Head': 'Vreme Dolaska', 'FieldName': 'vremeDolaska' },
    { 'Head': 'Vreme Odlaska', 'FieldName': 'vremeOdlaska' },
    { 'Head': 'Tip', 'FieldName': 'tip' },
  ]

  radnoVreme: RadnoVreme[] = [];
  constructor(
    private radnoVremeService: RadnoVremeService,
    private router: Router
  ) {
    super(radnoVremeService);
  }

  override ngOnInit(): void {
    this.loadRVreme();
  }

  loadRVreme() {
    this.radnoVremeService.getPaged().subscribe(vreme => {
      this.entities = vreme.data.content;
      console.log("Rezultat: ", this.entities)
    })
  }

  // dodajRadnoVreme(event: Event) {
  //   this.router.navigate(['/radnoVreme/dodaj'])
  // }






}
