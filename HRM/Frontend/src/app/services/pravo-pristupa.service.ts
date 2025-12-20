import { Injectable } from '@angular/core';
import { CrudService } from '../generics/generic-service';
import { PravoPristupa } from '../model/pravoPristupa';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PravoPristupaService extends CrudService<PravoPristupa> {

  constructor(private client: HttpClient) {
    super(client, `${environment.baseUrl}/pravaPristupa`);
  }
}
