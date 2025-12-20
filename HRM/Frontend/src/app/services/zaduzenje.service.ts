import { Injectable } from '@angular/core';
import { CrudService } from '../generics/generic-service';
import { Zaduzenje } from '../model/zaduzenje';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ZaduzenjeService extends CrudService<Zaduzenje> {

  constructor(private client: HttpClient) {
    super(client, `${environment.baseUrl}/zaduzenje`);
  }

  dohvatiModaZaduzenja() {
    return this.client.get<Zaduzenje[]>(`${this.baseUrl}/me`)
  }
}
