import { Injectable } from '@angular/core';
import { CrudService } from '../generics/generic-service';
import { Odsek } from '../model/odsek';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class OdsekService extends CrudService<Odsek> {

  constructor(private client: HttpClient) {
    super(client, `${environment.baseUrl}/odseci`);
  }
}
