export interface Odsustvo {
    id?: number;
    zaposleni: {
        id: number;
        korisnickoIme?: string | null;
    };
    tip: string;
    datumPocetka: Date;
    datumKraja: Date
    odobrio?: {
        id: number | null;
        korisnickoIme?: string | null;

    };
}