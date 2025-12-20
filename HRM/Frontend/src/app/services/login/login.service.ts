import { HttpClient } from '@angular/common/http';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { BehaviorSubject, tap, catchError } from 'rxjs';
import { environment } from '../../environments/environment';
import { User } from '../../model/user';
import { Token } from '../../model/token';
import { of } from 'rxjs';
import { Zaposleni } from '../../model/zaposlen';
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl = environment.baseUrl;

  private tokenSubject = new BehaviorSubject<string | null>(null);
  token$ = this.tokenSubject.asObservable();  // Omogućava subscribe komponentama
  user: any = null;
  loggedIn = false;

  constructor(private client: HttpClient) { }

  login(user: User) {
    return this.client.post(`${this.baseUrl}/zaposleni/login`, user, { responseType: 'text' }).pipe(
      tap((response: string) => {
        try {
          if (response) {
            const token: Token = { token: response };

            if (this.isValidToken(token.token)) {
              const decodedToken = JSON.parse(atob(token.token.split(".")[1]));

              this.tokenSubject.next(token.token);
              localStorage.setItem('token', token.token); // Store token

              this.user = {
                ...decodedToken,
                roles: Array.isArray(decodedToken.roles) ? decodedToken.roles : [] // Ensure roles is an array
              };

              this.loggedIn = true;
            } else {
              console.error('Invalid token format');
            }
          } else {
            console.error('Received response is empty or invalid');
          }
        } catch (e) {
          console.error('Error decoding token:', e);
        }
      }),
      catchError(error => {
        console.error('Login error:', error);
        // Return observable with a null value, so that the subscription continues to work
        return of(null);
      })
    );
  }

  register(user: Zaposleni) {
    console.log('Attempting registration with user:', user);
    // const role = this.registrovaniKorisnici.length === 0 ? 'ROLE_ADMIN' : 'ROLE_KORISNIK';
    const role = "RADNIK"
    // console.log('Registracija se vrsi dodeljivanjem:', role);

    //po defaultu treba da kad se korisnik registruje da ima ROLE_STUDENT,dodao sam izmene tako da kada se neko registruje da se izvrsi validateRoles,tako da se pojave dugmadi ako je korisnik registrovan za tu ulogu
    return this.client.post(`${this.baseUrl}/api/zaposleni/register/${role}`, user, { responseType: 'text' }).pipe(
      tap((token: string) => {
        console.log('Received token during registration:', token);
        if (token) {
          try {
            // Store the token in sessionStorage
            sessionStorage.setItem("token", token);

            // Automatically log the user in after registration
            // this.login(user).subscribe();

          } catch (e) {
            console.error('Error handling token after registration:', e);
          }
        }
      }),
      catchError(error => {
        console.error('Register error:', error);
        return of(null);
      })
    );
  }




  getToken(): string | null {
    return this.tokenSubject.value || localStorage.getItem('token');
  }

  logout(): void {
    this.tokenSubject.next(null);
    this.user = null;
    this.loggedIn = false;
  }

  validateRoles(roles: string[]): boolean {
    if (this.user) {
      const userRoles = new Set(this.user.roles || []);
      return roles.some(role => userRoles.has(role));

    }
    return false;
  }

  private isValidToken(token: string): boolean {
    const parts = token.split('.');
    return parts.length === 3;
  }


  getUserProfile() {
    const token = this.getToken();

    if (!token) {
      console.error('Token ne postoji. Korisnik nije ulogovan.');
      return of(null); // Vraća observable koji se ne lomi
    }

    return this.client.get(`${this.baseUrl}/zaposleni/me`, {
      headers: {
        Authorization: `Bearer ${token}`  // Najčešći oblik token headera
      }
    }).pipe(
      catchError(error => {
        console.error('Greška prilikom dohvatanja profila:', error);
        return of(null);
      })
    );
  }

  getLoggedInUserId(): number | null {
    return this.user?.id || null;
  }


  getLoggedInUsername(): string | null {
    return this.user?.sub || null;
  }


}

