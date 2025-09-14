package rc.ac.singidunum.novisad.features.zaduzenje;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.features.projekat.Projekat;
import rc.ac.singidunum.novisad.features.projekat.ProjekatDTO.ProjekatDTORecord;
import rc.ac.singidunum.novisad.features.zaduzenje.ZaduzenjeDTO.ZaduzenjeDTORecord;
import rc.ac.singidunum.novisad.features.zaposlen.Zaposleni;
import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniDTO.ZaposleniDTORecord;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;

@Component
public class ZaduzenjeMapper implements Mapper<ZaduzenjeDTORecord, Zaduzenje> {

	@Override
	public ZaduzenjeDTORecord map(Zaduzenje e) {
		if(e == null) {
			return null;
		}
		
		Zaposleni z  = e.getZaposleni();
		Projekat p = e.getProjekat();
		
		ZaduzenjeDTORecord zaduzenjeDTO = new 
				ZaduzenjeDTORecord(e.getId(), 
						new ZaposleniDTORecord(z.getId(), z.getIme(), z.getPrezime(), z.getKorisnickoIme(), null, null, null, null, null, null, null, null, null, null, null),
						new ProjekatDTORecord(p.getId(), p.getNaziv(), p.getOpis(), p.getDatumPocetka(), p.getDatumKraja()), 
						e.getUloga());
		return zaduzenjeDTO;
	}

}
