import { Injectable } from '@angular/core';
import { CrudService } from '../generics/generic-service';
import { Obuka } from '../model/obuka';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ObukaService extends CrudService<Obuka> {

  constructor(private client: HttpClient) {
    super(client, `${environment.baseUrl}/obuke`);
  }

  dohvatiMojeObuke() {
    return this.client.get<Obuka[]>(`${this.baseUrl}/me`)
  }
}
