package rc.ac.singidunum.novisad.features.adresa;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.features.adresa.AdresaDTO.AdresaDTORecord;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;

@Component
public class AdresaMapper implements Mapper<AdresaDTORecord, Adresa> {

	@Override
	public AdresaDTORecord map(Adresa e) {
		if(e == null) {
			return null;
		}
		AdresaDTORecord adresaDTO = new AdresaDTORecord(e.getId(), e.getUlica(), e.getBroj(), e.getGrad(), e.getPostanskiBroj(), e.getDrzava());
		return adresaDTO;
	}

}
