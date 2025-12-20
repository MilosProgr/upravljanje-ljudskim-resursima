import { Injectable } from '@angular/core';
import { CrudService } from '../generics/generic-service';
import { RadnoVreme } from '../model/radno_vreme';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RadnoVremeService extends CrudService<RadnoVreme> {

  constructor(private client: HttpClient) {
    super(client, `${environment.baseUrl}/radnoVreme`);
  }

  evidentirajDolazak() {
    return this.client.post(`${this.baseUrl}/dolazak`, {});
  }

  evidentirajOdlazak() {
    return this.client.put(`${this.baseUrl}/odlazak`, {});
  }

  dohvatiMojeRadnoVreme() {
    return this.client.get<RadnoVreme[]>(`${this.baseUrl}/me`);
  }


}
