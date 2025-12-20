import { Component, OnInit } from '@angular/core';
import { GenericTableComponent } from '../../generics/generic_reusable-table/generic-table.component';
import { GenericCrudComponent } from '../../generics/generic-component';
import { Pozicija } from '../../model/pozicija';
import { PozicijaService } from '../../services/pozicija.service';
import { PlataService } from '../../services/plata.service';
import { Plata } from '../../model/plata';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-pozicije',
  standalone: true,
  imports: [NgFor],
  templateUrl: './pozicije.component.html',
  styleUrl: './pozicije.component.css'
})
export class PozicijeComponent extends GenericCrudComponent<Pozicija> implements OnInit {


  plate: Plata[] = [];

  override ngOnInit(): void {
    super.ngOnInit();

    this.loadPlate();
  }

  constructor(
    private pozicijaSerice: PozicijaService,
    private plataService: PlataService) {
    super(pozicijaSerice)
  }

  loadPlate() {
    this.plataService.getAll().subscribe((plata => {
      this.plate = plata;
      console.log("plate: ", this.plate);
    }))
  }
}
