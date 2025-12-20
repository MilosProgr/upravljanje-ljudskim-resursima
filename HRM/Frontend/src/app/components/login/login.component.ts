import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login/login.service';
import { NgIf } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';

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
  login() {
    if (this.loginForma.valid) {
      this.loginService.login(this.loginForma.value).subscribe(

        (res: any) => {
          console.log("Odgovor:", res)
          if (res) {//res.token
            console.log("Working")
            this.router.navigate(['/home']);

          } else {
            console.log("Greska: ", res.message)
            this.loginFailed = true;
          }
        },
      );
    }
  }
}
