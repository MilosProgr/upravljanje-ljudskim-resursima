package rc.ac.singidunum.novisad.mapper.implementacija;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.dto.AdresaDTO;
import rc.ac.singidunum.novisad.dto.KorisnikPravoPristupaDTO;
import rc.ac.singidunum.novisad.dto.OdsekDTO;
import rc.ac.singidunum.novisad.dto.PozicijaDTO;
import rc.ac.singidunum.novisad.dto.ZaposleniDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.model.KorisnikPravoPristupa;
import rc.ac.singidunum.novisad.model.Pozicija;
import rc.ac.singidunum.novisad.model.Zaposleni;

@Component
public class KorisnikPravoPristupaMapper implements Mapper<KorisnikPravoPristupaDTO, KorisnikPravoPristupa> {

	@Override
	public KorisnikPravoPristupaDTO map(KorisnikPravoPristupa e) {
		if(e == null) {
			return null;
		}
		
		Zaposleni zaposlen = e.getZaposlen();
		Pozicija pozicija = e.getPozicija();
		KorisnikPravoPristupaDTO kpDto = new KorisnikPravoPristupaDTO(
				e.getId(), 
				new PozicijaDTO(pozicija.getId(), pozicija.getNaziv(), pozicija.getOpis()),
				new ZaposleniDTO(
						zaposlen.getId(), 
						zaposlen.getIme(),
						zaposlen.getPrezime(),
						zaposlen.getKorisnickoIme(),
						zaposlen.getLozinka(), 
						zaposlen.getEmail(), 
						zaposlen.getTelefon(),
						zaposlen.getDatumRodjenja(),
						zaposlen.getDatumZaposlenja(),
						zaposlen.getPol(), 
						zaposlen.getStatusZaposlenja(),
						new AdresaDTO(), 
						new OdsekDTO( 
                                zaposlen.getOdsek().getId(),
                                zaposlen.getOdsek().getNaziv(),
                                zaposlen.getOdsek().getOpis()
                        ))
				);
		return kpDto;
	}

}
