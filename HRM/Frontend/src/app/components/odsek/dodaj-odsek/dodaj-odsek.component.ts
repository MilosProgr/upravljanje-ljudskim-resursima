import { Component, OnInit } from '@angular/core';
import { Odsek } from '../../../model/odsek';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Zaposleni } from '../../../model/zaposlen';
import { OdsekService } from '../../../services/odsek.service';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';


@Component({
  selector: 'app-dodaj-odsek',
  standalone: true,
  imports: [ReactiveFormsModule, MatButtonModule, MatInputModule, MatFormFieldModule],
  templateUrl: './dodaj-odsek.component.html',
  styleUrl: './dodaj-odsek.component.css'
})
export class DodajOdsekComponent implements OnInit {

  odsekForm!: FormGroup;
  odseci: Odsek[] = [];

  zaposleni: Zaposleni[] = [];

  constructor(private fb: FormBuilder,
    public odsekService: OdsekService,
    public router: Router) {

  }

  ngOnInit(): void {
    this.odsekForm = this.fb.group({
      naziv: ['', Validators.required],
      opis: ['', Validators.required]
    });
  }



  onSubmit() {
    if (this.odsekForm.valid) {
      const formValue = this.odsekForm.value;

      const payload = {
        naziv: formValue.naziv,
        opis: formValue.opis
      };

      this.odsekService.create(payload).subscribe(
        {
          next: () => {
            alert('Uspešno dodat odsek.');
            this.router.navigate(['/odseci']);
          },


          error: () => alert('Greška pri dodavanju odseka.')
        }
      )
    }
  }


}
