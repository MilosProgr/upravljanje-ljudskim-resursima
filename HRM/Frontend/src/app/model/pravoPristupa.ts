import { Pozicija } from "./pozicija";
import { Zaposleni } from "./zaposlen";

export interface PravoPristupa {
    id?: number;
    pozicija: Pozicija;
    zaposlen: Zaposleni
}