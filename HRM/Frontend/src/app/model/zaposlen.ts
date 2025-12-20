import { Adresa } from "./adresa";
import { Odsek } from "./odsek";
import { Zaduzenje } from "./zaduzenje";

export interface Zaposleni {
    id?: number | undefined;
    ime?: string,
    prezime?: string;
    lozinka?: string;
    email?: string;
    telefon?: string;
    datumRodjenja?: Date;
    datumZaposlenja?: Date;
    korisnickoIme?: string;
    odsek?: Odsek;
    adresa?: Adresa;
    pol?: string;
    statusZaposlenja?: string;

    zaduzenja?: Zaduzenje;
}