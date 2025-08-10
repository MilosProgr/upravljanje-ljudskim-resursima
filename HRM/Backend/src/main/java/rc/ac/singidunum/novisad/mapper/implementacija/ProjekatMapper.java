package rc.ac.singidunum.novisad.mapper.implementacija;

import org.springframework.stereotype.Component;

import rc.ac.singidunum.novisad.dto.ProjekatDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.model.Projekat;

@Component
public class ProjekatMapper implements Mapper<ProjekatDTO, Projekat>{

	@Override
	public ProjekatDTO map(Projekat e) {
		if(e == null) {
			return null;
		}
		
		ProjekatDTO projekatDTO = new ProjekatDTO(e.getId(), e.getNaziv(), e.getOpis(), e.getDatumPocetka(), e.getDatumKraja());
		return projekatDTO;
	}
	
}
