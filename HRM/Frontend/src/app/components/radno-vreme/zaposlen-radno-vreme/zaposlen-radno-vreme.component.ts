import { Component, OnInit } from '@angular/core';
import { RadnoVremeService } from '../../../services/radno-vreme.service';
import { RadnoVreme } from '../../../model/radno_vreme';
import { CommonModule, NgFor } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { GenericCrudComponent } from '../../../generics/generic-component';
import { GenericTableComponent } from "../../../generics/generic_reusable-table/generic-table.component";

@Component({
  selector: 'app-zaposlen-radno-vreme',
  standalone: true,
  imports: [NgFor, CommonModule, MatButtonModule, GenericTableComponent],
  templateUrl: './zaposlen-radno-vreme.component.html',
  styleUrl: './zaposlen-radno-vreme.component.css'
})
export class ZaposlenRadnoVremeComponent extends GenericCrudComponent<RadnoVreme> implements OnInit {
  constructor(private radnoVremeService: RadnoVremeService) {
    super(radnoVremeService);
  }

  // radnoVreme: RadnoVreme[] = [];

  statusDolaska: boolean = false;
  statusOdlaska: boolean = false;

  headArray = [
    { 'Head': 'Zaposleni', 'FieldName': 'zaposleni.korisnickoIme' },
    { 'Head': 'Datum ', 'FieldName': 'datum' },
    { 'Head': 'Vreme Dolaska', 'FieldName': 'vremeDolaska' },
    { 'Head': 'Vreme Odlaska', 'FieldName': 'vremeOdlaska' },
    { 'Head': 'Tip', 'FieldName': 'tip' },
  ]

  override ngOnInit(): void {
    this.refresh();
  }

  evidentirajDolazak() {
    this.radnoVremeService.evidentirajDolazak().subscribe({
      next: (response: any) => {
        console.log('Odgovor!: ', response);
        alert(response.poruka || 'Dolazak evidentiran!');
        this.statusDolaska = true; // ✅ OMOGUĆI dugme za odlazak
        this.refresh()
      },
      error: err => {
        const msg = err.error?.greska || 'Greška prilikom evidentiranja dolaska';
        console.error(msg);
        alert(msg);
      }
    });
  }

  evidentirajOdlazak() {
    this.radnoVremeService.evidentirajOdlazak().subscribe({
      next: (response: any) => {
        alert(response.poruka || 'Odlazak evidentiran!');
        this.statusOdlaska = true; // ✅ ONEMOGUĆI dalje odlazke
        this.refresh();
      },
      error: err => {
        const msg = err.error?.greska || 'Greška prilikom evidentiranja odlaska';
        console.error(msg);
        alert(msg);
      }
    });
  }

  refresh() {
    const danas = new Date().toLocaleDateString('sv-SE'); // "2025-06-16"

    this.radnoVremeService.dohvatiMojeRadnoVreme().subscribe(data => {
      this.entities = data.filter((item: RadnoVreme) => {
        const datum = new Date(item.datum).toLocaleDateString('sv-SE');
        return datum === danas;
      });

      console.log("Radno vreme za danas:", this.entities);
    });
  }




}
