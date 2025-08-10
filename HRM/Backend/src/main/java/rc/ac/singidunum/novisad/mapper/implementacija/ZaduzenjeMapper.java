package rc.ac.singidunum.novisad.mapper.implementacija;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.dto.ProjekatDTO;
import rc.ac.singidunum.novisad.dto.ZaduzenjeDTO;
import rc.ac.singidunum.novisad.dto.ZaposleniDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.model.Projekat;
import rc.ac.singidunum.novisad.model.Zaduzenje;
import rc.ac.singidunum.novisad.model.Zaposleni;

@Component
public class ZaduzenjeMapper implements Mapper<ZaduzenjeDTO, Zaduzenje> {

	@Override
	public ZaduzenjeDTO map(Zaduzenje e) {
		if(e == null) {
			return null;
		}
		
		Zaposleni z  = e.getZaposleni();
		Projekat p = e.getProjekat();
		
		ZaduzenjeDTO zaduzenjeDTO = new 
				ZaduzenjeDTO(e.getId(), 
						new ZaposleniDTO(z.getId(), z.getIme(), z.getPrezime(), z.getKorisnickoIme(), null, null, null, null, null, null, null, null, null),
						new ProjekatDTO(p.getId(), p.getNaziv(), p.getOpis(), p.getDatumPocetka(), p.getDatumKraja()), 
						e.getUloga());
		return zaduzenjeDTO;
	}

}
