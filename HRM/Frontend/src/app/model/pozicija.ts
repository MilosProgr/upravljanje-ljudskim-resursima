import { Plata } from "./plata";

export interface Pozicija {
    id: number;
    naziv?: string;
    opis?: string;
    plata?: Plata;
}