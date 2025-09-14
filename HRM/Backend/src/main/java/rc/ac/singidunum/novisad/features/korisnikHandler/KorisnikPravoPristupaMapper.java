package rc.ac.singidunum.novisad.features.korisnikHandler;

import java.util.HashSet;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.features.adresa.AdresaDTO.AdresaDTORecord;
import rc.ac.singidunum.novisad.features.korisnikHandler.KorisnikPravoPristupaDTO.KorisnikPravoPristupaDTORecord;
import rc.ac.singidunum.novisad.features.odsek.OdsekDTO.OdsekDTORecord;
import rc.ac.singidunum.novisad.features.pozicija.Pozicija;
import rc.ac.singidunum.novisad.features.pozicija.PozicijaDTO.PozicijaDTORecord;
import rc.ac.singidunum.novisad.features.zaposlen.Zaposleni;
import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniDTO.ZaposleniDTORecord;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;

@Component
public class KorisnikPravoPristupaMapper implements Mapper<KorisnikPravoPristupaDTORecord, KorisnikPravoPristupa> {

	@Override
	public KorisnikPravoPristupaDTORecord map(KorisnikPravoPristupa e) {
		if(e == null) {
			return null;
		}
		
		Zaposleni zaposlen = e.getZaposlen();
		Pozicija pozicija = e.getPozicija();
		KorisnikPravoPristupaDTORecord kpDto = new KorisnikPravoPristupaDTORecord(
				e.getId(), 
				new PozicijaDTORecord(pozicija.getId(), pozicija.getNaziv(), pozicija.getOpis(),null),
				new ZaposleniDTORecord(
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
						new AdresaDTORecord(null, null, null, null, null, null), 
						new OdsekDTORecord( 
                                zaposlen.getOdsek().getId(),
                                zaposlen.getOdsek().getNaziv(),
                                zaposlen.getOdsek().getOpis(),
                                new HashSet<>()
                        ), new HashSet<>(),new HashSet<>())
				);
		return kpDto;
	}

}
