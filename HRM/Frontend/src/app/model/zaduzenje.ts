import { Projekat } from "./projekat";
import { Zaposleni } from "./zaposlen";

export interface Zaduzenje {
    id: number | undefined;
    zaposleni: Zaposleni;
    projekat: Projekat;
    uloga: string;
}