package rc.ac.singidunum.novisad.features.zaposlen;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.features.adresa.AdresaDTO.AdresaDTORecord;
import rc.ac.singidunum.novisad.features.odsek.OdsekDTO.OdsekDTORecord;
import rc.ac.singidunum.novisad.features.projekat.ProjekatDTO.ProjekatDTORecord;
import rc.ac.singidunum.novisad.features.zaduzenje.ZaduzenjeDTO.ZaduzenjeDTORecord;
import rc.ac.singidunum.novisad.features.zaposlen.ZaposleniDTO.ZaposleniDTORecord;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;

@Component
public class ZaposleniMapper implements Mapper<ZaposleniDTORecord, Zaposleni> {

	@Override
	public ZaposleniDTORecord map(Zaposleni e) {
		if (e == null) {
			return null;
		}

		OdsekDTORecord odsekDTO = null;
		if (e.getOdsek() != null) {
			odsekDTO = new OdsekDTORecord(
				e.getOdsek().getId(),
				e.getOdsek().getNaziv(),
				e.getOdsek().getOpis(),
				new HashSet<>()
			);
		}
		Set<ZaduzenjeDTORecord> zaduzenja = null;
		if (e.getZaduzenja() != null) {
		    zaduzenja = e.getZaduzenja().stream()
		        .map(zaduzenje ->
		            new ZaduzenjeDTORecord(
		                zaduzenje.getId(),
		                null,
		                new ProjekatDTORecord(
		                    zaduzenje.getProjekat().getId(),
		                    zaduzenje.getProjekat().getNaziv(),
		                    null, null, null
		                ),
		                zaduzenje.getUloga()
		            )
		        )
		        .collect(Collectors.toSet());
		}
		
		
		ZaposleniDTORecord zDto = new ZaposleniDTORecord(
			e.getId(),
			e.getIme(), 
			e.getPrezime(),
			e.getKorisnickoIme(),
			e.getLozinka(),
			e.getEmail(),
			e.getTelefon(),
			e.getDatumRodjenja(),
			e.getDatumZaposlenja(),
			e.getPol(),
			e.getStatusZaposlenja(),
			new AdresaDTORecord(e.getAdresa().getId(), null, null, null, null, null), 
			odsekDTO,
			zaduzenja,
			new HashSet<>()
		);
		

		return zDto;
	}

}
