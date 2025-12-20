import { Zaposleni } from "./zaposlen";

export interface Plata {
    id?: number;
    iznos: number;
    zaposleni: Zaposleni;
    datumIsplate: Date;
}