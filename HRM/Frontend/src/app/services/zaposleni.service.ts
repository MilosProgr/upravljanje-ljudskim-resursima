import { Injectable } from '@angular/core';
import { CrudService } from '../generics/generic-service';
import { Zaposleni } from '../model/zaposlen';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ZaposleniService extends CrudService<Zaposleni> {

  constructor(private client: HttpClient) {
    super(client, `${environment.baseUrl}/zaposleni`)
  }

  private profilZaIzmenu?: Zaposleni;

  setProfilZaIzmenu(z: Zaposleni) {
    this.profilZaIzmenu = z;
  }

  getProfilZaIzmenu(): Zaposleni | undefined {
    return this.profilZaIzmenu;
  }

  getRezultatPretrage(params: any) {
    return this.client.get<Zaposleni[]>(`${environment.baseUrl}/zaposleni/pretraga`, { params });
  }

}
