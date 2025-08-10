package rc.ac.singidunum.novisad.mapper.implementacija;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.dto.AdresaDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.model.Adresa;

@Component
public class AdresaMapper implements Mapper<AdresaDTO, Adresa> {

	@Override
	public AdresaDTO map(Adresa e) {
		if(e == null) {
			return null;
		}
		AdresaDTO adresaDTO = new AdresaDTO(e.getId(), e.getUlica(), e.getBroj(), e.getGrad(), e.getPostanskiBroj(), e.getDrzava());
		return adresaDTO;
	}

}
