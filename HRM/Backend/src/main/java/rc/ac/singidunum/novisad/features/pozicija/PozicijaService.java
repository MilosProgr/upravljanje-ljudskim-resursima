package rc.ac.singidunum.novisad.features.pozicija;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import rc.ac.singidunum.novisad.features.pozicija.PozicijaDTO.PozicijaDTORecord;
import rc.ac.singidunum.novisad.generics.mapper.Mapper;
import rc.ac.singidunum.novisad.generics.service.GenericCrudService;

@Service
public class PozicijaService extends GenericCrudService<PozicijaDTORecord, Pozicija, Long> {

	private PozicijaRepository pRepository;
	
	@Autowired
	protected PozicijaService(JpaRepository<Pozicija, Long> repository, Mapper<PozicijaDTORecord, Pozicija> mapper) {
		super(repository, mapper);
		this.pRepository = (PozicijaRepository) repository;
	}
	
	public Optional<Pozicija> findPozicijaByNaziv(String ime){
		return pRepository.findPozicijaByNaziv(ime);
	}

}
