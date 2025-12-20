import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login/login.service';
import { MatCardModule } from '@angular/material/card';
import { Zaposleni } from '../../model/zaposlen';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';
import { ZaposleniService } from '../../services/zaposleni.service';



@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [MatCardModule, MatButtonModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {


  constructor(public zaposlenService: ZaposleniService, public loginService: LoginService, public router: Router) {

  }

  user: any;
  ngOnInit(): void {
    this.loginService.getUserProfile().subscribe({
      next: (res: any) => {
        this.user = res;
      },
      error: err => {
        console.error('Greška prilikom dohvatanja profila:', err);
      }
    });
  }

  edituj(event: any) {
    this.zaposlenService.setProfilZaIzmenu(event);

    this.router.navigate(['/edit-profile'], {
      state: { profil: event }
    });
  }



}
