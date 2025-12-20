import { Component, OnInit } from '@angular/core';
import { ObukaService } from '../../../services/obuka.service';
import { Obuka } from '../../../model/obuka';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-moje-obuke',
  standalone: true,
  imports: [NgFor],
  templateUrl: './moje-obuke.component.html',
  styleUrl: './moje-obuke.component.css'
})
export class MojeObukeComponent implements OnInit {

  constructor(public obukaService: ObukaService) { }

  ngOnInit(): void {
    this.loadObuke()
  }

  obukeMoje: Obuka[] = [];

  loadObuke() {
    this.obukaService.dohvatiMojeObuke().subscribe(data => {
      this.obukeMoje = data;
    })
  }

}
