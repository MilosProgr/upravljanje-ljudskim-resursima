import { Routes } from '@angular/router';
import { ZaposleniComponent } from './components/zaposleni/zaposleni.component';
import { authGuard } from './guards/auth.guard';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { OdsekComponent } from './components/odsek/odsek.component';
import { DodajZaposlenogComponent } from './components/zaposleni/dodaj-zaposlenog/dodaj-zaposlenog.component';
import { ObukaComponent } from './components/obuka/obuka.component';
import { DodajObukuComponent } from './components/obuka/dodaj-obuku/dodaj-obuku.component';
import { ProfileComponent } from './components/profile/profile.component';
import { EditProfileComponent } from './components/profile/edit-profile/edit-profile.component';
import { OdsustvaComponent } from './components/odsustva/odsustva.component';
import { DodajOdsustvoComponent } from './components/odsustva/dodaj-odsustvo/dodaj-odsustvo.component';
import { PravoPristupaComponent } from './components/pravo-pristupa/pravo-pristupa.component';
import { ProjekatComponent } from './components/projekat/projekat.component';
import { DodajProjekatComponent } from './components/projekat/dodaj-projekat/dodaj-projekat.component';
import { ZaduzenjeComponent } from './components/zaduzenje/zaduzenje.component';
import { DodajZaduzenjeComponent } from './components/zaduzenje/dodaj-zaduzenje/dodaj-zaduzenje.component';
import { PlataComponent } from './components/plata/plata.component';
import { DodajPlatuComponent } from './components/plata/dodaj-platu/dodaj-platu.component';
import { RadnoVremeComponent } from './components/radno-vreme/radno-vreme.component';
import { ZaposlenRadnoVremeComponent } from './components/radno-vreme/zaposlen-radno-vreme/zaposlen-radno-vreme.component';
import { MojeObukeComponent } from './components/obuka/moje-obuke/moje-obuke.component';
import { DodajOdsekComponent } from './components/odsek/dodaj-odsek/dodaj-odsek.component';
import { PozicijeComponent } from './components/pozicije/pozicije.component';
import { MojaZaduzenjaComponent } from './components/zaduzenje/moja-zaduzenja/moja-zaduzenja.component';
import { GetZaposleniPlataComponent } from './components/plata/get-zaposleni-plata/get-zaposleni-plata.component';
import { GetZahteviZaposlenogComponent } from './components/odsustva/get-zahtevi-zaposlenog/get-zahtevi-zaposlenog.component';

export const routes: Routes = [

    { path: "", redirectTo: "login", pathMatch: "full" },
    { path: "home", component: HomeComponent },

    { path: "zaposleni", component: ZaposleniComponent, data: { allowedRoles: ['ADMINISTRATOR'] }, canActivate: [authGuard] },
    { path: "zaposleni/dodaj", component: DodajZaposlenogComponent, data: { allowedRoles: ['ADMINISTRATOR'] }, canActivate: [authGuard] },

    { path: "obuke", component: ObukaComponent, data: { allowedRoles: ['ADMINISTRATOR', 'DIREKTOR'] }, canActivate: [authGuard] },
    { path: "obuke/dodaj", component: DodajObukuComponent, data: { allowedRoles: ['ADMINISTRATOR'] }, canActivate: [authGuard] },
    { path: "moje-obuke", component: MojeObukeComponent, data: { allowedRoles: ['RADNIK'] }, canActivate: [authGuard] },

    { path: "odsustva", component: OdsustvaComponent, data: { allowedRoles: ['ADMINISTRATOR'] }, canActivate: [authGuard] },
    { path: "odsustva/dodaj", component: DodajOdsustvoComponent, data: { allowedRoles: ['RADNIK'] }, canActivate: [authGuard] },
    { path: "moji-zahtevi", component: GetZahteviZaposlenogComponent, data: { allowedRoles: ['RADNIK'] }, canActivate: [authGuard] },

    { path: "profil", component: ProfileComponent, data: { allowedRoles: ['ADMINISTRATOR', 'RADNIK', 'DIREKTOR'] }, canActivate: [authGuard] },
    { path: "edit-profile", component: EditProfileComponent, data: { allowedRoles: ['ADMINISTRATOR', 'RADNIK'] }, canActivate: [authGuard] },

    { path: "projekti", component: ProjekatComponent, data: { allowedRoles: ['ADMINISTRATOR', 'DIREKTOR'] }, canActivate: [authGuard] },
    { path: "projekti/dodaj", component: DodajProjekatComponent, data: { allowedRoles: ['ADMINISTRATOR', 'DIREKTOR'] }, canActivate: [authGuard] },

    { path: "zaduzenja", component: ZaduzenjeComponent, data: { allowedRoles: ['ADMINISTRATOR'] }, canActivate: [authGuard] },
    { path: "zaduzenja/dodaj", component: DodajZaduzenjeComponent, data: { allowedRoles: ['ADMINISTRATOR'] }, canActivate: [authGuard] },
    { path: "moja-zaduzenja", component: MojaZaduzenjaComponent, data: { allowedRoles: ['RADNIK'] }, canActivate: [authGuard] },

    { path: "odseci", component: OdsekComponent, data: { allowedRoles: ['ADMINISTRATOR'] }, canActivate: [authGuard] },
    { path: "odseci/dodaj", component: DodajOdsekComponent, data: { allowedRoles: ['ADMINISTRATOR'] }, canActivate: [authGuard] },

    { path: "plate", component: PlataComponent, data: { allowedRoles: ['ADMINISTRATOR'] }, canActivate: [authGuard] },
    { path: "plate/dodaj", component: DodajPlatuComponent, data: { allowedRoles: ['ADMINISTRATOR'] }, canActivate: [authGuard] },
    { path: "plataIsplata", component: GetZaposleniPlataComponent, data: { allowedRoles: ['RADNIK'] }, canActivate: [authGuard] },

    { path: "radnoVreme", component: RadnoVremeComponent, data: { allowedRoles: ['ADMINISTRATOR'] }, canActivate: [authGuard] },
    { path: "radnoVremeZaposlen", component: ZaposlenRadnoVremeComponent, data: { allowedRoles: ['RADNIK'] }, canActivate: [authGuard] },

    { path: "pozicije", component: PozicijeComponent, data: { allowedRoles: ['ADMINISTRATOR'] }, canActivate: [authGuard] },

    { path: "pravoPristupa", component: PravoPristupaComponent, data: { allowedRoles: ['ADMINISTRATOR'] }, canActivate: [authGuard] },
    //login
    { path: "login", component: LoginComponent }
];
