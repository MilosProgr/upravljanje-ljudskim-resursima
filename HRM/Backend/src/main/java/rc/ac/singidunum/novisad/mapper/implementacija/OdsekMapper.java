package rc.ac.singidunum.novisad.mapper.implementacija;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.dto.OdsekDTO;
import rc.ac.singidunum.novisad.dto.ZaposleniDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.model.Odsek;

@Component
public class OdsekMapper implements Mapper<OdsekDTO, Odsek> {

	@Override
	public OdsekDTO map(Odsek e) {
		if(e == null) {
			return null;
		}
		OdsekDTO oDto = new OdsekDTO(e.getId(), e.getNaziv(), e.getOpis());
		if(e.getZaposleni()!= null) {
			oDto.setZaposleni(
					e.getZaposleni().stream().map(
							zaposleni ->
							new ZaposleniDTO(zaposleni.getId(), zaposleni.getPrezime(), null, null, null, null, null, null, null, null, null, null, null)
							).collect(Collectors.toList())
					);
		}
		return oDto;
	}

}
