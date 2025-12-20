import { Injectable } from '@angular/core';
import { CrudService } from '../generics/generic-service';
import { Adresa } from '../model/adresa';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AdresaService extends CrudService<Adresa> {

  constructor(private client: HttpClient) {
    super(client, `${environment.baseUrl}/adrese`);
  }
}
