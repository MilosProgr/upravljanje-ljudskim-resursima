import { Component, OnInit } from '@angular/core';
import { RadnoVremeService } from '../../services/radno-vreme.service';
import { RadnoVreme } from '../../model/radno_vreme';
import { Router } from '@angular/router';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-radno-vreme',
  standalone: true,
  imports: [NgFor],
  templateUrl: './radno-vreme.component.html',
  styleUrl: './radno-vreme.component.css'
})
export class RadnoVremeComponent implements OnInit {



  ngOnInit(): void {
    this.loadRVreme();
  }
  radnoVreme: RadnoVreme[] = [];
  constructor(
    private radnoVremeService: RadnoVremeService,
    private router: Router
  ) {

  }
  loadRVreme() {
    this.radnoVremeService.getAll().subscribe(vreme => {
      this.radnoVreme = vreme;
    })
  }

  // dodajRadnoVreme(event: Event) {
  //   this.router.navigate(['/radnoVreme/dodaj'])
  // }






}
