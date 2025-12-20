import { Injectable } from '@angular/core';
import { CrudService } from '../generics/generic-service';
import { Plata } from '../model/plata';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PlataService extends CrudService<Plata> {

  constructor(private client: HttpClient) {
    super(client, `${environment.baseUrl}/plate`);
  }

  plataZaposlenog(id: number) {
    return this.client.get<Plata>(`${this.baseUrl}/zaposleni/${id}`);
  }


}
