export interface PayrollRequest {
    requestId: string;
    zaposleniId: Number;
    period: Date;
    osnovnaPlata: Number;
    brojRadnihSati: Number;
    brojDanaOdsustva: Number;
    brojPrekovremenihSati: Number;
}

export interface PayrollResult {
    requestId: string;
    zaposleniId: number;
    period: string;                 // "YYYY-MM"
    status: 'PROCESSING' | 'SUCCESS' | 'ERROR';
    brutoPlata?: number;
    neto?: number;
    porez?: number;
    doprinosi?: number;
}
