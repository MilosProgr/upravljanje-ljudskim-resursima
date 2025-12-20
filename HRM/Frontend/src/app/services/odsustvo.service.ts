import { Injectable } from '@angular/core';
import { CrudService } from '../generics/generic-service';
import { Odsustvo } from '../model/odsustvo';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class OdsustvoService extends CrudService<Odsustvo> {

  constructor(private client: HttpClient) {
    super(client, `${environment.baseUrl}/odsustva`);
  }

  zahteviZaposlenog(id: number) {
    return this.client.get<Odsustvo[]>(`${this.baseUrl}/zaposleniOdsustvo/${id}`)
  }
}
