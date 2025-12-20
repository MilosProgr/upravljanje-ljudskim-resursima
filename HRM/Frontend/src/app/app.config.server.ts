import { mergeApplicationConfig, ApplicationConfig, Provider } from '@angular/core';
import { provideServerRendering } from '@angular/platform-server';
import { appConfig } from './app.config';
import { BASE_URL } from './generics/generic-service';
import { environment } from './environments/environment';

const serverConfig: ApplicationConfig = {
  providers: [
    provideServerRendering(),
    { provide: BASE_URL, useValue: environment.baseUrl }  // <-- Provider objekat za starije verzije
  ]
};

export const config = mergeApplicationConfig(appConfig, serverConfig);
