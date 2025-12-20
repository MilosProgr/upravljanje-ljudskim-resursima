import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Odsustvo } from '../../../model/odsustvo';
import { OdsustvoService } from '../../../services/odsustvo.service';
import { LoginService } from '../../../services/login/login.service';

@Component({
  selector: 'app-get-zahtevi-zaposlenog',
  standalone: true,
  imports: [NgIf, NgFor],
  templateUrl: './get-zahtevi-zaposlenog.component.html',
  styleUrl: './get-zahtevi-zaposlenog.component.css'
})
export class GetZahteviZaposlenogComponent implements OnInit {
  odsustva: Odsustvo[] = [];

  constructor(
    private odsustvoService: OdsustvoService,
    private loginService: LoginService
  ) { }

  ngOnInit(): void {
    const userId = this.loginService.getLoggedInUserId();
    if (userId) {
      this.ucitajOdsustva(userId);
    } else {
      this.loginService.getUserProfile().subscribe((zaposleni: any) => {
        if (zaposleni?.id) {
          this.ucitajOdsustva(zaposleni.id);
        }
      });
    }
  }

  private ucitajOdsustva(id: number): void {
    this.odsustvoService.zahteviZaposlenog(id).subscribe({
      next: (value) => {
        this.odsustva = value;
      },
      error: (err) => {
        console.error('Greška pri dohvatanju zahteva za odsustvo:', err);
      }
    });
  }
}
