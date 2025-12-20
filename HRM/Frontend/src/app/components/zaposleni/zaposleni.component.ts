import { Component, OnInit } from '@angular/core';
import { Zaposleni } from '../../model/zaposlen';
import { ZaposleniService } from '../../services/zaposleni.service';
import { GenericTableComponent } from '../../generics/generic_reusable-table/generic-table.component';
import { GenericCrudComponent } from '../../generics/generic-component';
import { NgFor, NgIf } from '@angular/common';
import { LoginService } from '../../services/login/login.service';
import { OdsekService } from '../../services/odsek.service';
import { Odsek } from '../../model/odsek';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';



@Component({
  selector: 'app-zaposleni',
  standalone: true,
  imports: [GenericTableComponent, NgIf, NgFor, RouterLink, FormsModule, MatInputModule, MatButtonModule, MatFormFieldModule, MatSelectModule],
  templateUrl: './zaposleni.component.html',
  styleUrl: './zaposleni.component.css'
})
export class ZaposleniComponent extends GenericCrudComponent<Zaposleni> implements OnInit {



  constructor(
    private zaposlenService: ZaposleniService,
    private odsekService: OdsekService,
    public router: Router,
    public loginService: LoginService) {
    super(zaposlenService);
  }

  imeZaPretragu: string = '';
  prezimeZaPretragu: string = ''
  odsekIdZaPretragu?: number;

  odseci: Odsek[] = [];

  odsekTest: Odsek | undefined;

  // headArray = [
  //   { 'Head': 'Ime', 'FieldName': 'ime' },
  //   { 'Head': 'Prezime', 'FieldName': 'prezime' },
  //   { 'Head': 'Email', 'FieldName': 'email' },
  //   { 'Head': 'Telefon', 'FieldName': 'telefon' },
  //   { 'Head': 'DatumRodjenja', 'FieldName': 'datumRodjenja' },
  //   { 'Head': 'DatumZaposlenja', 'FieldName': 'datumZaposlenja' },
  //   { 'Head': 'KorisnickoIme', 'FieldName': 'korisnickoIme' },
  // ]

  loggedInUsername: string | null = null;

  override ngOnInit(): void {
    this.loggedInUsername = this.loginService.getLoggedInUsername();

    // This will ensure that getAllEntities is called when the component initializes.
    this.getZaposleni();
    this.getOdseci();
    super.ngOnInit();
  }

  getZaposleni() {
    this.zaposlenService.getAll().subscribe(zaposlen => {
      this.entities = zaposlen;
      console.log("zaposleni: ", this.entities)
    })
  }

  getOdseci() {
    this.odsekService.getAll().subscribe(odsek => {
      this.odseci = odsek;
      console.log("odseci", this.odseci);
    })
  }

  odsek(event: any) {
    console.log("Event object:", event);
    console.log("Event odsek_id (before conversion):", event.odsek);

    if (!this.odseci.length) {
      console.error("No odseci found! Maybe getOdseci() has not completed yet.");
      return;
    }

    const odsek_id = Number(event.odsek.id);
    console.log("Converted odsek_id:", odsek_id);

    console.log("List of odseci:", this.odseci);

    const odsek = this.odseci.find((o) => o.id === odsek_id);

    console.log("Odsek found:", odsek);

    if (odsek) {
      this.router.navigate(['/odsek'], {
        queryParams: { odsek: JSON.stringify(odsek) }
      });
    } else {
      console.error("No matching Odsek found for odsek_id:", odsek_id);
    }
  }

  override delete(id: number) {
    super.delete(id);
  }


  unapredi(z: Zaposleni) {
    console.log("Zaposleni: ", z);
    this.router.navigate(['/pravoPristupa'], {
      queryParams: { zaposlen: JSON.stringify(z) }
    })
  }

  pretraziZaposlene() {
    const kriterijumi: any = {};

    if (this.imeZaPretragu) kriterijumi.ime = this.imeZaPretragu;
    if (this.prezimeZaPretragu) kriterijumi.prezime = this.prezimeZaPretragu;

    if (this.odsekIdZaPretragu) kriterijumi.odsekId = this.odsekIdZaPretragu;

    this.zaposlenService.getRezultatPretrage(kriterijumi).subscribe(rezultat => {
      this.entities = rezultat;
      console.log("Rezultat pretrage:", rezultat);
    });
  }

  resetujPretragu() {
    this.imeZaPretragu = '';
    this.prezimeZaPretragu = '';
    this.getZaposleni(); // Prikaz svih zaposlenih ponovo
  }

  resetujFilter() {
    this.odsekIdZaPretragu = undefined;
    this.getZaposleni();
  }



}
