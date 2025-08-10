package rc.ac.singidunum.novisad.mapper.implementacija;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.dto.AdresaDTO;
import rc.ac.singidunum.novisad.dto.OdsekDTO;
import rc.ac.singidunum.novisad.dto.ZaduzenjeDTO;
import rc.ac.singidunum.novisad.dto.ZaposleniDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.model.Zaposleni;

@Component
public class ZaposleniMapper implements Mapper<ZaposleniDTO, Zaposleni> {

	@Override
	public ZaposleniDTO map(Zaposleni e) {
		if (e == null) {
			return null;
		}

		OdsekDTO odsekDTO = null;
		if (e.getOdsek() != null) {
			odsekDTO = new OdsekDTO(
				e.getOdsek().getId(),
				e.getOdsek().getNaziv(),
				e.getOdsek().getOpis()
			);
		}

		ZaposleniDTO zDto = new ZaposleniDTO(
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
			new AdresaDTO(e.getAdresa().getId(), null, null, null, null, null), // You may also want to check for null here if you're not filling it in manually
			odsekDTO
		);
		
		if(e.getZaduzenja() != null) {
			zDto.setZaduzenja(
					e.getZaduzenja().stream().map(
							zaduzenje ->
							new ZaduzenjeDTO(zaduzenje.getId(), null, null, zaduzenje.getUloga())
								).collect(Collectors.toSet())
					);
		}
		return zDto;
	}

}
