import { Zaposleni } from "./zaposlen";

export interface Obuka {
    id?: number;
    naziv: string;
    opis: string;
    datumOdrzavanja: Date;
    zaposleni: Zaposleni
}