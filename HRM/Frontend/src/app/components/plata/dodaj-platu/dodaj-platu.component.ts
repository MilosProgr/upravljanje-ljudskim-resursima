import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Zaposleni } from '../../../model/zaposlen';
import { ZaposleniService } from '../../../services/zaposleni.service';
import { Plata } from '../../../model/plata';
import { PlataService } from '../../../services/plata.service';

@Component({
  selector: 'app-dodaj-platu',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './dodaj-platu.component.html',
  styleUrl: './dodaj-platu.component.css'
})
export class DodajPlatuComponent implements OnInit {
  izabranZaposlenId: number | null = null;

  zaposleni: Zaposleni[] = [];
  forma!: FormGroup;
  plate: Plata[] = [];


  constructor(
    public zaposleniService: ZaposleniService,
    public plataService: PlataService,
    public fb: FormBuilder
  ) {

  }

  ngOnInit(): void {
    this.loadZaposleni();
    this.forma = this.fb.group({
      iznos: ['', Validators.required],
      zaposleni: ['', Validators.required],
      datumIsplate: ['', Validators.required]
    })

  }

  loadZaposleni() {
    this.zaposleniService.getAll().subscribe(zaposlen => {
      this.zaposleni = zaposlen;
    })
  }

  loadPlata() {
    this.plataService.getAll().subscribe(plata => {
      this.plate = plata;
    })
  }

  handleFormSubmit(): void {
    if (this.izabranZaposlenId) {
      const novaPlata: Plata = {
        id: undefined,
        iznos: this.forma.value.iznos,
        zaposleni: {
          id: this.forma.value.zaposleni
        },
        datumIsplate: this.forma.value.datumIsplate
      }

      this.plataService.create(novaPlata).subscribe((plata => {
        this.loadPlata();
        //redirect soon
      }))
    }
  }

}
