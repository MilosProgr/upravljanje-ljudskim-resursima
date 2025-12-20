import { Injectable } from '@angular/core';
import { CrudService } from '../generics/generic-service';
import { Projekat } from '../model/projekat';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProjekatService extends CrudService<Projekat> {

  constructor(private client: HttpClient) {
    super(client, `${environment.baseUrl}/projekti`)
  }
}
