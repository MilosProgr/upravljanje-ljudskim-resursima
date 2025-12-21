import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, of, tap } from 'rxjs';

import { environment } from '../../environments/environment';
import { User } from '../../model/user';
import { Token } from '../../model/token';
import { Zaposleni } from '../../model/zaposlen';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl = environment.baseUrl;

  private tokenSubject = new BehaviorSubject<string | null>(null);
  token$ = this.tokenSubject.asObservable();

  user: any = null;
  loggedIn = false;

  constructor(private http: HttpClient) {
    console.log('[LOGIN SERVICE] Initialized');
  }

  // ============================
  // LOGIN
  // ============================
  login(user: User) {
    console.log('[LOGIN] Request payload:', user);

    return this.http.post(
      `${this.baseUrl}/zaposleni/login`,
      user,
      { responseType: 'text' } // 🔴 KLJUČNO
    ).pipe(
      tap(token => {
        console.log('[LOGIN] Raw token:', token);

        if (!token) {
          console.error('[LOGIN] Token missing');
          return;
        }

        this.tokenSubject.next(token);
        localStorage.setItem('token', token);

        const decodedToken: any = JSON.parse(atob(token.split('.')[1]));

        this.user = {
          ...decodedToken,
          roles: decodedToken.roles ?? []
        };

        this.loggedIn = true;
      }),
      catchError(err => {
        console.error('[LOGIN] HTTP ERROR:', err);
        return of(null);
      })
    );
  }



  // ============================
  // REGISTER
  // ============================
  register(user: Zaposleni) {
    const role = 'RADNIK';

    console.log('[REGISTER] Payload:', user);
    console.log('[REGISTER] Role:', role);

    return this.http
      .post(`${this.baseUrl}/api/zaposleni/register/${role}`, user, {
        responseType: 'text'
      })
      .pipe(
        tap((token: string) => {
          console.log('[REGISTER] Raw token:', token);

          if (token) {
            sessionStorage.setItem('token', token);
            console.log('[REGISTER] Token stored in sessionStorage');
          }
        }),
        catchError(error => {
          console.error('[REGISTER] ERROR:', error);
          return of(null);
        })
      );
  }

  // ============================
  // TOKEN
  // ============================
  getToken(): string | null {
    const token = this.tokenSubject.value || localStorage.getItem('token');
    console.log('[TOKEN] getToken():', token);
    return token;
  }

  logout(): void {
    console.log('[LOGOUT]');
    this.tokenSubject.next(null);
    localStorage.removeItem('token');
    this.user = null;
    this.loggedIn = false;
  }

  // ============================
  // PROFILE
  // ============================
  getUserProfile() {
    const token = this.getToken();

    console.log('[PROFILE] Token:', token);

    if (!token) {
      console.error('[PROFILE] Token ne postoji');
      return of(null);
    }

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    console.log('[PROFILE] Headers:', headers);

    return this.http
      .get(`${this.baseUrl}/zaposleni/me`, { headers })
      .pipe(
        tap(res => console.log('[PROFILE] Response:', res)),
        catchError(error => {
          console.error('[PROFILE] ERROR:', error);
          return of(null);
        })
      );
  }

  // ============================
  // ROLES
  // ============================
  validateRoles(roles: string[]): boolean {
    if (!this.user) {
      console.warn('[ROLES] User nije definisan');
      return false;
    }

    const userRoles = new Set(this.user.roles || []);
    const hasRole = roles.some(role => userRoles.has(role));

    console.log('[ROLES] Required:', roles);
    console.log('[ROLES] User roles:', this.user.roles);
    console.log('[ROLES] Result:', hasRole);

    return hasRole;
  }

  // ============================
  // USER INFO
  // ============================
  getLoggedInUserId(): number | null {
    return this.user?.id ?? null;
  }

  getLoggedInUsername(): string | null {
    return this.user?.sub ?? null;
  }

  // ============================
  // HELPERS
  // ============================
  private isValidToken(token: string): boolean {
    return token.split('.').length === 3;
  }
}
