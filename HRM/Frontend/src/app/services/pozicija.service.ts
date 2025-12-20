import { Injectable } from '@angular/core';
import { Pozicija } from '../model/pozicija';
import { HttpClient } from '@angular/common/http';
import { CrudService } from '../generics/generic-service';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PozicijaService extends CrudService<Pozicija> {

  constructor(private client: HttpClient) {
    super(client, `${environment.baseUrl}/pozicije`);
  }
}
