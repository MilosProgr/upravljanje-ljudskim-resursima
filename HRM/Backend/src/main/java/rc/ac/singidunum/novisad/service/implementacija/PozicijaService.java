package rc.ac.singidunum.novisad.service.implementacija;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.dto.PozicijaDTO;
import rc.ac.singidunum.novisad.mapper.deklaracija.Mapper;
import rc.ac.singidunum.novisad.model.Pozicija;
import rc.ac.singidunum.novisad.repository.PozicijaRepository;
import rc.ac.singidunum.novisad.service.deklaracija.GenericCrudService;

@Service
public class PozicijaService extends GenericCrudService<PozicijaDTO, Pozicija, Long> {

	private PozicijaRepository pRepository;
	
	@Autowired
	protected PozicijaService(JpaRepository<Pozicija, Long> repository, Mapper<PozicijaDTO, Pozicija> mapper) {
		super(repository, mapper);
		this.pRepository = (PozicijaRepository) repository;
	}
	
	public Optional<Pozicija> findPozicijaByNaziv(String ime){
		return pRepository.findPozicijaByNaziv(ime);
	}

}
