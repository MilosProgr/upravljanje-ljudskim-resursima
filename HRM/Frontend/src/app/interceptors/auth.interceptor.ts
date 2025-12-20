import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { LoginService } from '../services/login/login.service';
import { exhaustMap, switchMap, take } from 'rxjs/operators';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const loginService = inject(LoginService);
  const storedToken = localStorage.getItem('token'); // Get token from storage

  return loginService.token$.pipe(
    take(1),
    switchMap(token => {
      const finalToken = token || storedToken; // Use either the latest or stored token
      if (finalToken) {
        const clonedRequest = req.clone({
          setHeaders: { Authorization: `${finalToken}` }
        });
        return next(clonedRequest);
      }
      console.warn("No token found, sending request without authorization header.");
      return next(req);
    })
  );
};


