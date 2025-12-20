import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { RouterOutlet, RouterLink, NavigationEnd, Router } from '@angular/router';
import { LoginService } from './services/login/login.service';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { filter } from 'rxjs';
import { LoginComponent } from "./components/login/login.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, NgIf, SideBarComponent, LoginComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'diplomski';
  showLoginButton = true;

  constructor(public loginService: LoginService, private router: Router) {
    // Listen to router events to determine current route
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe((event: any) => {
      const currentUrl = event.urlAfterRedirects;
      this.showLoginButton = currentUrl === '/';
    });
  }
}
