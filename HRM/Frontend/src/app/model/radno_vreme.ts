import { Time } from "@angular/common";
import { Zaposleni } from "./zaposlen";

export interface RadnoVreme {
    id: number;
    zaposleni: Zaposleni;
    datum: string; // <-- jer dolazi kao ISO string npr. "2025-06-16"
    vremeDolaska: string | null; // <-- format "08:00", može biti null
    vremeOdlaska: string | null; // <-- format "16:00", može biti null
    tipRadnogVremena: string | null;

}

// export interface RadnoVremeDisplay {
//     id: number;
//     zaposleni: Zaposleni;
//     datum: Date;
//     vremeDolaska: string;
//     vremeOdlaska: string;
// }   