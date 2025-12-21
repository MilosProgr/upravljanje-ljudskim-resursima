import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { LoginService } from '../services/login/login.service';
import { exhaustMap, switchMap, take } from 'rxjs/operators';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const loginService = inject(LoginService);
  const storedToken = localStorage.getItem('token');

  return loginService.token$.pipe(
    take(1),
    switchMap(token => {
      let finalToken = token ?? storedToken;

      // 🔴 AKO JE SLUČAJNO JSON STRING
      if (finalToken?.startsWith('{')) {
        try {
          finalToken = JSON.parse(finalToken).token;
        } catch {
          finalToken = null;
        }
      }

      if (typeof finalToken === 'string' && finalToken.trim().length > 0) {
        const clonedRequest = req.clone({
          setHeaders: {
            Authorization: ` ${finalToken}`
          }
        });
        return next(clonedRequest);
      }

      return next(req);
    })
  );
};



