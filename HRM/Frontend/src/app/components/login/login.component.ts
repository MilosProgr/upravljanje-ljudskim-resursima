import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login/login.service';
import { NgIf } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, NgIf, MatButtonModule, MatInputModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  title = 'Login page';

  loginForma: FormGroup = new FormGroup({
    korisnickoIme: new FormControl(null, Validators.required),
    lozinka: new FormControl(null, Validators.required),
  });
  loginFailed = false;

  constructor(public loginService: LoginService, private router: Router) { }

  ngOnInit(): void { }

  //Login
  async login() {
    if (!this.loginForma.valid) return;

    try {
      const user = this.loginForma.value;

      // Pretvaramo Observable u Promise
      const res: any = await firstValueFrom(this.loginService.login(user));

      console.log('Odgovor iz servisa:', res);

      if (res?.token) {
        console.log('Token primljen, redirect na /home');
        this.router.navigate(['/home']);
        this.loginFailed = false;
      } else {
        console.log('Login failed', res);
        this.loginFailed = true;
      }
    } catch (err) {
      console.error('Login error:', err);
      this.loginFailed = true;
    }
  }
}
