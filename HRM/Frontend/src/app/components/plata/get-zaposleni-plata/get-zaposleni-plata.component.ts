import { Component, OnInit } from '@angular/core';
import { Plata } from '../../../model/plata';
import { PlataService } from '../../../services/plata.service';
import { LoginService } from '../../../services/login/login.service';
import { NgIf } from '@angular/common';
@Component({
  selector: 'app-get-zaposleni-plata',
  standalone: true,
  imports: [NgIf],
  templateUrl: './get-zaposleni-plata.component.html',
  styleUrl: './get-zaposleni-plata.component.css'
})
export class GetZaposleniPlataComponent implements OnInit {
  plata: Plata | undefined;
  isUplacena: boolean | null = null;

  constructor(
    private plataService: PlataService,
    private loginService: LoginService
  ) { }

  ngOnInit() {
    const userId = this.loginService.getLoggedInUserId();
    if (userId) {
      this.ucitajPlatu(userId);
    } else {
      this.loginService.getUserProfile().subscribe((zaposleni: any) => {
        if (zaposleni?.id) {
          this.ucitajPlatu(zaposleni.id);
        }
      });
    }
  }

  private ucitajPlatu(userId: number) {
    this.plataService.plataZaposlenog(userId).subscribe({
      next: (data) => {
        this.plata = data;
        this.proveriDatumIsplate();
      },
      error: (err) => console.error('Greška pri dohvatanju plate:', err)
    });
  }

  private proveriDatumIsplate() {
    if (this.plata && this.plata.datumIsplate) {
      const danas = new Date();
      const datumIsplate = new Date(this.plata.datumIsplate);
      this.isUplacena = danas >= datumIsplate;
    }
  }
}
