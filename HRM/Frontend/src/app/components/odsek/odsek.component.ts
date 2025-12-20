import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Event, RouterLink } from '@angular/router';
import { Odsek } from '../../model/odsek';
import { NgFor } from '@angular/common';
import { OdsekService } from '../../services/odsek.service';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-odsek',
  standalone: true,
  imports: [NgFor, RouterLink, MatButtonModule],
  templateUrl: './odsek.component.html',
  styleUrl: './odsek.component.css'
})
export class OdsekComponent implements OnInit {


  odsek: Odsek | undefined;

  odseci: Odsek[] = []

  ngOnInit(): void {
    this.loadOdseci();
  }

  constructor(private router: Router, private odsekService: OdsekService) { }


  loadOdseci() {
    this.odsekService.getAll().subscribe((odsek => {
      this.odseci = odsek;
    }))
  }

  dodajOdsek(event: Event) {
    this.router.navigate(['/odseci/dodaj'])
  }


}
