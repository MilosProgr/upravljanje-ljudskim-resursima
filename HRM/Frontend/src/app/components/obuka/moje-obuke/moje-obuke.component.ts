import { Component, OnInit } from '@angular/core';
import { ObukaService } from '../../../services/obuka.service';
import { Obuka } from '../../../model/obuka';
import { NgFor } from '@angular/common';
import { GenericCrudComponent } from '../../../generics/generic-component';
import { GenericTableComponent } from "../../../generics/generic_reusable-table/generic-table.component";

@Component({
  selector: 'app-moje-obuke',
  standalone: true,
  imports: [NgFor, GenericTableComponent],
  templateUrl: './moje-obuke.component.html',
  styleUrl: './moje-obuke.component.css'
})
export class MojeObukeComponent extends GenericCrudComponent<Obuka> implements OnInit {

  constructor(public obukaService: ObukaService) {
    super(obukaService);
  }

  headArray = [
    { 'Head': 'Naziv', 'FieldName': 'naziv' },
    { 'Head': 'Opis', 'FieldName': 'opis' },
  ]

  override ngOnInit(): void {
    this.loadObuke()
  }

  // obukeMoje: Obuka[] = [];

  loadObuke() {
    this.obukaService.dohvatiMojeObuke().subscribe(data => {
      // this.obukeMoje = data;
      this.entities = data;
    })
  }

}
